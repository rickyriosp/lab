package com.riosr.myfancypdfinvoices.springboot.service;

import com.riosr.myfancypdfinvoices.springboot.model.Invoice;
import com.riosr.myfancypdfinvoices.springboot.repository.InvoiceRepository;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Component
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;

    private final String cdnUrl;

    public InvoiceService(@Value("${cdn.url}") String cdnUrl, InvoiceRepository invoiceRepository) {
        this.cdnUrl = cdnUrl;
        this.invoiceRepository = invoiceRepository;
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
    public Iterable<Invoice> findAll() {
        System.out.println("Is a database read transaction open? = " + TransactionSynchronizationManager.isActualTransactionActive());

        return invoiceRepository.findAll();
    }

    @Transactional
    public Iterable<Invoice> findByUserId(String userId) {
        return invoiceRepository.findByUserId(userId);
    }

    @Transactional
    public Invoice create(String userId, Integer amount) {
        System.out.println("Is a database write transaction open? = " + TransactionSynchronizationManager.isActualTransactionActive());

        String generatedPdfUrl = cdnUrl + "/images/default/sample.pdf";

        Invoice invoice = new Invoice();
        invoice.setPdfUrl(generatedPdfUrl);
        invoice.setAmount(amount);
        invoice.setUserId(userId);

        return invoiceRepository.save(invoice);
    }
}
