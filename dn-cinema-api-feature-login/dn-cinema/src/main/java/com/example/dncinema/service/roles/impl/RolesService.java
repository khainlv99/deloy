package com.example.dncinema.service.roles.impl;

import com.example.dncinema.model.Roles;
import com.example.dncinema.repository.IRolesRepository;
import com.example.dncinema.service.roles.IRolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolesService implements IRolesService {
    @Autowired
    IRolesRepository rolesRepository;
    @Override
    public Roles findRolesByName(String name) {
        return rolesRepository.findByNameRoles(name);
    }
}
