package com.example.demo.model;


import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.Range;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = {"username"}))
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username", unique = true)
    @NotBlank(message = "Name should not be empty")
    @Size(min = 2, max = 20, message = "name 1")
    private String username;

    @Column(name = "surname")
    @NotBlank(message = "Surname should not be empty")
    @Size(min = 2, max = 20)

    private String surname;
    @Column(name = "age")
    @Range(min = 0, max = 150)
    private int age;

    @Column(name = "pass")
    @NotBlank(message = "Pass should not be empty")
    @Size(min = 2)
    private String pass;

    @ManyToMany
    @Fetch(FetchMode.JOIN)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;


    public User(String username, String surname, int age, String pass, Set<Role> roles) {
        this.username = username;
        this.surname = surname;
        this.age = age;
        this.pass = pass;
        this.roles = roles;
    }

    public User() {
    }

    public boolean isAdmin() {
        return roles.contains(new Role("ADMIN"));
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return this.pass;
    }

    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "User "
                + username +
                ", with roles: " + roles.stream().toList().get(0).getName();
    }

    public String toStringRoles() {
        StringBuilder x = new StringBuilder();
        roles.stream().map(Role::getName).forEach(s -> x.append(s).append(" "));
        return x.toString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
