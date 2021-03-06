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

import org.mallfoundry.data.Query;
import org.mallfoundry.data.QueryBuilder;
import org.mallfoundry.identity.TenantOwnership;
import org.mallfoundry.util.ObjectBuilder;

import java.util.Set;
import java.util.function.Supplier;

public interface CouponQuery extends TenantOwnership, Query, ObjectBuilder.ToBuilder<CouponQuery.Builder> {

    String getStoreId();

    void setStoreId(String storeId);

    String getCustomerId();

    void setCustomerId(String customerId);

    String getName();

    void setName(String name);

    Set<CouponType> getTypes();

    void setTypes(Set<CouponType> types);

    Set<CouponStatus> getStatuses();

    void setStatuses(Set<CouponStatus> statuses);

    interface Builder extends QueryBuilder<CouponQuery, Builder> {

        Builder tenantId(String tenantId);

        Builder storeId(String storeId);

        Builder customerId(String customerId);

        Builder name(String name);

        Builder types(Set<CouponType> types);

        Builder types(Supplier<Set<CouponType>> supplier);

        Builder statuses(Set<CouponStatus> statuses);

        Builder statuses(Supplier<Set<CouponStatus>> supplier);
    }
}
