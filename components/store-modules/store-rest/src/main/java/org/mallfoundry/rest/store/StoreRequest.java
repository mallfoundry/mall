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

package org.mallfoundry.rest.store;

import lombok.Getter;
import lombok.Setter;
import org.mallfoundry.store.Store;

@Getter
@Setter
public class StoreRequest {

    private String id;

    private String name;

    private String domain;

    private String logo;

    private String industry;

    private String description;

    private String countryCode;

    private String phone;

    private String zip;

    private String provinceId;

    private String province;

    private String cityId;

    private String city;

    private String countyId;

    private String county;

    private String address;

    public Store assignToStore(Store store) {
        return store.toBuilder()
                .id(this.id).name(this.name).logo(this.logo)
                .industry(this.industry).description(this.description)
                .countryCode(this.countryCode).phone(this.phone).zip(this.zip)
                .provinceId(this.provinceId).province(this.province)
                .cityId(this.cityId).city(this.city)
                .countyId(this.countyId).county(this.county)
                .address(this.address)
                .build();
    }
}
