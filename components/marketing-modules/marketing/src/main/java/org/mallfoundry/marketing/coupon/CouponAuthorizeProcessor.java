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

package org.mallfoundry.marketing.coupon;

public class CouponAuthorizeProcessor implements CouponProcessor {

    //    @PreAuthorize("hasPermission(#coupon.storeId, '" + Resource.STORE_TYPE + "', '"
//            + AllAuthorities.COUPON_CREATE + ","
//            + AllAuthorities.COUPON_MANAGE + ","
//            + AllAuthorities.STORE_MANAGE + "')")
    @Override
    public Coupon preProcessBeforeAddCoupon(Coupon coupon) {
        return coupon;
    }

    //    @PreAuthorize("hasPermission(#receiveCoupon.customerId, '" + Resource.CUSTOMER_TYPE + "', '"
//            + AllAuthorities.COUPON_RECEIVE + ","
//            + AllAuthorities.CUSTOMER_MANAGE + "')")
    @Override
    public TakeCoupon preProcessBeforeTakeCoupon(Coupon coupon, TakeCoupon takeCoupon) {
        return takeCoupon;
    }
}
