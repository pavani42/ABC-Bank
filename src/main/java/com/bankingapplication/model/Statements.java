package com.bankingapplication.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.Date;

@Entity
public class Statements {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Account ID cannot be null")
    private Long accountId;

    @NotNull(message = "Statement date cannot be null")
    private Date statementDate;

    @NotBlank(message = "Statement details cannot be blank")
    @Size(max = 255, message = "Statement details cannot exceed 255 characters")
    private String statementDetails;

    // Constructors, getters, and setters

    public Statements() {
    }

    public Statements(Long accountId, Date statementDate, String statementDetails) {
        this.accountId = accountId;
        this.statementDate = statementDate;
        this.statementDetails = statementDetails;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Date getStatementDate() {
        return statementDate;
    }

    public void setStatementDate(Date statementDate) {
        this.statementDate = statementDate;
    }

    public String getStatementDetails() {
        return statementDetails;
    }

    public void setStatementDetails(String statementDetails) {
        this.statementDetails = statementDetails;
    }
}