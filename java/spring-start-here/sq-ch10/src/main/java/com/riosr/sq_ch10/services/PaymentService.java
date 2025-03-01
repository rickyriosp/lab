package com.riosr.sq_ch10.services;

import com.riosr.sq_ch10.exceptions.NotEnoughMoneyException;
import com.riosr.sq_ch10.models.PaymentDetails;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    public PaymentDetails processPayment() {
        throw new NotEnoughMoneyException();
    }
}
