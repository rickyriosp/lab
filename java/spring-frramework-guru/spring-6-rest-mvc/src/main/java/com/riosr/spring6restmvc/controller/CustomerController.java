package com.riosr.spring6restmvc.controller;

import com.riosr.spring6restmvc.exception.NotFoundException;
import com.riosr.spring6restmvc.model.CustomerDTO;
import com.riosr.spring6restmvc.model.RequestPaths;
import com.riosr.spring6restmvc.services.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * Created by Ricky on 2025-01-27
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(RequestPaths.CUSTOMERS_PATH)
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public List<CustomerDTO> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping(RequestPaths.CUSTOMERS_PATH_ID)
    public CustomerDTO getCustomerById(@PathVariable("customerId") UUID customerId) {
        log.debug("Get customer by Id - in controller");

        return customerService.getCustomerById(customerId).orElseThrow(NotFoundException::new);
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO customer) {
        log.debug("Create customer - in controller");

        CustomerDTO savedCustomer = customerService.createCustomer(customer);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1/customers/" + savedCustomer.getId().toString());

        return new ResponseEntity<>(savedCustomer, headers, HttpStatus.CREATED);
    }

    @PutMapping(RequestPaths.CUSTOMERS_PATH_ID)
    public ResponseEntity updateCustomerById(@PathVariable UUID customerId, @RequestBody CustomerDTO customer) {
        log.debug("Update customer - in controller");

        customerService.updateCustomerById(customerId, customer);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(RequestPaths.CUSTOMERS_PATH_ID)
    public ResponseEntity<CustomerDTO> deleteCustomerById(@PathVariable UUID customerId) {
        log.debug("Delete customer - in controller");

        customerService.deleteCustomerById(customerId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping(RequestPaths.CUSTOMERS_PATH_ID)
    public ResponseEntity<CustomerDTO> patchCustomerById(@PathVariable UUID customerId, @RequestBody CustomerDTO customer) {
        log.debug("Patch customer - in controller");

        customerService.patchCustomerById(customerId, customer);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
