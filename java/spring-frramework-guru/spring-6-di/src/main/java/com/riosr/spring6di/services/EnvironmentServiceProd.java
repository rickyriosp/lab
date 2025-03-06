package com.riosr.spring6di.services;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile({"prod"})
public class EnvironmentServiceProd implements EnvironmentService {

    @Override
    public String getEnvironment() {
        return "prod";
    }
}
