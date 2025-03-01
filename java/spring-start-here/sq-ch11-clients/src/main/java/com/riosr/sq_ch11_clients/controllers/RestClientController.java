package com.riosr.sq_ch11_clients.controllers;

import com.riosr.sq_ch11_clients.models.Payment;
import com.riosr.sq_ch11_clients.proxy.RestClientProxy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
public class RestClientController {

    private final Logger logger = Logger.getLogger(this.getClass().getName());

    private final RestClientProxy restClientProxy;

    public RestClientController(RestClientProxy restClientProxy) {
        this.restClientProxy = restClientProxy;
    }

    @PostMapping("/restclient/payment")
    public ResponseEntity<Payment> createPayment(@RequestBody Payment payment) {
        logger.info("OpenFeign request received");
        return ResponseEntity.ok(restClientProxy.createPayment(payment));
    }
}
