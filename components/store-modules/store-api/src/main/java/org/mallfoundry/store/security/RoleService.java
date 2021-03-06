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

import org.mallfoundry.data.SliceList;
import org.mallfoundry.store.StoreId;
import org.mallfoundry.store.staff.Staff;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface RoleService {

    RoleQuery createRoleQuery();

    RoleId createRoleId(StoreId storeId, String roleId);

    RoleId createRoleId(String storeId, String roleId);

    Role createRole(RoleId roleId);

    Role addRole(Role role);

    Role updateRole(Role role);

    void deleteRole(RoleId roleId);

    void clearRoles(StoreId storeId);

    Role createSuperRole(StoreId roleId);

    void changeSuperRole(RoleId roleId);

    Role getSuperRole(StoreId storeId);

    Role getRole(RoleId roleId);

    Optional<Role> findRole(RoleId roleId);

    List<Role> getRoles(Set<RoleId> roleIds);

    SliceList<Role> getRoles(RoleQuery query);

    void addRoleStaff(RoleId roleId, Staff staff);

    void removeRoleStaff(RoleId roleId, Staff staff);
}
