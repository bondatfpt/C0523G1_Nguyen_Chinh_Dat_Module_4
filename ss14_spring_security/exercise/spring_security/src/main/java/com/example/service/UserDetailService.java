package com.example.service;

import com.example.model.AppRole;
import com.example.model.AppUser;
import com.example.repository.IUserRepository;
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
public class UserDetailService implements UserDetailsService {
    @Autowired
    private IUserRepository iUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = iUserRepository.findAppUserByUsername(username);
        System.out.println(appUser);
        if (appUser == null) {
            System.out.println("Not found: " + username);
            throw new UsernameNotFoundException("User " + username + " was not found in the database");
        }
        Set<AppRole> appRoles = appUser.getRole();
        List<String> roleNames = new ArrayList<>();
        for (AppRole appRole : appRoles) {
            roleNames.add(appRole.getRoleName());
        }
        System.out.println("role: "+roleNames);
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<GrantedAuthority>();
        if (roleNames != null) {
            for (String role : roleNames) {
                GrantedAuthority authority = new SimpleGrantedAuthority(role);
                grantedAuthorityList.add(authority);
            }
        }
        System.out.println("ADDED"+grantedAuthorityList);
        UserDetails userDetails = (UserDetails) new User(appUser.getUsername()
                ,appUser.getPassword(),grantedAuthorityList);
        System.out.println(userDetails );
        return userDetails;
    }
}
