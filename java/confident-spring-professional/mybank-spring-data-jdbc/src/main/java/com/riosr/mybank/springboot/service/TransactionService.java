package com.riosr.mybank.springboot.service;

import com.riosr.mybank.springboot.model.Transaction;
import com.riosr.mybank.springboot.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.math.BigDecimal;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class TransactionService {

    private final TransactionRepository transactionRepository;

    private String slogan;

    public TransactionService(@Value("${bank.slogan}") String slogan, TransactionRepository transactionRepository) {
        this.slogan = slogan;
        System.out.println("Bank slogan is: " + slogan);
        this.transactionRepository = transactionRepository;
    }

    @Transactional
    public Iterable<Transaction> findAll() {
        System.out.println("Is a database transaction open? = " + TransactionSynchronizationManager.isActualTransactionActive());

        return transactionRepository.findAll();
    }

    @Transactional
    public Iterable<Transaction> findByReceivingUserId(String userId) {
        System.out.println("Is a database read transaction open? = " + TransactionSynchronizationManager.isActualTransactionActive());

        return transactionRepository.findByUserId(userId);
    }

    @Transactional
    public Transaction create(BigDecimal amount, String reference, String receivingUserId) {
        System.out.println("Is a database write transaction open? = " + TransactionSynchronizationManager.isActualTransactionActive());

        Date timestamp = new Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setReference(reference);
        transaction.setSlogan(slogan);
        transaction.setTimeStamp(timestamp);
        transaction.setReceivingUser(receivingUserId);

        return transactionRepository.save(transaction);
    }

    private static Transaction mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Transaction transaction = new Transaction();
        transaction.setId(resultSet.getObject("id").toString());
        transaction.setAmount(resultSet.getBigDecimal("amount"));
        transaction.setTimeStamp(resultSet.getTimestamp("time_stamp"));
        transaction.setReference(resultSet.getString("reference"));
        transaction.setSlogan(resultSet.getString("slogan"));
        transaction.setReceivingUser(resultSet.getString("receiving_user"));
        return transaction;
    }
}
