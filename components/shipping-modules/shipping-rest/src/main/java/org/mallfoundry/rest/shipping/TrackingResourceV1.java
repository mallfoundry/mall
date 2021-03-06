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

package org.mallfoundry.rest.shipping;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.lang3.StringUtils;
import org.mallfoundry.shipping.CarrierCode;
import org.mallfoundry.shipping.tracking.Track;
import org.mallfoundry.shipping.tracking.TrackService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Tracking")
@RequestMapping("/v1")
@RestController
public class TrackingResourceV1 {

    private final TrackService trackService;

    public TrackingResourceV1(TrackService trackService) {
        this.trackService = trackService;
    }

    @GetMapping("/carriers/{carrier_code}/tracks/{tracking_number}")
    public Track getTrack(@PathVariable("carrier_code") String carrierCode,
                          @PathVariable("tracking_number") String trackingNumber) {
        return this.trackService.getTrack(CarrierCode.valueOf(StringUtils.upperCase(carrierCode)), trackingNumber);
    }
}
