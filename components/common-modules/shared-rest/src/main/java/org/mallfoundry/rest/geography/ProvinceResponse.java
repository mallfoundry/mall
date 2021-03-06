/*
 * Copyright (C) 2019-2021 the original author or authors.
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

package org.mallfoundry.rest.geography;

import lombok.Getter;
import lombok.Setter;
import org.mallfoundry.geography.Province;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ProvinceResponse {
    private String id;
    private String code;
    private String name;
    private String countryId;
    private int position;
    private List<CityResponse> cities;

    public ProvinceResponse(Province province, int scope) {
        this.id = province.getId();
        this.code = province.getCode();
        this.name = province.getName();
        this.countryId = province.getCountryId();
        this.position = province.getPosition();
        if (scope > 0) {
            this.cities = province
                    .getCities()
                    .stream()
                    .map(city -> new CityResponse(city, scope - 1))
                    .collect(Collectors.toList());
        }
    }
}
