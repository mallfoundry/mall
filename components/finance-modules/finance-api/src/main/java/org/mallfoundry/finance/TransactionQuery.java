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

import java.util.Set;
import java.util.function.Supplier;

public interface TransactionQuery extends Query, ObjectBuilder.ToBuilder<TransactionQuery.Builder> {

    String getAccountId();

    void setAccountId(String accountId);

    Set<TransactionType> getTypes();

    void setTypes(Set<TransactionType> types);

    Set<TransactionStatus> getStatuses();

    void setStatuses(Set<TransactionStatus> statuses);

    interface Builder extends QueryBuilder<TransactionQuery, Builder> {

        Builder accountId(String accountId);

        Builder types(Set<TransactionType> types);

        Builder types(Supplier<Set<TransactionType>> supplier);

        Builder statuses(Set<TransactionStatus> statuses);

        Builder statuses(Supplier<Set<TransactionStatus>> supplier);
    }
}
