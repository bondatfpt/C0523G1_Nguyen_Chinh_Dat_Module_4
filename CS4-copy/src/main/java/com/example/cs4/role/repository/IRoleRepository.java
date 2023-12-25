package com.example.cs4.role.repository;

import com.example.cs4.role.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepository extends JpaRepository<Role,Integer> {
    Role findRoleByRoleName (String roleName);
}
