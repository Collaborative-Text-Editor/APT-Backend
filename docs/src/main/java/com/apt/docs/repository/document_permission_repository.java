package com.apt.docs.repository;

import java.util.List;

import com.apt.docs.model.document;
import com.apt.docs.model.user;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.apt.docs.model.document_permission;

public interface document_permission_repository extends JpaRepository<document_permission, Integer> {

    Iterable<document_permission> findByDocumentIdAndPermissionType(int document_id, String string);
    @Query("SELECT dp.document FROM document_permission dp WHERE dp.user = :user AND dp.permissionType = 'editor'")
    List<document> findByEditor(@Param("user") user user);

}
