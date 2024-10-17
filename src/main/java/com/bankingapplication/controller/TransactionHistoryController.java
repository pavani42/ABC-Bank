package com.bankingapplication.controller;

import com.bankingapplication.model.TransactionHistory;
import com.bankingapplication.service.TransactionHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts/{accountId}/transactions")
public class TransactionHistoryController {

    private final TransactionHistoryService transactionHistoryService;

    @Autowired
    public TransactionHistoryController(TransactionHistoryService transactionHistoryService) {
        this.transactionHistoryService = transactionHistoryService;
    }

    @GetMapping
    public ResponseEntity<List<TransactionHistory>> getAllTransactions(@PathVariable Long accountId) {
        List<TransactionHistory> transactions = transactionHistoryService.getAllTransactionsByAccountId(accountId);
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/{transactionId}")
    public ResponseEntity<TransactionHistory> getTransactionById(@PathVariable Long accountId, @PathVariable Long transactionId) {
        TransactionHistory transaction = transactionHistoryService.getTransactionById(accountId, transactionId);
        return ResponseEntity.ok(transaction);
    }

    @PostMapping
    public ResponseEntity<TransactionHistory> createTransaction(@PathVariable Long accountId, @RequestBody TransactionHistory transactionHistory) {
        TransactionHistory createdTransaction = transactionHistoryService.createTransaction(accountId, transactionHistory);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTransaction);
    }

    @DeleteMapping("/{transactionId}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long accountId, @PathVariable Long transactionId) {
        transactionHistoryService.deleteTransaction(accountId, transactionId);
        return ResponseEntity.noContent().build();
    }
}