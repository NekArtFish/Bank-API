package com.example.bankapi;

import com.example.bankapi.model.entities.Account;
import com.example.bankapi.model.enums.Currency;
import com.example.bankapi.model.services.AccountService;
import com.example.bankapi.model.services.TransactionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.UUID;

@SpringBootTest
public class AccountServiceTest {
    private AccountService accountService;
    private TransactionService transactionService;
    private UUID customerId;

    @BeforeEach
    void setUp(){
        transactionService = new TransactionService();
        accountService = new AccountService(transactionService);
        customerId = UUID.randomUUID();
    }
    @Test
    void testCreateAccount(){
        Account account = accountService.createAccount(customerId, "Тестовый Аккаунт", Currency.USD,
                BigDecimal.valueOf(100));
        Assertions.assertNotNull(account.getId(),"Индентификатор не должен быть Null");
        Assertions.assertEquals("Тестовый Аккаунт", account.getName());
        Assertions.assertEquals(BigDecimal.valueOf(100), account.getBalance());
        Assertions.assertEquals(Currency.USD, account.getCurrency());
    }

    @Test
    void testDeposit(){
        Account account = accountService.createAccount(customerId, "Deposit account", Currency.USD,
                BigDecimal.valueOf(100));
        accountService.deposit(account.getId(), BigDecimal.valueOf(50));
        Assertions.assertEquals(BigDecimal.valueOf(150),accountService.getAccount(account.getId()).getBalance());
    }

    @Test
    void testWithdraw(){
        Account account = accountService.createAccount(customerId, "Withdraw Test", Currency.USD,
                BigDecimal.valueOf(100));
        accountService.withdraw(account.getId(), BigDecimal.valueOf(40));
        Assertions.assertEquals(BigDecimal.valueOf(60), accountService.getAccount(account.getId()).getBalance());
    }

    @Test
    void testWithdrawOverflow(){
        Account account = accountService.createAccount(customerId, "Withdraw Test", Currency.USD,
                BigDecimal.valueOf(100));
        RuntimeException ex = Assertions.assertThrows(RuntimeException.class, ()->
                accountService.withdraw(account.getId(), BigDecimal.valueOf(150)));
        Assertions.assertEquals("Insufficient funds", ex.getMessage());

    }

    @Test
    void testTransfer(){
        Account accountFrom = accountService.createAccount(customerId, "transfer from", Currency.USD,
                BigDecimal.valueOf(200));
        Account accountTo = accountService.createAccount(UUID.randomUUID(),"transfer to", Currency.USD,
                BigDecimal.valueOf(100));
        accountService.transfer(accountFrom.getId(),accountTo.getId(),BigDecimal.valueOf(50));
        Assertions.assertEquals(BigDecimal.valueOf(150), accountService.getAccount(accountFrom.getId()).getBalance());
        Assertions.assertEquals(BigDecimal.valueOf(150), accountService.getAccount(accountTo.getId()).getBalance());
    }
}
