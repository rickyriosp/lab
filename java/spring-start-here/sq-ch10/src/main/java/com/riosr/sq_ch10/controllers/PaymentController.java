package com.riosr.sq_ch10.controllers;

import com.riosr.sq_ch10.models.ErrorDetails;
import com.riosr.sq_ch10.models.PaymentDetails;
import com.riosr.sq_ch10.services.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
public class PaymentController {

    private final Logger logger = Logger.getLogger(this.getClass().getName());
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/payment")
    public ResponseEntity<?> makePayment() {
        PaymentDetails paymentDetails = paymentService.processPayment();
        return ResponseEntity.accepted().body(paymentDetails);
    }

    @PostMapping("/payment")
    public ResponseEntity<?> processPayment(@RequestBody PaymentDetails paymentDetails) {
        logger.info("Received payment: " + paymentDetails.getAmount());
        return ResponseEntity.accepted().body(paymentDetails);
    }
}
