package com.example.cs4.role.service;

import com.example.cs4.role.model.Role;
import com.example.cs4.role.repository.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService implements IRoleService{
    @Autowired
    private IRoleRepository iRoleRepository;
    @Override
    public Role findRoleByRoleName(String roleName) {
        return iRoleRepository.findRoleByRoleName(roleName);
    }

    @Override
    public void save(Role role) {
        iRoleRepository.save(role);
    }
}
