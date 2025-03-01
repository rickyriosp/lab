package com.riosr.mybank.springboot.service;

import com.riosr.mybank.springboot.model.Transaction;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.math.BigDecimal;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Component
public class TransactionService {

    private final JdbcTemplate jdbcTemplate;

    private String slogan;

    public TransactionService(@Value("${bank.slogan}") String slogan, JdbcTemplate jdbcTemplate) {
        this.slogan = slogan;
        System.out.println("Bank slogan is: " + slogan);
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public List<Transaction> findAll() {
        System.out.println("Is a database transaction open? = " + TransactionSynchronizationManager.isActualTransactionActive());

        return jdbcTemplate.query("select * from transaction", TransactionService::mapRow);
    }

    @Transactional
    public List<Transaction> findByReceivingUserId(String userId) {
        System.out.println("Is a database transaction open? = " + TransactionSynchronizationManager.isActualTransactionActive());

        return jdbcTemplate.query("select * from transaction where receiving_user = ?",
                new Object[] { userId },
                TransactionService::mapRow);

//        return jdbcTemplate.query(con -> {
//            PreparedStatement ps = con.prepareStatement(
//                    "SELECT tx.id, tx.amount, tx.time_stamp, tx.reference, tx.slogan, tx.receiving_user FROM TRANSACTION tx WHERE tx.receiving_user = ?");
//            ps.setString(1, userId);
//            return ps;
//        }, TransactionService::mapRow);
    }

    @Transactional
    public Transaction create(BigDecimal amount, String reference, String receivingUserId) {
        System.out.println("Is a database transaction open? = " + TransactionSynchronizationManager.isActualTransactionActive());

        Date timestamp = new Date();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement("insert into transaction (amount, time_stamp, reference, slogan, receiving_user) values ( ?, ?, ?, ?, ? )",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setBigDecimal(1, amount);
            ps.setTimestamp(2, Timestamp.valueOf(df.format(timestamp)));
            ps.setString(3, reference);
            ps.setString(4, slogan);
            ps.setString(5, receivingUserId);
            return ps;
        }, keyHolder);

        String uuid = !keyHolder.getKeys().isEmpty() ? ((UUID) keyHolder.getKeys().values().iterator().next()).toString() : null;

        Transaction transaction = new Transaction(amount, timestamp, reference, slogan, receivingUserId);
        transaction.setId(uuid);

        return transaction;
    }

    private static Transaction mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Transaction transaction = new Transaction();
        transaction.setId(resultSet.getObject("id").toString());
        transaction.setAmount(resultSet.getBigDecimal("amount"));
        transaction.setTimestamp(resultSet.getTimestamp("time_stamp"));
        transaction.setReference(resultSet.getString("reference"));
        transaction.setSlogan(resultSet.getString("slogan"));
        transaction.setReceivingUser(resultSet.getString("receiving_user"));
        return transaction;
    }
}
