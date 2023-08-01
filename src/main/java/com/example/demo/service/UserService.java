package com.example.demo.service;


import com.example.demo.model.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserService {
    public List<User> getAllUsers();

    public User getUserById(long id);

    public void save(User user);

    public void update(User updatedUser);

    public void delete(long id);

    public User getUserByUsername(String username);

}
