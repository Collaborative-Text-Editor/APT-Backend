package com.apt.docs.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.apt.docs.model.document;
import com.apt.docs.model.user;
import com.apt.docs.repository.document_repository;
import com.apt.docs.repository.user_repository;

@Service
public class document_service {
    private final document_repository documentRepository;
    private final user_repository userRepository;

    public document_service(document_repository documentRepository, user_repository userRepository) {
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

    public document saveDocument(String title, byte[] content, int ownerId) {
        document document = new document();
        user user = userRepository.findById(ownerId).orElse(null);
        document.setCreatedAt(LocalDateTime.now());
        document.setTitle(title);
        document.setContent(content);
        document.setOwner(user);
        documentRepository.save(document);
        return document;
    }
}
