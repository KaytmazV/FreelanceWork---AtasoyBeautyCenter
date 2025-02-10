package com.volkankaytmaz.atasoybeauty.repository;

import com.volkankaytmaz.atasoybeauty.model.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
}
