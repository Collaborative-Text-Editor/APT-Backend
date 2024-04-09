package com.apt.docs.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.apt.docs.model.user;
import com.apt.docs.service.user_service;

import jakarta.validation.Valid;

@RestController
public class user_controller {;
    private final user_service userService;
    public user_controller(user_service userService) {
        this.userService = userService;
    }
    
    @GetMapping("/user")
    public Iterable<user> getUsers(){
        return userService.getUsers();
    }

    @GetMapping("/user/{id}")
    public user getUserById(@PathVariable int id){
        return userService.getUserById(id);
    }

    @DeleteMapping("/user/{id}")
    public void deleteUserById(@PathVariable int id){
        userService.deleteUserById(id);
    }

    @PostMapping("/user")
    public user saveUser(@RequestBody @Valid user user){
        return userService.saveUser(user.getUsername(), user.getPassword());
    }


}
