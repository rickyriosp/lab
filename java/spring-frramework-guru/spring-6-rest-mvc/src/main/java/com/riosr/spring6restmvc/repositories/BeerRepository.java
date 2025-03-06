package com.riosr.spring6restmvc.repositories;

import com.riosr.spring6restmvc.entities.Beer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * @author Ricky
 * @created 2025-02-13
 */
public interface BeerRepository extends JpaRepository<Beer, UUID> {
}
