package com.example.bankapi.model.services;

import com.example.bankapi.model.entities.Transaction;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.*;

public class TransactionService {

    private static Map<UUID, Transaction> transactionRepository = new HashMap<>();


    public static Transaction createTransaction(UUID debitAccount, UUID creditAccount, BigDecimal amount, String description) {
        Transaction transaction = new Transaction();
        transaction.setCreatedAt(OffsetDateTime.now());
        transaction.setId(UUID.randomUUID());
        transaction.setDebitAccountId(debitAccount);
        transaction.setCreditAccountId(creditAccount);
        transaction.setAmount(amount);
        transaction.setDescription(description);
        transactionRepository.put(transaction.getId(), transaction);
        return transaction;

    }

    public List<Transaction> getTransactionsForAccount(UUID accountId){
        List<Transaction> transactions = new ArrayList<>();
        for(Transaction transaction: transactionRepository.values()){
            if ((transaction.getDebitAccountId()!=null && transaction.getDebitAccountId().equals(accountId)) ||
                    (transaction.getCreditAccountId()!=null && transaction.getCreditAccountId().equals(accountId))){
                transactions.add(transaction);
            }
        }
        return transactions;
    }
}
