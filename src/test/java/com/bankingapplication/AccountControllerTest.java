package com.bankingapplication;

import com.bankingapplication.controller.AccountController;
import com.bankingapplication.model.Account;
import com.bankingapplication.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AccountControllerTest {

    @Mock
    private AccountService accountService;

    @InjectMocks
    private AccountController accountController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllAccounts_returnsAccountsList() {
        Long userId = 1L;
        List<Account> accounts = Arrays.asList(new Account(), new Account());
        when(accountService.getAllAccountsByUserId(userId)).thenReturn(accounts);

        ResponseEntity<List<Account>> response = accountController.getAllAccounts(userId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(accounts, response.getBody());
    }

    @Test
    void getAccountById_returnsAccount() {
        Long userId = 1L;
        Long accountId = 1L;
        Account account = new Account();
        when(accountService.getAccountById(userId, accountId)).thenReturn(account);

        ResponseEntity<Account> response = accountController.getAccountById(userId, accountId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(account, response.getBody());
    }

    @Test
    void createAccount_createsAndReturnsAccount() {
        Long userId = 1L;
        Account account = new Account();
        Account createdAccount = new Account();
        when(accountService.createAccount(userId, account)).thenReturn(createdAccount);

        ResponseEntity<Account> response = accountController.createAccount(userId, account);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(createdAccount, response.getBody());
    }

    @Test
    void updateAccount_updatesAndReturnsAccount() {
        Long userId = 1L;
        Long accountId = 1L;
        Account account = new Account();
        Account updatedAccount = new Account();
        when(accountService.updateAccount(userId, accountId, account)).thenReturn(updatedAccount);

        ResponseEntity<Account> response = accountController.updateAccount(userId, accountId, account);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedAccount, response.getBody());
    }

    @Test
    void deleteAccount_deletesAccount() {
        Long userId = 1L;
        Long accountId = 1L;
        doNothing().when(accountService).deleteAccount(userId, accountId);

        ResponseEntity<Void> response = accountController.deleteAccount(userId, accountId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(accountService, times(1)).deleteAccount(userId, accountId);
    }
}