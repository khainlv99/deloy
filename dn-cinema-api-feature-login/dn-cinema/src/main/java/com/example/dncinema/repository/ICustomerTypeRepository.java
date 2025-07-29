package com.example.dncinema.repository;

import com.example.dncinema.model.TypeCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICustomerTypeRepository extends JpaRepository<TypeCustomer, Integer> {
}
