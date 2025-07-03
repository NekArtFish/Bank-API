package com.example.bankapi;

import com.example.bankapi.model.entities.Manager;
import com.example.bankapi.model.services.ManagerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ManagerServiceTest {
    protected ManagerService managerService;


    @BeforeEach
    void  setUp(){
        managerService = new ManagerService();

    }

    @Test
    void testCreateManager(){
        Manager manager = managerService.createManager("Alice","Smith", "alice.smith@example.com",
                "password123");
        manager.setEmail("new.smith@example.com");
        Assertions.assertNotNull(managerService.getManager(manager.getId()), "Должен быть создан объект");
        Assertions.assertEquals("Alice",managerService.getManager(manager.getId()).getName());
        Assertions.assertEquals("Smith", managerService.getManager(manager.getId()).getLastName());
    }
    @Test
    void testUpdateManager() throws InterruptedException {
        Manager manager = managerService.createManager("Alice","Smith", "alice.smith@example.com",
                "password123");
        manager.setEmail("new.smith@example.com");
        Thread.sleep(2000);
        Manager updatedManager = managerService.updateManager(manager);
        Assertions.assertEquals("new.smith@example.com",updatedManager.getEmail());
        Assertions.assertTrue(updatedManager.getUpdatedAt().isAfter(updatedManager.getCreatedAt()));
    }



}
