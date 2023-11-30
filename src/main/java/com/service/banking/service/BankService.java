package com.service.banking.service;

import com.service.banking.model.Account;
import com.service.banking.repo.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BankService {

    @Autowired
    BankRepository repository;

    public Account addNewAccount(Account accountObject, String createdAt) {

        Account acc = repository.save(accountObject);
        acc.setCreatedAt(createdAt);

        return repository.save(accountObject);
    }

    public Account retrieveAccount(Long accountNumber) {
        return repository.findByAccountNumber(accountNumber);
    }

    public ResponseEntity<?> depositMoney(Double amount, Long accountNumber) {
        Account acc = repository.findByAccountNumber(accountNumber);
        if (acc != null) {
            acc.setBalance(amount + acc.getBalance());
            repository.save(acc);
            return ResponseEntity.ok(repository.findByAccountNumber(accountNumber));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Account not found");
        }

    }

    public ResponseEntity<?> withdrawMoney(Double amount, Long accountNumber) {
        Account acc = repository.findByAccountNumber(accountNumber);
        if (acc != null) {
            if (acc.getBalance() < amount) {
                return ResponseEntity.status(HttpStatus.INSUFFICIENT_STORAGE).body("Insufficient Funds");
            } else {
                acc.setBalance(acc.getBalance() - amount);
                repository.save(acc);
            }
            return ResponseEntity.ok(repository.findByAccountNumber(accountNumber));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Account not found");
        }

    }

    public Account retrieveAccountDetails(Long accountNumber) {
        return repository.findByAccountNumber(accountNumber);

    }
}
