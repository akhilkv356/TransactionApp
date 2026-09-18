package com.hackathon.demoTransactionApp.controller;

import com.hackathon.demoTransactionApp.entity.Transaction;
import com.hackathon.demoTransactionApp.service.TransactionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
    @RequestMapping("/transactions")
public class TransactionController {
    private final TransactionService transactionService;
    
    public TransactionController(TransactionService transactionService){
        this.transactionService = transactionService;
    }
    @GetMapping("/{id}")
    public String getTransaction(@PathVariable Long id){
        Transaction transaction = transactionService.getTransaction(id);
        return transaction.getDetails();
    }
}
