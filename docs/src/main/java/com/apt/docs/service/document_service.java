package com.apt.docs.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.apt.docs.RGA;
import com.apt.docs.model.document;
import com.apt.docs.model.document_permission;
import com.apt.docs.model.user;
import com.apt.docs.repository.document_permission_repository;
import com.apt.docs.repository.document_repository;
import com.apt.docs.repository.user_repository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.apt.docs.repository.document_permission_repository;

@Service
public class document_service {
    private final document_permission_repository documentPermissionRepository;
    private final document_repository documentRepository;
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
        Iterable<document_permission> permissions = documentPermissionRepository.findByUserIdAndPermissionType(id,
                "editor");

        List<document> documents = new ArrayList<>();
        for (document_permission permission : permissions) {
            documents.add(permission.getDocument());
        }

        return documents;
    }

    public List<document> getViewedDocsByUserId(int id) {
        Iterable<document_permission> permissions = documentPermissionRepository.findByUserIdAndPermissionType(id,
                "viewer");

        List<document> documents = new ArrayList<>();
        for (document_permission permission : permissions) {
            documents.add(permission.getDocument());
        }

        return documents;
    }

    public void deleteDocumentByID(int id) {
        dPermissionService.deleteDocumentPermissionByDocumentId(id);
        documentRepository.deleteById(id);
    }

    public void changeDocumentTitle(int id, String title) {
        document document = documentRepository.findById(id).orElse(null);
        document.setTitle(title);
        documentRepository.save(document);

    }

    public void applyAddAfterOperation(int documentId, String id, char value,boolean bold,boolean italic) throws JsonProcessingException {
        document document = documentRepository.findById(documentId)
                .orElseThrow(() -> new IllegalArgumentException("Document not found"));
        // apply the operation and get the new state
        RGA rga = RGA.fromByteArray(document.getContent());
        byte[] newState = rga.addAfter(id, value, bold,italic);
        // set the content of the document with the new state
        document.setContent(newState);
        // save the updated document back to the database
        documentRepository.save(document);
    }

    public void applyRemoveOperation(int documentId, String id) throws JsonProcessingException {
        // get the document from the database
        document document = documentRepository.findById(documentId)
                .orElseThrow(() -> new IllegalArgumentException("Document not found"));
        // convert the content of the document back into an RGA object
        RGA rga = RGA.fromByteArray(document.getContent());
        // apply the operation and get the new state
        byte[] newState = rga.remove(id);
        // set the content of the document with the new state
        document.setContent(newState);
        // save the updated document back to the database
        documentRepository.save(document);
    }

    public String getDocumentContentAsString(int documentId) throws JsonProcessingException {
        // get the document from the database
        document document = documentRepository.findById(documentId)
                .orElseThrow(() -> new IllegalArgumentException("Document not found"));
        // convert the content of the document back into an RGA object
        RGA rga = RGA.fromByteArray(document.getContent());
        // convert the RGA object to a string of text and return it
        return rga.toText();
    }

}
