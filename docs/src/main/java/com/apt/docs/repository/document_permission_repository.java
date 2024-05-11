package com.apt.docs.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.apt.docs.model.document_permission;

public interface document_permission_repository extends JpaRepository<document_permission, Integer> {

    Iterable<document_permission> findByDocumentIdAndPermissionType(int document_id, String string);
    Iterable<document_permission> findByUserIdAndPermissionType(int user_id, String string);


}
