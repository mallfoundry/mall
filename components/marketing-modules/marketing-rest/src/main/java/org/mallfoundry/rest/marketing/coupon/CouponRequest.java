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

package org.mallfoundry.rest.marketing.coupon;

import lombok.Getter;
import lombok.Setter;
import org.mallfoundry.marketing.coupon.Coupon;
import org.mallfoundry.marketing.coupon.CouponType;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class CouponRequest {
    private String storeId;
    private String code;
    private String name;
    private String description;
    private int issuingCount;
    public CouponType type;
    public BigDecimal discountAmount;
    public BigDecimal discountPercent;
    public BigDecimal discountMinAmount;
    public BigDecimal discountMaxAmount;
    public BigDecimal minAmount;
    public BigDecimal maxAmount;
    public Date startTime;
    public Date endTime;

    public Coupon assignTo(Coupon coupon) {
        return coupon.toBuilder()
                .storeId(this.storeId)
                .code(this.code).name(this.name).description(this.description)
                .issuingCount(this.issuingCount)
                .type(this.type)
                .discountAmount(this.discountAmount).discountPercent(this.discountPercent)
                .discountMinAmount(this.discountMinAmount).discountMaxAmount(this.discountMaxAmount)
                .minAmount(this.minAmount).maxAmount(this.maxAmount)
                .startTime(this.startTime).endTime(this.endTime)
                .build();
    }
}
