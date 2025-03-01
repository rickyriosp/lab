package com.riosr.myfancypdfinvoices.springboot.service;

import com.riosr.myfancypdfinvoices.springboot.model.Invoice;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.UUID;

@Component
public class InvoiceService {

    private final JdbcTemplate jdbcTemplate;

    private final UserService userService;
    private String cdnUrl;

    public InvoiceService(UserService userService, @Value("${cdn.url}") String cdnUrl, JdbcTemplate jdbcTemplate) {
        this.userService = userService;
        this.cdnUrl = cdnUrl;
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostConstruct
    public void init() {
        System.out.println("Fetching PDF template from S3...");
        // TODO download from S3 and save locally
    }

    @PreDestroy
    public void shutdown() {
        System.out.println("Deleting downloaded PDF template...");
        // TODO actual deletion of PDFs
    }

    @Transactional
    public List<Invoice> findAll() {
        System.out.println("Is a database transaction open? = " + TransactionSynchronizationManager.isActualTransactionActive());

        return jdbcTemplate.query("select * from invoices", (rs, rowNum) -> {
            Invoice invoice = new Invoice();
            invoice.setId(rs.getObject("id").toString());
            invoice.setUserId(rs.getString("user_id"));
            invoice.setAmount(rs.getInt("amount"));
            invoice.setPdfUrl(rs.getString("pdf_url"));
            return invoice;
        });
    }

    @Transactional
    public Invoice create(String userId, Integer amount) {
        System.out.println("Is a database transaction open? = " + TransactionSynchronizationManager.isActualTransactionActive());

        String generatedPdf = cdnUrl + "/images/default/sample.pdf";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement("insert into invoices(user_id, amount, pdf_url) values (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, userId);
            ps.setInt(2, amount);
            ps.setString(3, generatedPdf);
            return ps;
        }, keyHolder);

        String id = !keyHolder.getKeys().isEmpty() ? ((UUID) keyHolder.getKeys().values().iterator().next()).toString() : null;

        Invoice invoice = new Invoice(userId, amount, generatedPdf);
        invoice.setId(id);
        return invoice;
    }
}
