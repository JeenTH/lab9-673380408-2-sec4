package com.example.lab9.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.lab9.model.Account;
import com.example.lab9.model.DepositTransaction;
import com.example.lab9.service.AccountService;
import com.example.lab9.service.DepositService;




@RestController 
public class AccountController {
    private AccountService accountService;
    private DepositService depositService;

    //constructor
    public AccountController(AccountService accountService, DepositService depositService){
        this.accountService = accountService;
        this.depositService = depositService;
    }

    @PostMapping("/accounts")
    public Account CreateAccount(@RequestBody Account account) {
        accountService.createAccount(account);
        return account;
    }
    @GetMapping("/accounts/{id}")
    public Account showBalance(@PathVariable Long id) {
        return accountService.findAccountById(id);
    }
    @PostMapping("/accounts/{id}/deposit")
    public String deposit(@PathVariable Long id, @RequestBody DepositTransaction depositTransaction) {
        depositService.deposit(id, depositTransaction.getAmount());
        return "Deposit successful";
    }
    
    
    
}
