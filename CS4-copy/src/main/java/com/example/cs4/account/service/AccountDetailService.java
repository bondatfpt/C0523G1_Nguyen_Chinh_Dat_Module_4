package com.example.cs4.account.service;

import com.example.cs4.account.model.Account;
import com.example.cs4.account.repository.IAccountRepository;
import com.example.cs4.customer.model.Customer;
import com.example.cs4.customer.service.ICustomerService;
import com.example.cs4.role.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class AccountDetailService implements UserDetailsService {
    @Autowired
    private IAccountRepository iAccountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = iAccountRepository.findAccountByUserName(username);
        System.out.println(account);
        if (account == null || account.isDeleted() || account.isActive() == false ) {
            System.out.println("Not found: " + username);
            throw new UsernameNotFoundException("User " + username + " was not found in the database");
        }
        Set<Role> roles = account.getRole();
        System.out.println("Role: " + roles);
        List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
        if (roles != null) {
            for (Role role : roles) {
                GrantedAuthority authority = new SimpleGrantedAuthority(role.getRoleName());
                grantedAuthorities.add(authority);
            }
        }
        System.out.println("Added " + grantedAuthorities);
        UserDetails userDetails =  new User(account.getUserName(),account.getPassword(),grantedAuthorities);
        System.out.println("UserDetails: " + userDetails);
        return userDetails;
    }

    public void save (Account account){
        iAccountRepository.save(account);
    }
}
