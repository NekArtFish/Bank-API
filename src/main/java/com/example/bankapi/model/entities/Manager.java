package com.example.bankapi.model.entities;

import com.example.bankapi.model.enums.ManagerStatus;

import java.time.OffsetDateTime;
import java.util.UUID;

public class Manager {
    private UUID id;
    private ManagerStatus status;
    private String name;
    private String lastName;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public ManagerStatus getStatus() {
        return status;
    }

    public void setStatus(ManagerStatus status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(OffsetDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    private String email;
    private String password;
    private OffsetDateTime createdAt;
    private  OffsetDateTime updatedAt;

}
