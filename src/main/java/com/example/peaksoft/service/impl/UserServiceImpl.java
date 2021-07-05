package com.example.peaksoft.service.impl;
import com.example.peaksoft.entity.User;
import com.example.peaksoft.repository.UserRepository;
import com.example.peaksoft.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public User getById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    @Transactional
    public User getByName(String name) {
       return userRepository.getByName(name);
    }

    @Override
    @Transactional
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }


    @Override
    @Transactional
    public User findByUsername(String s) {
        List<User>users = getAllUsers();
        return users.stream().filter(x -> x.getName().equals(s)).findAny().orElse(null);
    }

    @Override
    public User findByEmail(String email) {
        List<User>users = getAllUsers();
        return users.stream().filter(x -> x.getEmail().equals(email)).findAny().orElse(null);
    }
}
