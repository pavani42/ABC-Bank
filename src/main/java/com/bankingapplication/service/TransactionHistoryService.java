package com.bankingapplication.service;

import com.bankingapplication.model.TransactionHistory;
import com.bankingapplication.repository.TransactionHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionHistoryService {

    private final TransactionHistoryRepository transactionHistoryRepository;

    @Autowired
    public TransactionHistoryService(TransactionHistoryRepository transactionHistoryRepository) {
        this.transactionHistoryRepository = transactionHistoryRepository;
    }

    public List<TransactionHistory> getAllTransactionsByAccountId(Long accountId) {
        return transactionHistoryRepository.findByAccountId(accountId);
    }

    public TransactionHistory getTransactionById(Long accountId, Long transactionId) {
        return transactionHistoryRepository.findByIdAndAccountId(transactionId, accountId);
    }

    public TransactionHistory createTransaction(Long accountId, TransactionHistory transactionHistory) {
        transactionHistory.setAccountId(accountId);
        return transactionHistoryRepository.save(transactionHistory);
    }

    public void deleteTransaction(Long accountId, Long transactionId) {
        transactionHistoryRepository.deleteByIdAndAccountId(transactionId, accountId);
    }
}