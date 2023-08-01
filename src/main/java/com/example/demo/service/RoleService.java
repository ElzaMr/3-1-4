package com.example.demo.service;

import com.example.demo.model.Role;
import com.example.demo.model.User;

import java.util.List;
import java.util.Optional;

public interface RoleService {

    void saveRole(Role role);
    List<Role> getAllRoles();
    Optional<Role> findById(long id);
}
