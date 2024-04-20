package com.apt.docs.service;

import org.springframework.stereotype.Service;

import com.apt.docs.model.user;
import com.apt.docs.repository.user_repository;

@Service
public class user_service {
    private final user_repository userRepository;

    public user_service(user_repository userRepository) {
        this.userRepository = userRepository;
    }

    public Iterable<user> getUsers() {
        return userRepository.findAll();
    }

    public user getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public user getUserByUsername(String username) {
        user user = userRepository.findByUsername(username);
        System.out.println("user: " + user);
        return userRepository.findByUsername(username);
    }

    public void deleteUserById(int id) {
        userRepository.deleteById(id);
    }

    public user saveUser(String username, String password) {
        user user = new user();
        user.setUsername(username);
        user.setPassword(password);
        userRepository.save(user);
        return user;
    }

    public void deleteUserByUsername(String username) {
        user user = userRepository.findByUsername(username);
        userRepository.delete(user);
    }
}
