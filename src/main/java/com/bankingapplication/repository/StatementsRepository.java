package com.bankingapplication.repository;

import com.bankingapplication.model.Statements;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatementsRepository extends JpaRepository<Statements, Long> {
    List<Statements> findByAccountId(Long accountId);
    Statements findByIdAndAccountId(Long id, Long accountId);
    void deleteByIdAndAccountId(Long id, Long accountId);
}