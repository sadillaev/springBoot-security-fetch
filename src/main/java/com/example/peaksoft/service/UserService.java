package com.example.peaksoft.service;

import com.example.peaksoft.entity.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User getByName(String name);

    User getById(Long id);

    User updateUser(User user);

    void deleteUser(User user);

    User save(User user);

    User findByUsername(String s);
}
