package com.payflow.api.controller;

import com.payflow.api.entity.Transaction;
import com.payflow.api.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    // POST /transactions - Record a payment transaction
    @PostMapping
    public ResponseEntity<Transaction> sendMoney(@RequestBody Transaction transaction) {
        Transaction savedTransaction = transactionService.sendMoney(transaction);
        return new ResponseEntity<>(savedTransaction, HttpStatus.CREATED);
    }
}
