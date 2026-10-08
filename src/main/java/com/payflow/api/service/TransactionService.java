package com.payflow.api.service;

import com.payflow.api.entity.Transaction;
import com.payflow.api.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    /*
     * Spring Startup & Dependency Injection Explanation:
     * @Service registers TransactionService as a Spring bean in the application context.
     * At application startup, Spring instantiates the TransactionRepository bean and injects
     * it into the @Autowired field, enabling TransactionService to execute database operations.
     */
    @Autowired
    private TransactionRepository transactionRepository;

    public Transaction sendMoney(Transaction transaction) {
        // Balance deduction is not required by assignment specifications; simply persist transaction record.
        return transactionRepository.save(transaction);
    }
}
