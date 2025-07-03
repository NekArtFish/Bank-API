package com.example.bankapi;

import com.example.bankapi.model.entities.Customer;
import com.example.bankapi.model.services.CustomerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CustomerServiceTest {
    private CustomerService customerService;

    @BeforeEach
    void setUp(){
        customerService = new CustomerService();
    }

    @Test
    void testCreateCustomer(){
        Customer customer = customerService.createCustomer("John", "Doe", "john.doe@example.com",
                "123 Main street","123456789");
        Assertions.assertNotNull(customerService.getCustomer(customer.getId()), "Клиент должен быть создан!");
    }
    @Test
    void updateCustomer() throws InterruptedException {
        Customer customer = customerService.createCustomer("John", "Doe", "john.doe@example.com",
                "123 Main street","123456789");
        customer.setEmail("new.doe@example.com");
        Thread.sleep(2000);
        Customer updatedCustomer = customerService.updateCustomer(customer);
        Assertions.assertEquals("new.doe@example.com", updatedCustomer.getEmail());
        Assertions.assertTrue(updatedCustomer.getUpdatedAt().isAfter(updatedCustomer.getCreatedAt()));
    }

}
