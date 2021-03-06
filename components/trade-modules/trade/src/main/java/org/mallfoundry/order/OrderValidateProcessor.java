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
import org.mallfoundry.validation.ValidationHolder;
import org.springframework.core.Ordered;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@org.springframework.core.annotation.Order(Ordered.HIGHEST_PRECEDENCE + 200)
public class OrderValidateProcessor implements OrderProcessor {

    private void validate(List<Order> orders) {
        var target = new PlaceOrders(orders);
        ValidationHolder.validate(target, "place");
    }

    @Override
    public List<Order> preProcessAfterPlaceOrders(List<Order> orders) {
        this.validate(orders);
        return orders;
    }

    @Getter
    @Setter
    private static class PlaceOrders {

        @Valid
        @NotEmpty
        private List<Order> orders;

        PlaceOrders(List<Order> orders) {
            this.orders = orders;
        }
    }
}
