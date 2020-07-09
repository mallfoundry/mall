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

package org.mallfoundry.district;

import java.util.List;
import java.util.Optional;

public interface DistrictService {

    DistrictQuery createQuery();

    Region createRegion(String code, String name, String countryId);

    Province createProvince(String code, String name, String countryId);

    City createCity(String code, String name, String provinceId);

    County createCounty(String code, String name, String cityId);

    Region addRegion(Region region);

    Province addProvince(Province province);

    City addCity(City city);

    County addCounty(County county);

    List<Region> getRegions(DistrictQuery query);

    List<Province> getProvinces(DistrictQuery query);

    List<City> getCities(DistrictQuery query);

    List<County> getCounties(DistrictQuery query);

    Optional<County> getCounty(String id);
}
