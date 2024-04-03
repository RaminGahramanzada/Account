package com.company.account.service;

import com.company.account.dto.CreateAccountRequest;
import com.company.account.model.Account;
import com.company.account.model.Transaction;
import com.company.account.repository.TransActionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TransactionService {
    private Logger logger = LoggerFactory.getLogger(TransactionService.class);
    private final TransActionRepository transActionRepository;

    public TransactionService(TransActionRepository transActionRepository) {
        this.transActionRepository = transActionRepository;
    }
    protected Transaction initiateMoney(final Account account, BigDecimal amount){
        return transActionRepository.save(
              initiateMoney(account,amount)
        );
    }
    }

