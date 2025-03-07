package com.riosr.spring6restmvc.controller;

import com.riosr.spring6restmvc.entities.Customer;
import com.riosr.spring6restmvc.exception.NotFoundException;
import com.riosr.spring6restmvc.mappers.CustomerMapper;
import com.riosr.spring6restmvc.model.CustomerDTO;
import com.riosr.spring6restmvc.repositories.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Ricky
 * @created 2025-03-05
 */
@SpringBootTest
class CustomerControllerIntegrationTest {

    @Autowired
    CustomerController customerController;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CustomerMapper customerMapper;

    @Test
    void listCustomers() {
        List<CustomerDTO> customers = customerController.listCustomers();

        assertNotNull(customers);
        assertThat(customers.size()).isEqualTo(3);
    }

    @Test
    @Transactional
    @Rollback
    void listCustomersEmpty() {
        customerRepository.deleteAll();
        List<CustomerDTO> customers = customerController.listCustomers();

        assertThat(customers.size()).isEqualTo(0);
    }

    @Test
    void getCustomerById() {
        Customer customer = customerRepository.findAll().get(0);

        CustomerDTO dto = customerController.getCustomerById(customer.getId());

        assertNotNull(dto);
        assertThat(dto.getId()).isEqualTo(customer.getId());
    }

    @Test
    void getCustomerByIdNotFound() {
        assertThrows(NotFoundException.class, () -> {
            customerController.getCustomerById(UUID.randomUUID());
        });
    }

    @Test
    @Rollback
    @Transactional
    void createCustomer() {
        CustomerDTO dto = CustomerDTO.builder()
                .customerName("New Customer")
                .build();

        ResponseEntity<CustomerDTO> response = customerController.createCustomer(dto);

        assertThat(response.getBody()).isNotNull();
        assertThat(response.getHeaders().getLocation()).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        String[] locationUUID = response.getHeaders().getLocation().getPath().split("/");
        UUID savedUUID = UUID.fromString(locationUUID[locationUUID.length - 1]);

        Customer savedCustomer = customerRepository.findById(savedUUID).get();
        assertThat(savedCustomer.getId()).isNotNull();
    }

    @Test
    @Rollback
    @Transactional
    void updateCustomerById() {
        Customer customer = customerRepository.findAll().get(0);
        CustomerDTO dto = customerMapper.customerToCustomerDTO(customer);
        dto.setId(null);
        dto.setCustomerName(null);
        final String customerName = "UPDATED";
        dto.setCustomerName(customerName);

        ResponseEntity<CustomerDTO> response = customerController.updateCustomerById(customer.getId(), dto);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

        Customer updatedCustomer = customerRepository.findById(customer.getId()).get();
        assertThat(updatedCustomer.getCustomerName()).isEqualTo(customerName);
    }
}