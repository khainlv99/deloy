package com.example.dncinema.service.roles;

import com.example.dncinema.model.Roles;

public interface IRolesService {
    Roles findRolesByName(String name);
}
