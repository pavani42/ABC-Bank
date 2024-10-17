package com.bankingapplication.service;

import com.bankingapplication.model.Statements;
import com.bankingapplication.repository.StatementsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatementsService {

    private final StatementsRepository statementsRepository;

    @Autowired
    public StatementsService(StatementsRepository statementsRepository) {
        this.statementsRepository = statementsRepository;
    }

    public List<Statements> getAllStatementsByAccountId(Long accountId) {
        return statementsRepository.findByAccountId(accountId);
    }

    public Statements getStatementById(Long accountId, Long statementId) {
        return statementsRepository.findByIdAndAccountId(statementId, accountId);
    }

    public Statements createStatement(Long accountId, Statements statements) {
        statements.setAccountId(accountId);
        return statementsRepository.save(statements);
    }

    public void deleteStatement(Long accountId, Long statementId) {
        statementsRepository.deleteByIdAndAccountId(statementId, accountId);
    }
}