package com.riosr.web.forms;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public class TxForm {

    @NotNull
    @Min(5)
    @Max(25)
    private BigDecimal amount;

    @NotBlank
    @Size(min = 2, max = 25)
    private String reference;

    @NotBlank
    private String receivingUser;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getReceivingUser() {
        return receivingUser;
    }

    public void setReceivingUser(String receivingUser) {
        this.receivingUser = receivingUser;
    }
}
