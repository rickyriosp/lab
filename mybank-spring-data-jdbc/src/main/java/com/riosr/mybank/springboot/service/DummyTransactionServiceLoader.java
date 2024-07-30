package com.riosr.mybank.springboot.service;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Profile("dev")
public class DummyTransactionServiceLoader {

    private final TransactionService transactionService;

    public DummyTransactionServiceLoader(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostConstruct
    public void setup() {
        System.out.println("Creating dev transactions...");
        transactionService.create(BigDecimal.valueOf(10.0), "eating out at mcdonalds", "ricky");
        transactionService.create(BigDecimal.valueOf(20.0), "eating out at burger king", "ricky");
    }
}
