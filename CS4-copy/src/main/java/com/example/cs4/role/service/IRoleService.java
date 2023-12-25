package com.example.cs4.role.service;

import com.example.cs4.role.model.Role;

public interface IRoleService {
    Role findRoleByRoleName (String roleName);
    void save (Role role);
}
