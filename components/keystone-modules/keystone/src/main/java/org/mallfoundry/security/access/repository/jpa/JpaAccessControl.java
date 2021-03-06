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

package org.mallfoundry.security.access.repository.jpa;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.mallfoundry.security.access.AccessControl;
import org.mallfoundry.security.access.AccessControlEntry;
import org.mallfoundry.security.access.AccessControlSupport;
import org.mallfoundry.security.access.Principal;
import org.mallfoundry.security.access.Resource;
import org.springframework.beans.BeanUtils;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "mf_access_control")
public class JpaAccessControl extends AccessControlSupport {

    @Id
    @Column(name = "id_")
    private String id;

    @ManyToOne(targetEntity = JpaResource.class)
    @JoinColumn(name = "resource_id_")
    private Resource resource;

    @ManyToOne(targetEntity = JpaPrincipal.class)
    @JoinColumn(name = "owner_id_")
    private Principal owner;

    @Column(name = "inherit_")
    private boolean inherit = true;

    @OneToMany(targetEntity = JpaAccessControlEntry.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "access_control_id_")
    private List<AccessControlEntry> entries = new ArrayList<>();

    @ManyToOne(targetEntity = JpaAccessControl.class)
    @JoinColumn(name = "parent_id_")
    private AccessControl parent;

    public JpaAccessControl(Principal owner, Resource resource) {
        this.owner = owner;
        this.resource = resource;
    }

    public static JpaAccessControl of(AccessControl acl) {
        if (acl instanceof JpaAccessControl) {
            return (JpaAccessControl) acl;
        }
        var target = new JpaAccessControl();
        BeanUtils.copyProperties(acl, target);
        return target;
    }

    private Optional<AccessControlEntry> findEntry(Principal principal) {
        return entries.stream()
                .filter(entry -> entry.getPrincipal().getType().equals(principal.getType())
                        && entry.getPrincipal().getName().equals(principal.getName()))
                .findFirst();
    }

    private AccessControlEntry getEntry(Principal principal) {
        return this.findEntry(principal).orElseGet(() -> {
            var entry = new JpaAccessControlEntry(principal);
            this.entries.add(entry);
            return entry;
        });
    }

    @Override
    public AccessControl createAccessControl(Principal owner, Resource resource) {
        var childControl = new JpaAccessControl();
        childControl.setOwner(owner);
        childControl.setResource(resource);
        childControl.setParent(this);
        return childControl;
    }

    @Override
    public void grant(String permission, Principal principal) {
        this.getEntry(principal).addPermission(permission);
    }

    @Override
    public void grant(Set<String> permissions, Principal principal) {
        this.getEntry(principal).addPermissions(permissions);
    }

    @Override
    public void revoke(String permission, Principal principal) {
        this.getEntry(principal).removePermission(permission);
    }

    @Override
    public void revoke(Set<String> permissions, Principal principal) {
        this.getEntry(principal).removePermissions(permissions);
    }

    @Override
    public void revokeAll(Principal principal) {
        this.getEntry(principal).clearPermissions();
    }

    @Override
    public boolean granted(String permission, Principal principal) {
        return this.findEntry(principal)
                .map(entry -> entry.checkPermission(permission))
                .orElse(false);
    }

    @Override
    public boolean granted(Set<String> permissions, Principal principal) {
        return this.findEntry(principal)
                .map(entry -> entry.checkAnyPermission(permissions))
                .orElse(false);
    }

    @Override
    public boolean granted(Set<String> permissions, Set<Principal> principals) {
        for (Principal principal : principals) {
            if (this.granted(permissions, principal)) {
                return true;
            }
        }
        if (this.isInherit() && Objects.nonNull(this.getParent())) {
            return this.getParent().granted(permissions, principals);
        }
        return false;
    }
}
