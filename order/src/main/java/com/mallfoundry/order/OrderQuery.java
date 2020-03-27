/*
 * Copyright 2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mallfoundry.order;

import com.mallfoundry.data.PageLimit;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.function.Supplier;

@Getter
@Setter
public class OrderQuery extends PageLimit {

    private String title;

    private List<OrderStatus> statuses;

    private String storeId;

    private String customerId;

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private OrderQuery query;

        public Builder() {
            this.query = new OrderQuery();
        }

        public Builder page(int page) {
            this.query.setPage(page);
            return this;
        }

        public Builder limit(int limit) {
            this.query.setLimit(limit);
            return this;
        }

        public Builder customerId(String customerId) {
            this.query.setCustomerId(customerId);
            return this;
        }

        public Builder storeId(String storeId) {
            this.query.setStoreId(storeId);
            return this;
        }

        public Builder statuses(Supplier<List<OrderStatus>> supplier) {
            return this.statuses(supplier.get());
        }

        public Builder statuses(List<OrderStatus> statuses) {
            this.query.setStatuses(statuses);
            return this;
        }

        public OrderQuery build() {
            return this.query;
        }
    }
}
