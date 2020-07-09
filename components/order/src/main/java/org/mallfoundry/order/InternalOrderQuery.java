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

package org.mallfoundry.order;

import lombok.Getter;
import lombok.Setter;
import org.mallfoundry.data.QuerySupport;
import org.mallfoundry.payment.methods.PaymentMethod;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
public class InternalOrderQuery extends QuerySupport implements OrderQuery {

    private Set<String> ids;

    private String name;

    private Set<OrderStatus> statuses;

    private Set<OrderType> types;

    private Set<PaymentMethod> paymentMethods;

    private Set<OrderSource> sources;

    private String storeId;

    private String customerId;

    private Date minPlacedTime;

    private Date maxPlacedTime;
}
