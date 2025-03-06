package com.riosr.spring6restmvc.controller;

import com.riosr.spring6restmvc.exception.NotFoundException;
import com.riosr.spring6restmvc.model.BeerDTO;
import com.riosr.spring6restmvc.model.RequestPaths;
import com.riosr.spring6restmvc.services.BeerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * Created by Ricky on 2025-01-23
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(RequestPaths.BEER_PATH)
public class BeerController {

    private final BeerService beerService;

    @RequestMapping(method = RequestMethod.GET)
    public List<BeerDTO> listBeers() {
        return beerService.getAllBeers();
    }

    @RequestMapping(method = RequestMethod.GET, path = RequestPaths.BEER_PATH_ID)
    public BeerDTO getBeerById(@PathVariable UUID beerId) {
        log.debug("Get beer by Id - in controller");

        return beerService.getBeerById(beerId).orElseThrow(NotFoundException::new);
    }

    @PostMapping
    public ResponseEntity<BeerDTO> createBeer(@RequestBody BeerDTO beer) {
        log.debug("Create beer - in controller");

        BeerDTO savedBeer = beerService.createBeer(beer);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1/beer/" + savedBeer.getId().toString());

        return new ResponseEntity<>(savedBeer, headers, HttpStatus.CREATED);
    }

    @PutMapping(RequestPaths.BEER_PATH_ID)
    public ResponseEntity<BeerDTO> updateBeerById(@PathVariable UUID beerId, @RequestBody BeerDTO beer) {
        log.debug("Update beer by - in controller");

        beerService.updateBeerById(beerId, beer);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(RequestPaths.BEER_PATH_ID)
    public ResponseEntity<BeerDTO> deleteBeerById(@PathVariable UUID beerId) {
        log.debug("Delete beer by - in controller");

        beerService.deleteBeerById(beerId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping(RequestPaths.BEER_PATH_ID)
    public ResponseEntity<BeerDTO> patchBeerById(@PathVariable UUID beerId, @RequestBody BeerDTO beer) {
        log.debug("Patch beer by - in controller");

        beerService.patchBeerById(beerId, beer);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
