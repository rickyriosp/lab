package com.riosr.spring6restmvc.services;

import com.riosr.spring6restmvc.model.BeerDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Created by Ricky on 2025-01-23
 */
public interface BeerService {

    List<BeerDTO> getAllBeers();

    Optional<BeerDTO> getBeerById(UUID id);

    BeerDTO createBeer(BeerDTO beer);

    Optional<BeerDTO> updateBeerById(UUID beerId, BeerDTO beer);

    boolean deleteBeerById(UUID beerId);

    Optional<BeerDTO> patchBeerById(UUID beerId, BeerDTO beer);
}
