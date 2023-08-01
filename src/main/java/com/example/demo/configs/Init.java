package com.example.demo.configs;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repo.RoleRepo;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.web.servlet.oauth2.login.OAuth2LoginSecurityMarker;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component

public class Init {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public Init(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;

    }

    @PostConstruct
    public void initUsers() {

        Role adminRole = new Role("ADMIN");
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        roleService.saveRole(adminRole);

        Role userRole = new Role("USER");
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(userRole);
        roleService.saveRole(userRole);

        User admin = new User("admin", "admin", 20, "123", adminRoles);
        userService.save(admin);

        User user = new User("user", "user", 20, "123", userRoles);
        userService.save(user);
    }
}

