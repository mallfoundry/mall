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

package org.mallfoundry.geography.repository.jpa;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.mallfoundry.geography.County;
import org.mallfoundry.geography.CountySupport;
import org.springframework.beans.BeanUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "mf_district_county")
public class JpaCounty extends CountySupport implements County {

    @Id
    @Column(name = "id_")
    private String id;

    @Column(name = "code_")
    private String code;

    @Column(name = "name_")
    private String name;

    @Column(name = "position_")
    private int position;

    @Column(name = "city_id_")
    private String cityId;

    public JpaCounty(String id) {
        this.setId(id);
    }

    public JpaCounty(String code, String name, String cityId) {
        this.setCode(code);
        this.setName(name);
        this.cityId = cityId;
    }

    public static JpaCounty of(County county) {
        if (county instanceof JpaCounty) {
            return (JpaCounty) county;
        }
        var target = new JpaCounty();
        BeanUtils.copyProperties(county, target);
        return target;
    }
}
