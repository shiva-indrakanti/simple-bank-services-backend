package com.service.banking.controller;

import com.service.banking.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.service.banking.service.BankService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;


@RestController
public class AccountController {
    @Autowired
    BankService service;

    // creating account
    @PostMapping(value = "/banking/accounts/")
    public ResponseEntity<?> saveAccount(@RequestBody Account accountObject) {
        Long uniqueNumber = accountObject.getAccountNumber();
        DateFormat dateAndTimeFormat = new SimpleDateFormat("dd-MM-yy HH:mm:ss");
        Date dateCreation = new Date();

        Account account = service.retrieveAccountDetails(uniqueNumber);

        if (account == null) {
            return ResponseEntity.ok(service.addNewAccount(accountObject, dateAndTimeFormat.format(dateCreation)));
        } else {
            return ResponseEntity.status(HttpStatus.FOUND).body("Account Already Exist, please click on Account Details to check your account details");
        }
    }

    // Get specific account
    @GetMapping(value = "/banking/{accountNumber}")
    public Account getAccount(@PathVariable Long accountNumber) {
        return service.retrieveAccount(accountNumber);
    }

    //deposit
    @PostMapping(value = "/banking/deposit/{accountNumber}")
    public ResponseEntity<?> deposit(@RequestBody Map<String, Double> request, @PathVariable Long accountNumber) {
        Double amount = request.get("amount");
        return service.depositMoney(amount, accountNumber);
    }

    //withdraw
    @PostMapping(value = "/banking/withdraw/{accountNumber}")
    public ResponseEntity<?> withdraw(@RequestBody Map<String, Double> request, @PathVariable Long accountNumber) {
        Double amount = request.get("amount");
        return service.withdrawMoney(amount, accountNumber);
    }


    //Retrieve Balance of a specific account
    @GetMapping(value = "/banking/account/{accountNumber}")
    public ResponseEntity<?> getBalance(@PathVariable Long accountNumber) {
        Account account = service.retrieveAccountDetails(accountNumber);
        if (account == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Account not found");
        } else {
            return ResponseEntity.ok(account);
        }
    }

}
