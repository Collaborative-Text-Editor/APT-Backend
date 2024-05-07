package com.apt.docs.service;

import org.springframework.stereotype.Service;

import com.apt.docs.model.user;
import com.apt.docs.repository.user_repository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class user_service {
    private final user_repository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;


    public user_service(user_repository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
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
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
        return user;
    }

    public void deleteUserByUsername(String username) {
        user user = userRepository.findByUsername(username);
        userRepository.delete(user);
    }


    public boolean userValid(user user) {
        user user1 = userRepository.findByUsername(user.getUsername());
        if (user1 == null) {
            return false;
        }
        return checkUserPassword(user.getPassword(), user1.getPassword());
    }

    public boolean checkUserPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
