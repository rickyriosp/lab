package com.riosr.spring6restmvc.services;

import com.riosr.spring6restmvc.model.CustomerDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.*;

/**
 * Created by Ricky on 2025-01-27
 */
@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

    private final Map<UUID, CustomerDTO> customers;

    public CustomerServiceImpl() {
        this.customers = new HashMap<>();

        CustomerDTO customer1 = CustomerDTO.builder()
                .id(UUID.randomUUID())
                .version(1)
                .customerName("Customer 1")
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();

        CustomerDTO customer2 = CustomerDTO.builder()
                .id(UUID.randomUUID())
                .version(1)
                .customerName("Customer 2")
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();

        CustomerDTO customer3 = CustomerDTO.builder()
                .id(UUID.randomUUID())
                .version(1)
                .customerName("Customer 3")
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();

        this.customers.put(customer1.getId(), customer1);
        this.customers.put(customer2.getId(), customer2);
        this.customers.put(customer3.getId(), customer3);
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return new ArrayList<>(this.customers.values());
    }

    @Override
    public Optional<CustomerDTO> getCustomerById(UUID id) {
        log.debug("Get customer by Id - in service. Id: {}", id);

        return Optional.ofNullable(this.customers.get(id));
    }

    @Override
    public CustomerDTO createCustomer(CustomerDTO customer) {
        log.debug("Create customer - in service. Customer Id: {}", customer.getId());

        CustomerDTO savedCustomer = CustomerDTO.builder()
                .id(UUID.randomUUID())
                .version(customer.getVersion())
                .customerName(customer.getCustomerName())
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();

        this.customers.put(savedCustomer.getId(), savedCustomer);

        return savedCustomer;
    }

    @Override
    public void updateCustomerById(UUID customerId, CustomerDTO customer) {
        log.debug("Update customer - in service. Customer Id: {}", customer.getId());

        CustomerDTO existingCustomer = this.customers.get(customerId);
        existingCustomer.setCustomerName(customer.getCustomerName());
        existingCustomer.setUpdatedDate(LocalDateTime.now());

        this.customers.put(customerId, existingCustomer);
    }

    @Override
    public void deleteCustomerById(UUID customerId) {
        log.debug("Delete customer - in service. Customer Id: {}", customerId);

        this.customers.remove(customerId);
    }

    @Override
    public void patchCustomerById(UUID customerId, CustomerDTO customer) {
        log.debug("Patch customer - in service. Customer Id: {}", customer.getId());

        CustomerDTO existingCustomer = this.customers.get(customerId);

        if (StringUtils.hasText(customer.getCustomerName())) {
            existingCustomer.setCustomerName(customer.getCustomerName());
        }

        existingCustomer.setUpdatedDate(LocalDateTime.now());
        this.customers.put(customerId, existingCustomer);
    }
}
