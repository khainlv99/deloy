package com.example.dncinema.repository;

import com.example.dncinema.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IRolesRepository extends JpaRepository<Roles, Integer> {
    Roles findByNameRoles(String name);
}