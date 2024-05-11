package com.apt.docs.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.mapping.List;
import org.springframework.stereotype.Service;

import com.apt.docs.model.document;
import com.apt.docs.model.document_permission;
import com.apt.docs.model.user;
import com.apt.docs.repository.document_permission_repository;
import com.apt.docs.repository.document_repository;
import com.apt.docs.repository.user_repository;
import com.apt.docs.repository.document_permission_repository;



@Service
public class document_service {
    private final document_permission_repository documentPermissionRepository;
    private final document_repository documentRepository;
    private final document_permission_repository documentPermissionRepository;
    private final user_repository userRepository;
    private final document_permission_service dPermissionService;


    public document_service(document_repository documentRepository, user_repository userRepository,
            document_permission_repository documentPermissionRepository,
            document_permission_service dPermissionService) {

        this.documentPermissionRepository = documentPermissionRepository;
        this.documentRepository = documentRepository;
        this.userRepository = userRepository;
        this.dPermissionService = dPermissionService;
    }

    public Iterable<document> getDocuments() {
        return documentRepository.findAll();
    }

    public document getDocumentById(int id) {
        return documentRepository.findById(id).orElse(null);
    }

    public document saveDocument(String title, byte[] content, String username) {
        document document = new document();
        user user = userRepository.findByUsername(username);
        document.setCreatedAt(LocalDateTime.now());
        document.setTitle(title);
        document.setContent(content);
        document.setOwner(user);
        documentRepository.save(document);
        return document;
    }

    public Iterable<document> getDocumentsByUsername(String username) {
        user user = userRepository.findByUsername(username);
        System.out.println("user: " + user);
        return documentRepository.findByOwner(user);
    }

    public Iterable<document> getOwnedDocsByUserId(int id) {
        user user = userRepository.findById(id).orElse(null);
        return documentRepository.findByOwner(user);
    }


    public List<document> getEditedDocsByUserId(int id) {
        Iterable<document_permission> permissions = documentPermissionRepository.findByUserIdAndPermissionType(id, "editor");
        
        List<document> documents = new ArrayList<>();
        for (document_permission permission : permissions) {
            documents.add(permission.getDocument());
        }
        
        return documents;
    }

    public List<document> getViewedDocsByUserId(int id) {
        Iterable<document_permission> permissions = documentPermissionRepository.findByUserIdAndPermissionType(id, "viewer");
        
        List<document> documents = new ArrayList<>();
        for (document_permission permission : permissions) {
            documents.add(permission.getDocument());
        }
        
        return documents;

    public void deleteDocumentByID(int id) {
        dPermissionService.deleteDocumentPermissionByDocumentId(id);
        documentRepository.deleteById(id);
    }

    public void changeDocumentTitle(int id, String title) {
        document document = documentRepository.findById(id).orElse(null);
        document.setTitle(title);
        documentRepository.save(document);

    }

}
