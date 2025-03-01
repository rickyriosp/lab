package com.riosr.sq_ch13.controller;

import com.riosr.sq_ch13.model.Account;
import com.riosr.sq_ch13.model.TransferRequest;
import com.riosr.sq_ch13.service.TransferService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transfer")
public class TransferController {

    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @GetMapping
    public ResponseEntity<List<Account>> getAllAccounts() {
        return ResponseEntity.ok(transferService.getAllAccounts());
    }

    @PostMapping
    public void transferMoney(@RequestBody TransferRequest transferRequest) {
        transferService.transferMoney(
                transferRequest.getSenderId(),
                transferRequest.getReceiverId(),
                transferRequest.getAmount());
    }
}
