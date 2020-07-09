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

package org.mallfoundry.shipping;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Tang Zhi
 * @since 1.0
 */
@Getter
@Setter
public class DefaultAddress extends AddressSupport {

    private String id;

    private String firstName;

    private String lastName;

    private String countryCode;

    private String mobile;

    private String zip;

    private String provinceId;

    private String province;

    private String cityId;

    private String city;

    private String countyId;

    private String county;

    private String address;
}
