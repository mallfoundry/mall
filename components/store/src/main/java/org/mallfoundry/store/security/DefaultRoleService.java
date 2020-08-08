/*
 * Copyright (C) 2019-2020 the original author or authors.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */

package org.mallfoundry.store.security;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.ListUtils;
import org.mallfoundry.data.SliceList;
import org.mallfoundry.processor.Processors;
import org.mallfoundry.store.staff.Staff;
import org.mallfoundry.util.Copies;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

public class DefaultRoleService implements RoleService, RoleProcessorInvoker {

    private List<RoleProcessor> processors = Collections.emptyList();

    private final RoleRepository roleRepository;

    public DefaultRoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public void setProcessors(List<RoleProcessor> processors) {
        this.processors = ListUtils.emptyIfNull(processors);
    }

    @Override
    public RoleQuery createRoleQuery() {
        return new DefaultRoleQuery();
    }

    @Override
    public RoleId createRoleId(String storeId, String roleId) {
        return new ImmutableRoleId(storeId, roleId);
    }

    @Override
    public Role createRole(RoleId roleId) {
        return this.roleRepository.create(roleId);
    }

    @Transactional
    @Override
    public Role addRole(Role role) {
        role.addStaff(null);
        role.custom();
        return Function.<Role>identity()
                .compose(this.roleRepository::save)
                .compose(this::invokePreProcessBeforeAddRole)
                .apply(role);
    }

    private Role updateRole(Role source, Role target) {
        Copies.notBlank(source::getName).trim(target::setName);
        Copies.notBlank(source::getDescription).trim(target::setDescription);
        if (CollectionUtils.isNotEmpty(source.getAuthorities())) {
            target.setAuthorities(source.getAuthorities());
        }
        return target;
    }

    @Transactional
    @Override
    public Role updateRole(Role role) {
        return Function.<Role>identity()
                .compose(this.roleRepository::save)
                .compose(this::invokePreProcessAfterUpdateRole)
                .<Role>compose(target -> this.updateRole(role, target))
                .compose(this::invokePreProcessBeforeUpdateRole)
                .compose(this::requiredRole)
                .apply(new ImmutableRoleId(role.getStoreId(), role.getId()));
    }

    @Transactional
    @Override
    public void addRoleStaff(RoleId roleId, Staff staff) {
        var role = this.requiredRole(roleId);
        role.addStaff(this.invokePreProcessBeforeAddRoleStaff(role, staff));
        this.roleRepository.save(role);
    }

    @Transactional
    @Override
    public void removeRoleStaff(RoleId roleId, Staff staff) {
        var role = this.requiredRole(roleId);
        role.removeStaff(this.invokePreProcessBeforeRemoveRoleStaff(role, staff));
        this.roleRepository.save(role);
    }

    private Role requiredRole(RoleId roleId) {
        return this.getRole(roleId).orElseThrow();
    }

    @Transactional
    @Override
    public void deleteRole(RoleId roleId) {
        var role = Function.<Role>identity()
                .compose(this::invokePreProcessBeforeDeleteRole)
                .compose(this::requiredRole)
                .apply(roleId);
        /*if (role.isPrimitive()) {
            throw new RoleException("The primitive role cannot be deleted");
        }*/
        role = this.invokePreProcessAfterDeleteRole(role);
        this.roleRepository.delete(role);
    }

    @Override
    public Optional<Role> getRole(RoleId roleId) {
        return this.roleRepository.findById(roleId);
    }

    @Override
    public List<Role> getRoles(Set<RoleId> roleIds) {
        return this.roleRepository.findAllById(roleIds);
    }

    @Override
    public SliceList<Role> getRoles(RoleQuery query) {
        return this.roleRepository.findAll(query);
    }

    @Override
    public Role invokePreProcessBeforeAddRole(Role role) {
        return Processors.stream(this.processors)
                .map(RoleProcessor::preProcessBeforeAddRole)
                .apply(role);
    }

    @Override
    public Role invokePreProcessBeforeUpdateRole(Role role) {
        return Processors.stream(this.processors)
                .map(RoleProcessor::preProcessBeforeUpdateRole)
                .apply(role);
    }

    @Override
    public Role invokePreProcessAfterUpdateRole(Role role) {
        return Processors.stream(this.processors)
                .map(RoleProcessor::preProcessAfterUpdateRole)
                .apply(role);
    }

    @Override
    public Staff invokePreProcessBeforeAddRoleStaff(Role role, Staff staff) {
        return Processors.stream(this.processors)
                .<Staff>map((processor, identity) -> processor.preProcessBeforeAddRoleStaff(role, identity))
                .apply(staff);
    }

    @Override
    public Staff invokePreProcessBeforeRemoveRoleStaff(Role role, Staff staff) {
        return Processors.stream(this.processors)
                .<Staff>map((processor, identity) -> processor.preProcessBeforeRemoveRoleStaff(role, identity))
                .apply(staff);
    }

    @Override
    public Role invokePreProcessBeforeDeleteRole(Role role) {
        return Processors.stream(this.processors)
                .map(RoleProcessor::preProcessBeforeDeleteRole)
                .apply(role);
    }

    @Override
    public Role invokePreProcessAfterDeleteRole(Role role) {
        return Processors.stream(this.processors)
                .map(RoleProcessor::preProcessAfterDeleteRole)
                .apply(role);
    }
}
