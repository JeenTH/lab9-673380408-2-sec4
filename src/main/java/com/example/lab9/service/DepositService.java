package com.example.lab9.service;

import org.springframework.stereotype.Service;

import com.example.lab9.model.Account;
import com.example.lab9.model.DepositTransaction;
import com.example.lab9.repository.AccountRepository;
import com.example.lab9.repository.DepositRepository;

import jakarta.transaction.Transactional;

@Service 
public class DepositService {
    private final AccountRepository accountRepo;
    private final DepositRepository depositRepo;

    //constructor
    public DepositService(AccountRepository accountRepo, DepositRepository depositRepo){
        this.accountRepo = accountRepo;
        this.depositRepo = depositRepo;
    }

    @Transactional
    public void deposit(Long accountId, Double amount){
        //find account
        Account account = accountRepo.findById(accountId).orElseThrow(() -> new RuntimeException("Account not found: " + accountId));

        //set balance
        account.setBalance(account.getBalance() + amount);
        accountRepo.save(account);

        //create depositTransaction
        DepositTransaction depositTransaction = new DepositTransaction();
        depositTransaction.setAmount(amount);
        depositTransaction.setAccount(account); 
        depositRepo.save(depositTransaction);

        //12.1 test
        // throw new RuntimeException("Test Rollback");
    }

}
