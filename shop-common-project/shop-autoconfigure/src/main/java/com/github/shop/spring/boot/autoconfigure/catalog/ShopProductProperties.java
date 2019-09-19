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

package com.github.shop.spring.boot.autoconfigure.catalog;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "shop.product")
public class ShopProductProperties {

    @Getter
    @Setter
    private Search search;

    enum SearchType {
        LUCENE, SOLR, ELASTICSEARCH
    }

    static class Search {

        @Getter
        @Setter
        private SearchType type;

        @Getter
        @Setter
        private Lucene lucene;
    }

    static class Lucene {

        @Getter
        @Setter
        private String directory;
    }
}
