package com.riosr.sq_ch14.controller;

import com.riosr.sq_ch14.model.Account;
import com.riosr.sq_ch14.model.TransferRequest;
import com.riosr.sq_ch14.service.TransferService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController {

    private final TransferService transferService;

    public AccountController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping("/transfer")
    public void transferMoney(@RequestBody TransferRequest request) {
        transferService.transferMoney(
                request.getSenderId(),
                request.getReceiverId(),
                request.getAmount());
    }

    @GetMapping("/accounts")
    public ResponseEntity<Iterable<Account>> getAllAccounts(@RequestParam(required = false) String name) {
        Iterable<Account> accounts;

        if (name == null) {
            accounts = transferService.getAllAccounts();
        } else {
            accounts = transferService.findAccountsByName(name);
        }

        return ResponseEntity.ok().body(accounts);
    }
}
