package com.volkankaytmaz.atasoybeauty.service;

import com.volkankaytmaz.atasoybeauty.model.CustomerEntity;
import com.volkankaytmaz.atasoybeauty.repository.CustomerRepository;
import com.volkankaytmaz.atasoybeauty.security.JwtAuthenticationFilter;
import io.jsonwebtoken.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CustomerService implements UserDetailsService {
    @Autowired
    private final CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        CustomerEntity customer = repository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        return new User(customer.getEmail(), customer.getPassword(), new ArrayList<>());
    }

    public Optional<CustomerEntity> findByEmail(String email) {
        return repository.findByEmail(email);
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
        }
        return repository.save(customer);
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