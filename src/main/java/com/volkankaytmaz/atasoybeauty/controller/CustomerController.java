package com.volkankaytmaz.atasoybeauty.controller;

import com.volkankaytmaz.atasoybeauty.model.CustomerEntity;
import com.volkankaytmaz.atasoybeauty.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {


    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<CustomerEntity>> getAllCustomers(){
        List<CustomerEntity> customer = customerService.getAllCustomers();
        if (customer == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(customer, HttpStatus.OK);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<CustomerEntity> getCustomerById(@PathVariable long id) {
        CustomerEntity customer = customerService.getCustomerById(id);
        if (customer == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(customer, HttpStatus.OK);
        }
    }
    @PostMapping("/create")
    public ResponseEntity<CustomerEntity> createCustomer(@Valid @RequestBody CustomerEntity customer){
        CustomerEntity customerEntity = customerService.createCustomer(customer);
        return new ResponseEntity<>(customerEntity, HttpStatus.CREATED);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteAllCustomers(){
        customerService.deleteAllCustomers();
        return new ResponseEntity<>("All customers deleted", HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCustomerById(long id){
        if (customerService.getCustomerById(id) == null) {
            return new ResponseEntity<>("Customer not found", HttpStatus.NOT_FOUND);
        } else {
            customerService.deleteCustomerById(id);
        }
        return new ResponseEntity<>("Customer deleted", HttpStatus.OK);
    }
    @PutMapping("/update")
    public ResponseEntity<Optional<CustomerEntity>> customerUpdate(@Valid @RequestBody CustomerEntity customer){
        Optional<CustomerEntity> customerEntity = customerService.customerUpdate(customer);
        if (customerEntity.isPresent()) {
            return new ResponseEntity<>(customerEntity, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(customerEntity, HttpStatus.NOT_FOUND);
        }
    }

}
