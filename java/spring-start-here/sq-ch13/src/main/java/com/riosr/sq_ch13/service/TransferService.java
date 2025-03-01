package com.riosr.sq_ch13.service;

import com.riosr.sq_ch13.model.Account;
import com.riosr.sq_ch13.repository.AccountRespository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TransferService {

    private final AccountRespository accountRespository;

    public TransferService(AccountRespository accountRespository) {
        this.accountRespository = accountRespository;
    }

    @Transactional
    public void transferMoney(long idSender, long idReceiver, BigDecimal amount) {
        Account sender = accountRespository.findAccountById(idSender);
        Account receiver = accountRespository.findAccountById(idReceiver);

        BigDecimal senderNewAmount = sender.getAmount().subtract(amount);
        BigDecimal recieverNewAmount = receiver.getAmount().add(amount);

        accountRespository.changeAmount(idSender, senderNewAmount);
        accountRespository.changeAmount(idReceiver, recieverNewAmount);

        throw new RuntimeException("transfer failed");
    }

    public List<Account> getAllAccounts() {
        return accountRespository.findAllAccounts();
    }
}
