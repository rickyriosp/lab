package com.riosr.sq_ch11_clients.proxy;

import com.riosr.sq_ch11_clients.models.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Component
public class RestClientProxy {

    private final RestTemplate restClient;

    @Value("${name.service.url}")
    private String paymentsServiceUrl;

    public RestClientProxy(RestTemplate restClient) {
        this.restClient = restClient;
    }

    public Payment createPayment(Payment payment) {
        String uri = paymentsServiceUrl + "/payment";

        HttpHeaders headers = new HttpHeaders();
        headers.add("requestId", UUID.randomUUID().toString());

        HttpEntity<Payment> httpEntity = new HttpEntity<>(payment, headers);

        ResponseEntity<Payment> response =
                restClient.exchange(uri, HttpMethod.POST, httpEntity, Payment.class);

        return response.getBody();
    }
}
