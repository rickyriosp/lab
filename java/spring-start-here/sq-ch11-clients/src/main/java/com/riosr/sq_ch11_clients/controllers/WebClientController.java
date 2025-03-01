package com.riosr.sq_ch11_clients.controllers;

import com.riosr.sq_ch11_clients.models.Payment;
import com.riosr.sq_ch11_clients.proxy.WebClientProxy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.UUID;
import java.util.logging.Logger;

@RestController
public class WebClientController {

    private final Logger logger = Logger.getLogger(this.getClass().getName());

    private final WebClientProxy webClientProxy;

    public WebClientController(WebClientProxy webClientProxy) {
        this.webClientProxy = webClientProxy;
    }

    @PostMapping("/webclient/payment")
    public Mono<Payment> createPayment(@RequestBody Payment payment) {
        logger.info("WebClient request received");

        String requestId = UUID.randomUUID().toString();
        return webClientProxy.createPayment(requestId, payment);
    }
}
