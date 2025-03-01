package com.riosr.sq_ch11_clients.proxy;

import com.riosr.sq_ch11_clients.models.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class WebClientProxy {

    private final WebClient webClient;

    @Value("${name.service.url}")
    private String paymentsServiceUrl;

    public WebClientProxy(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<Payment> createPayment(String requestId, Payment payment) {
        String uri = paymentsServiceUrl + "/payment";

        return webClient
                .post()
                .uri(uri)
                .header("requestId", requestId)
                .body(Mono.just(payment), Payment.class)
                .retrieve()
                .bodyToMono(Payment.class);
    }
}
