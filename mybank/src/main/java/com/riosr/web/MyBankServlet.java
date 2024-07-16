package com.riosr.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.riosr.context.ApplicationConfiguration;
import com.riosr.model.Transaction;
import com.riosr.service.TransactionService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.util.List;

public class MyBankServlet extends HttpServlet {


    private TransactionService transactionService;
    private ObjectMapper objectMapper;

    @Override
    public void init() {
        AnnotationConfigApplicationContext ctx
                = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);

        ctx.registerShutdownHook();

        transactionService = ctx.getBean(TransactionService.class);
        objectMapper = ctx.getBean(ObjectMapper.class);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (req.getRequestURI().equalsIgnoreCase("/transactions")) {

            Integer amount = Integer.valueOf(req.getParameter("amount"));
            String reference = req.getParameter("reference");

            Transaction transaction = transactionService.create(amount, reference);
            String json = objectMapper.writeValueAsString(transaction);

            resp.setContentType("application/json; charset=utf-8");
            resp.getWriter().print(json);
        } else {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (req.getRequestURI().equalsIgnoreCase("/")) {
            resp.setContentType("text/html; charset=UTF-8");
            resp.getWriter().print("""
                    <!DOCTYPE html>
                    <body>
                    <h1>Hello World</h1>
                    <p>This is a simple HTML text for MyBank application.</p>
                    </body>
                    """);
        } else if (req.getRequestURI().equalsIgnoreCase("/transactions")) {

            List<Transaction> transactions = transactionService.findAll();
            String json = objectMapper.writeValueAsString(transactions);

            // TODO get real transactions.
            resp.setContentType("application/json; charset=utf-8");
            resp.getWriter().print(json);
        }
    }
}
