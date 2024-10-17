package com.bankingapplication.repository;

import com.bankingapplication.model.TransactionHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionHistoryRepository extends JpaRepository<TransactionHistory, Long> {
    List<TransactionHistory> findByAccountId(Long accountId);
    TransactionHistory findByIdAndAccountId(Long id, Long accountId);
    void deleteByIdAndAccountId(Long id, Long accountId);
}