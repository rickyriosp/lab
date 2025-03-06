package com.riosr.spring6restmvc.mappers;

import com.riosr.spring6restmvc.entities.Customer;
import com.riosr.spring6restmvc.model.CustomerDTO;
import org.mapstruct.Mapper;

/**
 * @author Ricky
 * @created 2025-02-13
 */
@Mapper
public interface CustomerMapper {

    Customer customerDtoToCustomer(CustomerDTO customerDTO);
    CustomerDTO customerToCustomerDTO(Customer customer);
}
