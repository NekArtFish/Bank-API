package com.example.bankapi.model.services;

import com.example.bankapi.model.entities.Account;
import com.example.bankapi.model.enums.AccountStatus;
import com.example.bankapi.model.enums.Currency;


import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AccountService {
    private Map<UUID, Account> accountRepository = new HashMap<>();
    private TransactionService transactionService;


    public AccountService(TransactionService transactionService){
        this.transactionService = transactionService;

    }
    public Account createAccount(UUID customerId, String name, Currency currency, BigDecimal initialBalance){
        Account account = new Account();
        account.setId((UUID.randomUUID()));
        account.setCustomerId(customerId);
        account.setName(name);
        account.setCurrency(currency);
        account.setBalance(initialBalance);
        account.setCreatedAt(OffsetDateTime.now());
        account.setUpdatedAt(OffsetDateTime.now());
        account.setStatus(AccountStatus.Active);
        accountRepository.put(account.getId(),account);
        return account;

    }

    public void deposit(UUID accountId,BigDecimal amount){
        Account account = accountRepository.get(accountId);
        if(account == null) {
            throw new RuntimeException(("Счет не найден")); // cast isclucheniya
        }
        if(account.getStatus() != AccountStatus.Active){
            throw new RuntimeException("Счет не активен");
        }
        account.setBalance(account.getBalance().add(amount));
        account.setUpdatedAt(OffsetDateTime.now());

        TransactionService.createTransaction(null,accountId,amount, "Пополнение счета");
    }

    public void withdraw(UUID accountId, BigDecimal amount){
        Account account = accountRepository.get(accountId);
        if(account == null) {
            throw new RuntimeException(("Счет не найден")); // cast isclucheniya
        }
        if(account.getStatus() != AccountStatus.Active){
            throw new RuntimeException("Счет не активен");
        }
        if(account.getBalance().compareTo(amount)<0){
            throw new RuntimeException("Insufficient funds");
        }

        account.setBalance(account.getBalance().subtract(amount));
        account.setUpdatedAt(OffsetDateTime.now());

        transactionService.createTransaction(accountId, null, amount,"Снятие со счета");
    }
    public void transfer(UUID fromAccountId, UUID toAccountId, BigDecimal amount){
        Account fromAccount = accountRepository.get(fromAccountId);
        Account toAccount = accountRepository.get(toAccountId);
        if(fromAccount == null) {
            throw new RuntimeException(("Счет вывода не найден")); // cast isclucheniya
        }
        if(fromAccount.getStatus() != AccountStatus.Active){
            throw new RuntimeException("Счет вывода не активен");
        }
        if(toAccount == null) {
            throw new RuntimeException(("Счет перевода не найден")); // cast isclucheniya
        }
        if(toAccount.getStatus() != AccountStatus.Active){
            throw new RuntimeException("Счет перевода не активен");
        }
        if(fromAccount.getBalance().compareTo(amount)<0){
            throw new RuntimeException("Недостаточно средств на исходящем счете");
        }

        toAccount.setBalance(toAccount.getBalance().add(amount));
        fromAccount.setBalance(fromAccount.getBalance().subtract(amount));
        fromAccount.setUpdatedAt(OffsetDateTime.now());
        toAccount.setUpdatedAt(OffsetDateTime.now());

        transactionService.createTransaction(fromAccountId,toAccountId,amount,"Перевод средств");



    }

    public Account getAccount(UUID accountId){
        return accountRepository.get(accountId);
    }
    public Map<UUID, Account> getAll(){
        return accountRepository;
    }



}
