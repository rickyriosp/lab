package com.riosr.sq_ch14.service;

import com.riosr.sq_ch14.exception.AccountNotFoundException;
import com.riosr.sq_ch14.model.Account;
import com.riosr.sq_ch14.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TransferService {

    private final AccountRepository accountRepository;

    public TransferService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional
    public void transferMoney(long senderId, long receiverId, BigDecimal amount) {
        Account sender = accountRepository
                .findById(senderId)
                .orElseThrow(() -> new AccountNotFoundException());

        Account receiver = accountRepository
                .findById(receiverId)
                .orElseThrow(AccountNotFoundException::new);

        BigDecimal senderNewAmount = sender.getAmount().subtract(amount);
        BigDecimal receiverNewAmount = receiver.getAmount().add(amount);

        accountRepository.changeAmount(senderId, senderNewAmount);
        accountRepository.changeAmount(receiverId, receiverNewAmount);
    }

    public Iterable<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public List<Account> findAccountsByName(String name) {
        return accountRepository.findAccountsByName(name);
    }
}
