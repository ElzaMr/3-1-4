package com.example.demo.repo;

import com.example.demo.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

public interface RoleRepo extends JpaRepository<Role, Long> {
}
