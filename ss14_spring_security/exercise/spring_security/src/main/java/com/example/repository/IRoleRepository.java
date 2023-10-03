package com.example.repository;

import com.example.model.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IRoleRepository extends JpaRepository<AppRole,Integer> {
}
