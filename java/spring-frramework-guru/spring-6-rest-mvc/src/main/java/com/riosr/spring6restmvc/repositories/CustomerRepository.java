package com.riosr.spring6restmvc.repositories;

import com.riosr.spring6restmvc.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * @author Ricky
 * @created 2025-02-13
 */
public interface CustomerRepository extends JpaRepository<Customer, UUID> {
}
