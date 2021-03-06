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

import org.mallfoundry.store.staff.Staff;

import java.util.List;

public interface RoleProcessor {

    default Role preProcessBeforeAddRole(Role role) {
        return role;
    }

    default Role preProcessAfterAddRole(Role role) {
        return role;
    }

    default Role preProcessBeforeUpdateRole(Role role) {
        return role;
    }

    default Role preProcessAfterUpdateRole(Role role) {
        return role;
    }

    default Staff preProcessBeforeAddRoleStaff(Role role, Staff staff) {
        return staff;
    }

    default Staff preProcessBeforeRemoveRoleStaff(Role role, Staff staff) {
        return staff;
    }

    default Role preProcessBeforeDeleteRole(Role role) {
        return role;
    }

    default Role preProcessAfterDeleteRole(Role role) {
        return role;
    }

    default List<Role> preProcessBeforeClearRoles(List<Role> roles) {
        return roles;
    }

    default List<Role> preProcessAfterClearRoles(List<Role> roles) {
        return roles;
    }
}
