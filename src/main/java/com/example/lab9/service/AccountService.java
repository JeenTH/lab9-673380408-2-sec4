package com.example.lab9.service;

import org.springframework.stereotype.Service;

import com.example.lab9.model.Account;
import com.example.lab9.repository.AccountRepository;

@Service 
public class AccountService {
    private final AccountRepository accountRepo;

    //constructor
    public AccountService(AccountRepository accountRepo){
        this.accountRepo = accountRepo;
    }

    //function
    public void createAccount(Account account){
        accountRepo.save(account);
    }
    public Account findAccountById(Long id){
        return accountRepo.findById(id).orElse(null);
    }

}
