package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public User getUserById(long id) {
        User user = userRepo.findById(id).orElse(null);
        return user;
    }

    @Override
    @Transactional
    public void save(User user) {
        user.setPass(passwordEncoder.encode(user.getPass()));
        userRepo.save(user);
    }

    @Override
    @Transactional
    public void update(User updatedUser) {
        userRepo.save(updatedUser);
    }

    @Override
    @Transactional
    public void delete(long id) {
        userRepo.deleteById(id);
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepo.getUserByUsername(username);
    }
}
