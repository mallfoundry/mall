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

package org.mallfoundry.autocofigure.page;

import org.mallfoundry.page.DefaultPageService;
import org.mallfoundry.page.PageIdentityProcessor;
import org.mallfoundry.page.PageProcessor;
import org.mallfoundry.page.PageViewRepository;
import org.mallfoundry.page.repository.jpa.DelegatingJpaPageViewRepository;
import org.mallfoundry.page.repository.jpa.JpaPageViewRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import java.util.List;

@Configuration
public class PageAutoConfiguration {

    @Bean
    public DelegatingJpaPageViewRepository delegatingJpaPageViewRepository(JpaPageViewRepository repository) {
        return new DelegatingJpaPageViewRepository(repository);
    }

    @Bean
    public DefaultPageService defaultPageService(@Lazy List<PageProcessor> processors,
                                                 PageViewRepository pageViewRepository) {
        var service = new DefaultPageService(pageViewRepository);
        service.setProcessors(processors);
        return service;
    }

    @Bean
    public PageIdentityProcessor pageIdentityProcessor() {
        return new PageIdentityProcessor();
    }
}
