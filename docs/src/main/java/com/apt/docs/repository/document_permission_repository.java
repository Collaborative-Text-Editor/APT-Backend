package com.apt.docs.repository;
import org.springframework.data.repository.CrudRepository;

import com.apt.docs.model.document_permission;


public interface document_permission_repository extends CrudRepository< document_permission, Integer> {

    // Iterable<document_permission> findByDocument_idAndPermission_type(int document_id, String string);
    
}
