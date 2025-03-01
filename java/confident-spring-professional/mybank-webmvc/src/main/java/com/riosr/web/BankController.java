package com.riosr.web;

import com.riosr.dto.TransactionDto;
import com.riosr.model.Transaction;
import com.riosr.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BankController {

    private TransactionService transactionService;

    public BankController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

//    @GetMapping("/")
//    public String home() {
//        return "Hello World";
//    }

    @GetMapping("/transactions")
    public List<Transaction> transactions() {
        return transactionService.findAll();
    }

    @PostMapping("/transactions")
    public Transaction transactions(@RequestBody @Valid TransactionDto transactionDto) {
        return this.transactionService.create(transactionDto.getAmount(), transactionDto.getReference(), transactionDto.getReceivingUser());
    }
}
