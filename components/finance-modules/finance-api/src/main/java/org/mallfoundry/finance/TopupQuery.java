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

package org.mallfoundry.finance;

import org.mallfoundry.data.Query;
import org.mallfoundry.data.QueryBuilder;
import org.mallfoundry.util.ObjectBuilder;

import java.util.Date;
import java.util.Set;
import java.util.function.Supplier;

public interface TopupQuery extends Query, ObjectBuilder.ToBuilder<TopupQuery.Builder> {

    String getAccountId();

    void setAccountId(String accountId);

    Set<TopupStatus> getStatuses();

    void setStatuses(Set<TopupStatus> statuses);

    Set<PaymentMethodType> getSourceTypes();

    void setSourceTypes(Set<PaymentMethodType> sourceTypes);

    Date getCreatedTimeStart();

    void setCreatedTimeStart(Date createdTimeStart);

    Date getCreatedTimeEnd();

    void setCreatedTimeEnd(Date createdTimeEnd);

    interface Builder extends QueryBuilder<TopupQuery, Builder> {

        Builder accountId(String accountId);

        Builder statuses(Set<TopupStatus> statuses);

        Builder statuses(Supplier<Set<TopupStatus>> supplier);

        Builder sourceTypes(Set<PaymentMethodType> sourceTypes);

        Builder sourceTypes(Supplier<Set<PaymentMethodType>> supplier);

        Builder createdTimeStart(Date createdTimeStart);

        Builder createdTimeEnd(Date createdTimeEnd);
    }
}
