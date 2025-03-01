package com.riosr.service;

import com.riosr.model.Transaction;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

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

    public Transaction create(Integer amount, String reference) {

        // TODO real bank transaction and sorting it on network server
        Date timestamp = new Date();
        Transaction transaction = new Transaction(amount, timestamp, reference, slogan);
        transactions.add(transaction);

        return transaction;
    }
}
