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

package org.mallfoundry.autoconfigure.finance;

import org.mallfoundry.finance.DefaultTransactionService;
import org.mallfoundry.finance.TransactionIdentityProcessor;
import org.mallfoundry.finance.TransactionProcessor;
import org.mallfoundry.finance.TransactionRepository;
import org.mallfoundry.finance.repository.jpa.DelegatingJpaTransactionRepository;
import org.mallfoundry.finance.repository.jpa.JpaTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;

import java.util.List;

public class TransactionConfiguration {

    @Bean
    public DelegatingJpaTransactionRepository delegatingJpaTransactionRepository(JpaTransactionRepository repository) {
        return new DelegatingJpaTransactionRepository(repository);
    }

    @Bean
    public DefaultTransactionService defaultTransactionService(@Autowired(required = false)
                                                               @Lazy List<TransactionProcessor> processors,
                                                               TransactionRepository transactionRepository) {
        var transaction = new DefaultTransactionService(transactionRepository);
        transaction.setProcessors(processors);
        return transaction;
    }

    @Bean
    public TransactionIdentityProcessor transactionIdentityProcessor() {
        return new TransactionIdentityProcessor();
    }
}
