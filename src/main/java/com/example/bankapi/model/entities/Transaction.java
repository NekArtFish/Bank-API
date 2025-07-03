package com.example.bankapi.model.entities;

import com.example.bankapi.model.enums.StatusTransaction;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

public class Transaction {
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getDebitAccountId() {
        return debitAccountId;
    }

    public void setDebitAccountId(UUID debitAccountId) {
        this.debitAccountId = debitAccountId;
    }

    public UUID getCreditAccountId() {
        return creditAccountId;
    }

    public void setCreditAccountId(UUID creditAccountId) {
        this.creditAccountId = creditAccountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public StatusTransaction getStatusTransaction() {
        return statusTransaction;
    }

    public void setStatusTransaction(StatusTransaction statusTransaction) {
        this.statusTransaction = statusTransaction;
    }

    private UUID id;
    private UUID debitAccountId;
    private UUID creditAccountId;
    private BigDecimal amount;
    private String description;
    private OffsetDateTime createdAt;
    private StatusTransaction statusTransaction;
}
