package com.riosr.spring6di.services;

import org.springframework.stereotype.Service;

@Service
public class GreetingServiceSetterInjection implements GreetingService {

    @Override
    public String sayGreeting() {
        return "Hi I'm setting a Greeting!";
    }
}
