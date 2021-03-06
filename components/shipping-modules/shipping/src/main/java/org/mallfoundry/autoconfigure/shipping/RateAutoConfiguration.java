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

package org.mallfoundry.autoconfigure.shipping;

import org.mallfoundry.shipping.rate.DefaultRateService;
import org.mallfoundry.shipping.rate.RateAuthorizer;
import org.mallfoundry.shipping.rate.RateIdentifier;
import org.mallfoundry.shipping.rate.RateProcessor;
import org.mallfoundry.shipping.rate.RateProcessorsInvoker;
import org.mallfoundry.shipping.rate.RateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import java.util.List;

@Configuration
public class RateAutoConfiguration {

    @Autowired(required = false)
    @Bean
    public RateProcessorsInvoker rateProcessorsInvoker(@Lazy List<RateProcessor> processors) {
        return new RateProcessorsInvoker(processors);
    }

    @Bean
    public DefaultRateService defaultRateService(RateProcessorsInvoker processorsInvoker, RateRepository rateRepository) {
        return new DefaultRateService(processorsInvoker, rateRepository);
    }

    @Bean
    public RateIdentifier rateIdentifier() {
        return new RateIdentifier();
    }

    @Bean
    public RateAuthorizer rateAuthorizer() {
        return new RateAuthorizer();
    }
}
