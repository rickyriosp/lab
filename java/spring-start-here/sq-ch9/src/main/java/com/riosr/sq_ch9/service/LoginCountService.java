package com.riosr.sq_ch9.service;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

@Service
@ApplicationScope
public class LoginCountService {

    private int loginCount;

    public void increment() {
        loginCount++;
    }

    public int getLoginCount() {
        return loginCount;
    }
}
