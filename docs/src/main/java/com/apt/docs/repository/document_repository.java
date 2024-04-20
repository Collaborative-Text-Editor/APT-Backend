package com.apt.docs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.apt.docs.model.document;

public interface document_repository extends JpaRepository<document, Integer>{
    
}
