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
        System.out.println("user: " + user);    
        System.out.println("pass " +  passwordEncoder.encode(password));
        userRepository.save(user);
        return user;
    }

    public void deleteUserByUsername(String username) {
        user user = userRepository.findByUsername(username);
        userRepository.delete(user);
    }



    public user loginUser(user user) {
        user foundUser = userRepository.findByUsername(user.getUsername());
        if (foundUser == null) {
            return null;
        }
        boolean passwordMatches = checkUserPassword(user.getPassword(), foundUser.getPassword());
        if (!passwordMatches) {
            return null;
        }
        return foundUser;
    }

    public boolean checkUserPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);

    public user getUser(String username, String password) {
        user user = userRepository.findByUsernameAndPassword(username, password);
        return user;

    }
}
