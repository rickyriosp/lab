package com.riosr.sq_ch10.controllers;

import com.riosr.sq_ch10.models.Country;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CountryController {

    @GetMapping("/all")
    public List<Country> countries() {
        Country c1 = Country.of("Spain", 34);
        Country c2 = Country.of("Germany", 23);

        return List.of(c1, c2);
    }

    @GetMapping("/spain")
    public ResponseEntity<Country> spain() {
        Country c = Country.of("Spain", 34);

        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .header("continent", "Europe")
                .header("capital", "Madrid")
                .header("favorite_food", "jamon")
                .body(c);
    }
}
