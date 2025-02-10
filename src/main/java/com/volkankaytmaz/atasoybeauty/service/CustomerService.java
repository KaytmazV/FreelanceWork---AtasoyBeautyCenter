package com.volkankaytmaz.atasoybeauty.service;


import com.volkankaytmaz.atasoybeauty.model.CustomerEntity;
import com.volkankaytmaz.atasoybeauty.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private final CustomerRepository repository;
    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public List<CustomerEntity> getAllCustomers() {
        return repository.findAll();
    }

    public CustomerEntity getCustomerById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Söz konusu " + id + " hatalı."));
    }

    public CustomerEntity createCustomer(CustomerEntity customer) {
        if (customer == null || customer.getEmail() == null ) {
            throw new IllegalArgumentException("Tekrar deneyin");

        }
        if (customer.getPhoneNumber() == null || customer.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Tekrar deneyin");

        } return repository.save(customer);
    }

    public Optional<CustomerEntity> customerUpdate(CustomerEntity customer) {
        Optional<CustomerEntity> customerEntity = repository.findById(customer.getId());
        if (customerEntity.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(repository.save(customer));
    }

    public String deleteAllCustomers() {
        repository.deleteAll();
        return "All customers have been deleted.";
    }

    public String deleteCustomerById(Long id) {
        repository.deleteById(id);
        return "Customer with id " + id + " has been deleted";
    }

}
