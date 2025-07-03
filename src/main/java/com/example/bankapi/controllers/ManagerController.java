package com.example.bankapi.controllers;

import com.example.bankapi.model.entities.Manager;
import com.example.bankapi.model.services.ManagerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/managers")
public class ManagerController {
    private final ManagerService managerService;

    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }
    @PostMapping
    public ResponseEntity<Manager> createManager(@RequestBody CreateManagerRequest req){
        Manager manager = managerService.createManager(req.getName(), req.getLastName(), req.getEmail(), req.getPassword());
        return ResponseEntity.ok(manager);
    }
    @PostMapping("/{id}")
    public ResponseEntity<Manager> getManager(@PathVariable UUID id){
        Manager manager = managerService.getManager(id);
        return manager != null ? ResponseEntity.ok(manager): ResponseEntity.notFound().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<Manager> updateManager(@PathVariable UUID id, @RequestBody UpdateManagerRequest req){
        Manager existing = managerService.getManager(id);
        if(existing != null){
            return ResponseEntity.notFound().build();
        }
        existing.setName(req.getName());
        existing.setLastName(req.getLastName());
        existing.setEmail(req.getEmail());
        existing.setPassword(req.getPassword());
        Manager updated = managerService.updateManager(existing);
        return ResponseEntity.ok(updated);
    }
    public static class CreateManagerRequest{
        private String name;
        private String lastName;
        private String email;
        private String password;

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
    }
    public static class UpdateManagerRequest extends CreateManagerRequest{

    }
}
