package com.example.service;

import com.example.model.User;
import com.example.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService{
    @Autowired
    private IUserRepository iUserRepository;
    @Override
    public boolean save(User user) {
        try {
            iUserRepository.save(user);
        }catch (Exception e){
            return false;
        }
        return true;
    }
}
