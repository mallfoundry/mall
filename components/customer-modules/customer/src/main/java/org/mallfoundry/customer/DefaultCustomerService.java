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

package org.mallfoundry.customer;

import org.mallfoundry.identity.User;
import org.mallfoundry.keygen.PrimaryKeyHolder;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class DefaultCustomerService implements CustomerService, CustomerProcessorInvoker, ApplicationEventPublisherAware {

    private static final String ADDRESS_ID_VALUE_NAME = "customer.address.id";

    private final CustomerRepository customerRepository;

    private ApplicationEventPublisher eventPublisher;

    public DefaultCustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @Override
    public CustomerId createCustomerId(String tenantId, String customerId) {
        return new ImmutableCustomerId(tenantId, customerId);
    }

    @Override
    public Customer createCustomer(User user) {
        return this.customerRepository.create(user);
    }

    @Override
    public Customer createCustomer(CustomerId customerId) {
        return this.customerRepository.create(customerId);
    }

    @Override
    public Optional<Customer> findCustomer(String customerId) {
        return this.customerRepository.findById(customerId)
                .map(this::invokePostProcessAfterGetCustomer);
    }

    @Transactional
    @Override
    public Customer addCustomer(Customer customer) {
        customer = this.invokePreProcessBeforeAddCustomer(customer);
        customer = this.customerRepository.save(customer);
        this.eventPublisher.publishEvent(new ImmutableCustomerAddedEvent(customer));
        return customer;
    }

    public Customer requiredCustomer(String customerId) {
        return this.findCustomer(customerId).orElseThrow();
    }

    @Transactional
    @Override
    public Customer updateCustomer(Customer customer) {
        var savedCustomer = this.requiredCustomer(customer.getId());
        if (Objects.nonNull(customer.getNickname())) {
            savedCustomer.setNickname(customer.getNickname());
        }
        if (Objects.nonNull(customer.getGender())) {
            savedCustomer.setGender(customer.getGender());
        }
        if (Objects.nonNull(customer.getBirthdate())) {
            savedCustomer.setBirthdate(customer.getBirthdate());
        }
        return this.customerRepository.save(savedCustomer);
    }

    @Transactional
    @Override
    public void deleteCustomer(String customerId) {
        var customer = this.requiredCustomer(customerId);
        this.customerRepository.delete(customer);
    }

    @Transactional
    @Override
    public CustomerAddress addCustomerAddress(String customerId, CustomerAddress address) {
        address.setId(PrimaryKeyHolder.next(ADDRESS_ID_VALUE_NAME));
        this.requiredCustomer(customerId).addAddress(address);
        return address;
    }

    @Transactional
    @Override
    public List<CustomerAddress> getCustomerAddresses(String customerId) {
        return this.requiredCustomer(customerId).getAddresses();
    }

    @Override
    public Optional<CustomerAddress> findCustomerAddress(String customerId, String addressId) {
        return this.requiredCustomer(customerId).getAddress(addressId);
    }

    @Override
    public CustomerAddress getDefaultCustomerAddress(String customerId) {
        return this.findDefaultCustomerAddress(customerId).orElseThrow();
    }

    @Override
    public Optional<CustomerAddress> findDefaultCustomerAddress(String customerId) {
        return this.requiredCustomer(customerId).getDefaultAddress();
    }

    @Transactional
    @Override
    public void updateCustomerAddress(String customerId, CustomerAddress newAddress) {
        this.requiredCustomer(customerId).updateAddress(newAddress);
    }

    private CustomerAddress requiredCustomerAddress(Customer customer, String addressId) {
        return customer.getAddress(addressId).orElseThrow();
    }

    @Transactional
    @Override
    public void removeCustomerAddress(String customerId, String addressId) {
        var customer = this.requiredCustomer(customerId);
        customer.removeAddress(this.requiredCustomerAddress(customer, addressId));
    }

    @Override
    public Customer invokePreProcessBeforeAddCustomer(Customer customer) {
        return customer;
    }

    @Override
    public Customer invokePreProcessBeforeUpdateCustomer(Customer customer) {
        return customer;
    }

    @Override
    public Customer invokePreProcessBeforeDeleteCustomer(Customer customer) {
        return customer;
    }

    @Override
    public Customer invokePostProcessAfterGetCustomer(Customer customer) {
        return customer;
    }

    @Override
    public CustomerAddress invokePreProcessBeforeAddCustomerAddress(Customer customer, CustomerAddress address) {
        return address;
    }

    @Override
    public CustomerAddress invokePreProcessBeforeUpdateCustomerAddress(Customer customer, CustomerAddress address) {
        return address;
    }

    @Override
    public CustomerAddress invokePostProcessAfterGetCustomerAddress(Customer customer, CustomerAddress address) {
        return address;
    }

    @Override
    public CustomerAddress invokePostProcessAfterGetDefaultCustomerAddress(Customer customer, CustomerAddress address) {
        return address;
    }

    @Override
    public List<CustomerAddress> invokePostProcessAfterGetDefaultCustomerAddresses(Customer customer, List<CustomerAddress> addresses) {
        return addresses;
    }

    @Override
    public CustomerAddress invokePreProcessBeforeRemoveCustomerAddress(Customer customer, CustomerAddress address) {
        return address;
    }
}
