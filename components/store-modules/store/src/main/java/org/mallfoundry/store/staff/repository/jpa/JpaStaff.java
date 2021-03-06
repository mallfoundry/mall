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

package org.mallfoundry.store.staff.repository.jpa;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.mallfoundry.store.security.Role;
import org.mallfoundry.store.security.repository.jpa.JpaRole;
import org.mallfoundry.store.staff.Staff;
import org.mallfoundry.store.staff.StaffId;
import org.springframework.beans.BeanUtils;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@IdClass(JpaStaffId.class)
@Table(name = "mf_store_staff")
public class JpaStaff extends JpaStaffBase {

    @NotNull
    @ManyToMany(targetEntity = JpaRole.class, fetch = FetchType.EAGER)
    @JoinTable(name = "mf_store_staff_role",
            joinColumns = {@JoinColumn(name = "staff_id_", referencedColumnName = "id_"),
                    @JoinColumn(name = "store_id_", referencedColumnName = "store_id_")},
            inverseJoinColumns = {@JoinColumn(name = "role_id_", referencedColumnName = "id_")})
    @OrderBy("createdTime")
    private List<Role> roles = new ArrayList<>();

    public JpaStaff(StaffId staffId) {
        this.setTenantId(staffId.getTenantId());
        this.setStoreId(staffId.getStoreId());
        this.setId(staffId.getId());
    }

    public static JpaStaff of(Staff staff) {
        if (staff instanceof JpaStaff) {
            return (JpaStaff) staff;
        }
        var target = new JpaStaff();
        BeanUtils.copyProperties(staff, target);
        return target;
    }
}
