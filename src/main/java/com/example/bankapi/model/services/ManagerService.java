package com.example.bankapi.model.services;

import com.example.bankapi.model.entities.Manager;
import com.example.bankapi.model.enums.ManagerStatus;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ManagerService {
    public Map<UUID, Manager> managerRepository = new HashMap<>();

    public Manager createManager(String name, String lastName, String email, String password){
        // пароль в системе не в зашифрованном виде?
        Manager manager = new Manager();
        manager.setId(UUID.randomUUID());
        manager.setName(name);
        manager.setLastName(lastName);
        manager.setEmail(email);
        manager.setPassword(password); // в прикладных проектах необходимо хэшировать, такой подход повысит безопасность системы
        manager.setCreatedAt(OffsetDateTime.now());
        manager.setUpdatedAt(OffsetDateTime.now());
        manager.setStatus(ManagerStatus.ACTIVE);
        managerRepository.put(manager.getId(), manager);
        return manager;
    }

    public Manager updateManager(Manager manager){
        manager.setUpdatedAt(OffsetDateTime.now());
        managerRepository.put(manager.getId(),manager);
        return manager;
    }

    public Manager getManager(UUID managerId){
        return managerRepository.get(managerId);
    }

    public Map<UUID,Manager> getAll(){
        return managerRepository;
    }
}
