package com.riosr.sq_ch11_clients.controllers;

import com.riosr.sq_ch11_clients.models.Payment;
import com.riosr.sq_ch11_clients.proxy.OpenFeignProxy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.logging.Logger;

@RestController
public class OpenFeignController {

    private static final Logger logger = Logger.getLogger(OpenFeignController.class.getName());

    private final OpenFeignProxy openFeignProxy;

    public OpenFeignController(OpenFeignProxy openFeignProxy) {
        this.openFeignProxy = openFeignProxy;
    }

    @PostMapping("/openfeign/payment")
    public ResponseEntity<Payment> createPayment(@RequestBody Payment payment) {
        logger.info("OpenFeign request received");
        String requestId = UUID.randomUUID().toString();

        return ResponseEntity
                .ok()
                .body(openFeignProxy.createPayment(requestId, payment));
    }
}
