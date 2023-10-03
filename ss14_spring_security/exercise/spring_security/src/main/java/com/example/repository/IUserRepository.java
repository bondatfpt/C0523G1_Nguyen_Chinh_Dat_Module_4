package com.example.repository;

import com.example.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IUserRepository extends JpaRepository<AppUser,Integer> {
        AppUser findAppUserByUsername (String username);
//        @Query(value = "select * from app_user where username :username",nativeQuery = true)
//        public AppUser findAppUserByUsername (@Param("username") String username);
}
