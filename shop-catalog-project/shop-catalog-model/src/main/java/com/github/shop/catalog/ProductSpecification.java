/*
 * Copyright 2019 the original author or authors.
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

package com.github.shop.catalog;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@JsonPropertyOrder({"id", "type", "items"})
public class ProductSpecification {

    @Getter
    @Setter
    private short id;

    @Getter
    @Setter
    private String type;

    @Getter
    @Setter
    private List<ProductSpecItem> items;

    public ProductSpecification() {
    }

    public ProductSpecification(short id, String type, List<ProductSpecItem> items) {
        this.id = id;
        this.type = type;
        this.items = items;
    }
}
