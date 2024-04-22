package com.apt.docs.repository;

import com.apt.docs.model.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface user_repository extends JpaRepository<user, Integer> {

    user findByUsername(String username);
}
