package com.apt.docs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apt.docs.model.document;
import com.apt.docs.model.user;

public interface document_repository extends JpaRepository<document, Integer>{

    Iterable<document> findByOwner(user user);

   
    
}
