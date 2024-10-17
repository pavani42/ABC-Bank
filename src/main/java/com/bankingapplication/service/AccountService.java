package com.bankingapplication.service;

import com.bankingapplication.model.Account;
import com.bankingapplication.repository.AccountRepository;
import com.bankingapplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Account> getAllAccountsByUserId(Long userId) {
        return accountRepository.findByUserId(userId);
    }

    public Account getAccountById(Long userId, Long accountId) {
        return accountRepository.findByIdAndUserId(accountId, userId)
                .orElseThrow(() -> new RuntimeException("Account not found"));
    }

    public Account createAccount(Long userId, Account account) {
        account.setUserId(userId);
        return accountRepository.save(account);
    }

    public Account updateAccount(Long userId, Long accountId, Account account) {
        Account existingAccount = getAccountById(userId, accountId);
        existingAccount.setAccountNumber(account.getAccountNumber());
        existingAccount.setAccountType(account.getAccountType());
        existingAccount.setBalance(account.getBalance());
        return accountRepository.save(existingAccount);
    }

    public void deleteAccount(Long userId, Long accountId) {
        Account account = getAccountById(userId, accountId);
        accountRepository.delete(account);
    }
}