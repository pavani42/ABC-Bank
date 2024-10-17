package com.bankingapplication.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Beneficiary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name cannot be blank")
    @Size(max = 255, message = "Name cannot exceed 255 characters")
    private String name;

    @NotBlank(message = "Account number cannot be blank")
    @Size(max = 255, message = "Account number cannot exceed 255 characters")
    private String accountNumber;

    @NotBlank(message = "Bank name cannot be blank")
    @Size(max = 255, message = "Bank name cannot exceed 255 characters")
    private String bankName;

    @NotBlank(message = "IFSC code cannot be blank")
    @Size(max = 11, message = "IFSC code cannot exceed 11 characters")
    private String ifscCode;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }
}