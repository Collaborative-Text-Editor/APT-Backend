package com.apt.docs.repository;

//import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.apt.docs.model.document;
import com.apt.docs.model.document_permission;
// import com.apt.docs.service.DocumentPermissionId;

public interface document_permission_repository extends JpaRepository<document_permission, Integer> {

    Iterable<document_permission> findByDocumentIdAndPermissionType(int document_id, String string);

    void deleteByDocumentIdAndUserId(int documentId, int userId);

    Iterable<document_permission> findByUser_IdAndPermissionType(int id, String string);

    Iterable<document_permission> findByUserIdAndPermissionType(int user_id, String string);
    void deleteByDocumentId(int id);

}
