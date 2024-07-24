package com.riosr.mybank.springboot.web;

import com.riosr.mybank.springboot.dto.TransactionDto;
import com.riosr.mybank.springboot.model.Transaction;
import com.riosr.mybank.springboot.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BankController {

    private final TransactionService transactionService;

    public BankController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/transactions")
    public List<Transaction> getTransactions() {
        return transactionService.findAll();
    }

    @PostMapping("/transactions")
    public Transaction createTransaction(@Valid @RequestBody TransactionDto transactionDto) {
        return transactionService.create(transactionDto.getAmount(), transactionDto.getReference(), transactionDto.getReceivingUser());
    }
}
