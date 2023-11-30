package com.service.banking.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Bank")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String accountHolderName;
    private Long mobileNumber;
    private Long accountNumber;
    private double balance;
    private String bankName;

    @Column(name = "created_at")
    private String createdAt;
    public String getCreatedAt() {
        return createdAt;
    }

    public String setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
        return createdAt;
    }

    public Account() {
    }

    public Account(String accountHolderName,Long mobileNumber, Long accountNumber, double balance, String bankName,String date) {
        this.accountHolderName = accountHolderName;
        this.mobileNumber=mobileNumber;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.bankName = bankName;
        this.createdAt =date;
    }

    public Long getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(Long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }


    @Override
    public String toString() {
        return "Account{" +
                "accountHolderName='" + accountHolderName + '\'' +
                "mobileNumber='" + mobileNumber + '\'' +
                ", accountNumber=" + accountNumber +
                ", balance=" + balance +
                ", bankName='" + bankName + '\'' +
                '}';
    }
}
