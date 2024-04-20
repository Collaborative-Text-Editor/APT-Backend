package com.apt.docs.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.apt.docs.model.document;
import com.apt.docs.repository.document_repository;

@Service
public class document_service {
    private final document_repository documentRepository;

    public document_service(document_repository documentRepository) {
        this.documentRepository = documentRepository;
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

    public document saveDocument(String title, byte[] content, String ownerUsername) {
        document document = new document();
        document.setCreatedAt(LocalDateTime.now());
        document.setTitle(title);
        document.setContent(content);
        document.setOwnerUsername(ownerUsername);
        documentRepository.save(document);
        return document;
    }
}
