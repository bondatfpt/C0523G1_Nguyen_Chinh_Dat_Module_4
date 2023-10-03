package com.example.model;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "app_roles")
public class AppRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String roleName;
    private boolean isDeleted;
    @ManyToMany
    @JoinTable(name = "user_role")
    @JoinColumn(columnDefinition = "role_id",referencedColumnName = "user_id")
    private Set<AppUser> user;

    public AppRole() {
    }

    public AppRole( String roleName, boolean isDeleted, Set<AppUser> user) {
        this.roleName = roleName;
        this.isDeleted = isDeleted;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public Set<AppUser> getUser() {
        return user;
    }

    public void setUser(Set<AppUser> user) {
        this.user = user;
    }
}
