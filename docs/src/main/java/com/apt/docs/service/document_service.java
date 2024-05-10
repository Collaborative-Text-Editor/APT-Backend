package com.apt.docs.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.apt.docs.model.document;
import com.apt.docs.model.user;
import com.apt.docs.repository.document_permission_repository;
import com.apt.docs.repository.document_repository;
import com.apt.docs.repository.user_repository;

@Service
public class document_service {
    private final document_permission_repository documentPermissionRepository;
    private final document_repository documentRepository;
    private final user_repository userRepository;

    public document_service(document_repository documentRepository, user_repository userRepository) {
        this.documentPermissionRepository = null;
        this.documentRepository = documentRepository;
        this.userRepository = userRepository;
    }

    public Iterable<document> getDocuments() {
        return documentRepository.findAll();
    }

    public document getDocumentById(int id) {
        return documentRepository.findById(id).orElse(null);
    }

    public void deleteDocumentById(int id) {
        documentRepository.deleteById(id);
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

    public Iterable<document> getEditedDocsByUserId(int id) {
        user user = userRepository.findById(id).orElse(null);
        return documentPermissionRepository.findByEditor(user);
    }

}
