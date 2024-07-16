package com.riosr.service;

import com.riosr.model.Transaction;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class TransactionService {

    private String slogan;
    private List<Transaction> transactions = new CopyOnWriteArrayList<>();

    public TransactionService(@Value("${bank.slogan}") String slogan) {
        this.slogan = slogan;
        System.out.println("Bank slogan is: " + slogan);
    }

    public List<Transaction> findAll() {
        return transactions;
    }

    public List<Transaction> findByReceivingUserId(String userId) {
        return transactions.stream().filter(t -> t.getReceivingUser().equalsIgnoreCase(userId)).toList();
    }

    public Transaction create(BigDecimal amount, String reference, String receivingUserId) {

        // TODO real bank transaction and sorting it on network server
        Date timestamp = new Date();
        Transaction transaction = new Transaction(amount, timestamp, reference, slogan, receivingUserId);
        transactions.add(transaction);

        return transaction;
    }
}
