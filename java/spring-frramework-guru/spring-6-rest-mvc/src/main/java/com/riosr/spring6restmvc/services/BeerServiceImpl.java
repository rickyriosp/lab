package com.riosr.spring6restmvc.services;

import com.riosr.spring6restmvc.model.BeerDTO;
import com.riosr.spring6restmvc.model.BeerStyle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Created by Ricky on 2025-01-23
 */
@Slf4j
@Service
public class BeerServiceImpl implements BeerService {

    private final Map<UUID, BeerDTO> beerMap;

    public BeerServiceImpl() {
        this.beerMap = new HashMap<>();

        BeerDTO beer1 = BeerDTO.builder()
                .id(UUID.randomUUID())
                .version(1)
                .beerName("Galaxy Cat")
                .beerStyle(BeerStyle.PALE_ALE)
                .upc("123456")
                .price(new BigDecimal("12.99"))
                .quantityOnHand(122)
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();

        BeerDTO beer2 = BeerDTO.builder()
                .id(UUID.randomUUID())
                .version(1)
                .beerName("Crank")
                .beerStyle(BeerStyle.PALE_ALE)
                .upc("12345622")
                .price(new BigDecimal("11.99"))
                .quantityOnHand(392)
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();

        BeerDTO beer3 = BeerDTO.builder()
                .id(UUID.randomUUID())
                .version(1)
                .beerName("Sunshine City")
                .beerStyle(BeerStyle.IPA)
                .upc("123456")
                .price(new BigDecimal("13.99"))
                .quantityOnHand(144)
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();

        this.beerMap.put(beer1.getId(), beer1);
        this.beerMap.put(beer2.getId(), beer2);
        this.beerMap.put(beer3.getId(), beer3);
    }

    @Override
    public List<BeerDTO> getAllBeers() {
        return new ArrayList<>(this.beerMap.values());
    }

    @Override
    public Optional<BeerDTO> getBeerById(UUID id) {
        log.debug("Get beer by Id - in service. Id: {}", id);

        return Optional.of(beerMap.get(id));
    }

    @Override
    public BeerDTO createBeer(BeerDTO beer) {
        log.debug("Create beer - in service. Id: {}", beer.getId());

        BeerDTO savedBeer = BeerDTO.builder()
                .id(UUID.randomUUID())
                .version(beer.getVersion())
                .beerName(beer.getBeerName())
                .beerStyle(beer.getBeerStyle())
                .upc(beer.getUpc())
                .price(beer.getPrice())
                .quantityOnHand(beer.getQuantityOnHand())
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();

        this.beerMap.put(savedBeer.getId(), savedBeer);

        return savedBeer;
    }

    @Override
    public Optional<BeerDTO> updateBeerById(UUID beerId, BeerDTO beer) {
        log.debug("Update beer - in service. Id: {}", beerId);

        BeerDTO existingBeer = this.beerMap.get(beerId);
        existingBeer.setBeerName(beer.getBeerName());
        existingBeer.setBeerStyle(beer.getBeerStyle());
        existingBeer.setUpc(beer.getUpc());
        existingBeer.setPrice(beer.getPrice());
        existingBeer.setQuantityOnHand(beer.getQuantityOnHand());
        existingBeer.setUpdatedDate(LocalDateTime.now());

        this.beerMap.put(beerId, existingBeer);
        return Optional.of(existingBeer);
    }

    @Override
    public boolean deleteBeerById(UUID beerId) {
        log.debug("Delete beer - in service. Id: {}", beerId);

        this.beerMap.remove(beerId);

        return true;
    }

    @Override
    public Optional<BeerDTO> patchBeerById(UUID beerId, BeerDTO beer) {
        log.debug("Patch beer - in service. Id: {}", beerId);

        BeerDTO existingBeer = this.beerMap.get(beerId);

        if (StringUtils.hasText(beer.getBeerName())) {
            existingBeer.setBeerName(beer.getBeerName());
        }
        if (beer.getBeerStyle() != null) {
            existingBeer.setBeerStyle(beer.getBeerStyle());
        }
        if (StringUtils.hasText(beer.getUpc())) {
            existingBeer.setUpc(beer.getUpc());
        }
        if (beer.getPrice() != null) {
            existingBeer.setPrice(beer.getPrice());
        }
        if (beer.getQuantityOnHand() != null) {
            existingBeer.setQuantityOnHand(beer.getQuantityOnHand());
        }

        existingBeer.setUpdatedDate(LocalDateTime.now());
        this.beerMap.put(beerId, existingBeer);

        return Optional.of(existingBeer);
    }
}
