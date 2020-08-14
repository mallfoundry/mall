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

package org.mallfoundry.autoconfigure.catalog;

import org.mallfoundry.catalog.collection.DefaultProductCollectionService;
import org.mallfoundry.catalog.collection.ProductCollectionAuthorizeProcessor;
import org.mallfoundry.catalog.collection.ProductCollectionIdentityProcessor;
import org.mallfoundry.catalog.collection.ProductCollectionProcessor;
import org.mallfoundry.catalog.collection.ProductCollectionRepository;
import org.mallfoundry.catalog.collection.ProductCollectionService;
import org.mallfoundry.catalog.collection.repository.ProductCollectionProductsCountProcessor;
import org.mallfoundry.catalog.collection.repository.jpa.DelegatingJpaProductCollectionRepository;
import org.mallfoundry.catalog.collection.repository.jpa.JpaProductCollectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import java.util.List;

@Configuration
public class ProductCollectionConfiguration {

    @Bean
    public DelegatingJpaProductCollectionRepository delegatingJpaProductCollectionRepository(JpaProductCollectionRepository repositoryDelegate) {
        return new DelegatingJpaProductCollectionRepository(repositoryDelegate);
    }

    @Bean
    public DefaultProductCollectionService defaultProductCollectionService(@Autowired(required = false)
                                                                           @Lazy List<ProductCollectionProcessor> processors,
                                                                           ProductCollectionRepository repository) {
        var service = new DefaultProductCollectionService(repository);
        service.setProcessors(processors);
        return service;
    }

    @Bean
    public ProductCollectionIdentityProcessor productCollectionIdentityProcessor() {
        return new ProductCollectionIdentityProcessor();
    }

    @Bean
    public ProductCollectionAuthorizeProcessor productCollectionAuthorizeProcessor() {
        return new ProductCollectionAuthorizeProcessor();
    }

    @Bean
    public ProductCollectionProductsCountProcessor productCollectionProductsCountProcessor(ProductCollectionService collectionService) {
        return new ProductCollectionProductsCountProcessor(collectionService);
    }
}