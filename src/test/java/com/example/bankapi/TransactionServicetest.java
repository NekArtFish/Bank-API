package com.example.bankapi;

import com.example.bankapi.model.entities.Transaction;
import com.example.bankapi.model.services.TransactionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@SpringBootTest
public class TransactionServicetest {
    private TransactionService transactionService;

    @BeforeEach
    void setUp(){
        transactionService = new TransactionService();
    }

    @Test
    void testCreateTransaction(){
        Transaction transaction = transactionService.createTransaction(UUID.randomUUID(),UUID.randomUUID(),
                BigDecimal.valueOf(100), "Test transaction");
        Assertions.assertNotNull(transaction, "Должна быть создана тразакция");
        Assertions.assertEquals(BigDecimal.valueOf(100), transaction.getAmount());
        Assertions.assertEquals("Test transaction", transaction.getDescription());
    }

    @Test
    void testGetTransactionsAccount(){
        UUID accountId = UUID.randomUUID();
        transactionService.createTransaction(accountId,null,BigDecimal.valueOf(100),
                "Withdrawal");
        transactionService.createTransaction(null, accountId,BigDecimal.valueOf(50),
                "Deposit");
        transactionService.createTransaction(UUID.randomUUID(),UUID.randomUUID(),BigDecimal.valueOf(30),
                "Transfer");
        List<Transaction> transactions = transactionService.getTransactionsForAccount(accountId);
        Assertions.assertEquals(2,transactions.size());
    }
}
