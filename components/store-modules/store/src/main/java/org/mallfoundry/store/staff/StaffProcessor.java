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

package org.mallfoundry.store.staff;

public interface StaffProcessor {

    default Staff preProcessBeforeAddStaff(Staff staff) {
        return staff;
    }

    default Staff preProcessAfterAddStaff(Staff staff) {
        return staff;
    }

    default Staff postProcessAfterGetStaff(Staff staff) {
        return staff;
    }

    default StaffQuery preProcessBeforeGetStaffs(StaffQuery query) {
        return query;
    }

    default StaffQuery preProcessBeforeCountStaffs(StaffQuery query) {
        return query;
    }

    default Staff preProcessBeforeUpdateStaff(Staff staff) {
        return staff;
    }

    default Staff preProcessAfterUpdateStaff(Staff staff) {
        return staff;
    }

    default Staff preProcessBeforeActiveStaff(Staff staff) {
        return staff;
    }

    default Staff preProcessBeforeInactiveStaff(Staff staff) {
        return staff;
    }

    default Staff preProcessBeforeDeleteStaff(Staff staff) {
        return staff;
    }

    default Staff preProcessAfterDeleteStaff(Staff staff) {
        return staff;
    }

    default void preProcessAfterCompletion() {
    }
}
