package com.riosr.mybank.springboot.web;

import com.riosr.mybank.springboot.dto.TransactionDto;
import com.riosr.mybank.springboot.model.Transaction;
import com.riosr.mybank.springboot.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
public class BankController {

    private final TransactionService transactionService;

    public BankController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/transactions")
    public Iterable<Transaction> getTransactions() {
        return transactionService.findAll();
    }

    @GetMapping("/transactions/user/{userId}")
    public Iterable<Transaction> getTransactionsByUserId(@PathVariable String userId) {
        return transactionService.findByReceivingUserId(userId);
    }

    @PostMapping("/transactions")
    public Transaction createTransaction(@Valid @RequestBody TransactionDto transactionDto) {
        return transactionService.create(transactionDto.getAmount(), transactionDto.getReference(), transactionDto.getReceivingUser());
    }
}
