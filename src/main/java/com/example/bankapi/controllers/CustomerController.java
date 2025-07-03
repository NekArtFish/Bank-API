package com.example.bankapi.controllers;

import com.example.bankapi.model.entities.Customer;
import com.example.bankapi.model.services.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@Mapping("/customer")
public class CustomerController {
    //TODO найти бесплатный сервис для подключения https и разграничить доступ к запросам по ролям
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

    // Чем отличается POST запрос от Get запроса?
    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody CreateCustomerRequest req){
        Customer customer = customerService.createCustomer(req.getName(), req.getLastName(), req.getEmail(),
                req.getAddress(), req.getPhone());
        return ResponseEntity.ok(customer);
    }
    GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable UUID id){
        Customer customer = customerService.getCustomer(id);
        return customer!= null ? ResponseEntity.ok(customer) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable UUID id, @RequestBody UpdateCustomerRequest req){
        Customer existing = customerService.getCustomer(id);
        if(existing !=null){
            return ResponseEntity.notFound().build();
        }
        existing.setName(req.getName());
        existing.setLastName(req.getLastName());
        existing.setEmail(req.getEmail());
        existing.setAddress(req.getAddress());
        existing.setPhone(req.getPhone());
        Customer updated = customerService.updateCustomer(existing);
        return ResponseEntity.ok(updated);
    }





    public static class CreateCustomerRequest{
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

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        String name;
        String lastName;
        String email;
        String address;
        String phone;


    }
    public static class UpdateCustomerRequest extends CreateCustomerRequest{}
}
