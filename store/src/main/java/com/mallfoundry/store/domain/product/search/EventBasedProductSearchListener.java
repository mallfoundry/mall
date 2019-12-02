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

package com.mallfoundry.store.domain.product.search;


import com.mallfoundry.store.domain.product.ProductAddedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class EventBasedProductSearchListener {

    private final ProductSearchProvider productSearchProvider;

    public EventBasedProductSearchListener(ProductSearchProvider productSearchProvider) {
        this.productSearchProvider = productSearchProvider;
    }

    @EventListener(ProductAddedEvent.class)
    public void onAddProduct(ProductAddedEvent event) {
        this.productSearchProvider.add(event.getProduct());
    }
}
