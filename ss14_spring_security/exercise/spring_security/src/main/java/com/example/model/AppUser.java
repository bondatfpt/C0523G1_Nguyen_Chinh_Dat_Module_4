package com.example.model;

import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import java.util.Set;
@Lazy
@Entity
@Table(name = "app_users")
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String password;
    private boolean isDeleted;
    @ManyToMany(fetch = FetchType.EAGER  ,mappedBy = "user",cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    private Set<AppRole> role;

    public AppUser() {
    }

    public AppUser(String username, String password, boolean isDeleted, Set<AppRole> role) {
        this.username = username;
        this.password = password;
        this.isDeleted = isDeleted;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public Set<AppRole> getRole() {
        return role;
    }

    public void setRole(Set<AppRole> role) {
        this.role = role;
    }


    @Override
    public String toString() {
        return "AppUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
