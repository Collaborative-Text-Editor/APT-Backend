package com.apt.docs.service;

import org.springframework.stereotype.Service;

import com.apt.docs.model.document;
import com.apt.docs.model.document_permission;
import com.apt.docs.model.user;
import com.apt.docs.repository.document_permission_repository;
import com.apt.docs.repository.document_repository;
import com.apt.docs.repository.user_repository;

import jakarta.transaction.Transactional;

import com.apt.docs.model.document_permission_id;

@Service
public class document_permission_service {
    private final document_permission_repository documentPermissionRepository;
    private final document_repository documentRepository;
    private final user_repository userRepository;

    public document_permission_service(document_permission_repository documentPermissionRepository,
            document_repository documentRepository, user_repository userRepository) {
        this.documentPermissionRepository = documentPermissionRepository;
        this.documentRepository = documentRepository;
        this.userRepository = userRepository;
    }

    public void deleteDocumentPermissionById(int id) {
        documentPermissionRepository.deleteById(id);
    }

    public Iterable<document_permission> getDocumentPermissions() {
        return documentPermissionRepository.findAll();
    }

    public document_permission getDocumentPermissionById(int id) {
        return documentPermissionRepository.findById(id).orElse(null);
    }

    public Iterable<document_permission> getEditorsByDocumentId(int id) {
        return documentPermissionRepository.findByDocumentIdAndPermissionType(id, "editor");
    }

    public Iterable<document_permission> getOwnerOfDocument(int id) {
        return documentPermissionRepository.findByDocumentIdAndPermissionType(id, "owner");
    }

    public void saveDocumentPermission(int documentId, String username, String permissionType) {
        document document = documentRepository.findById(documentId)
                .orElseThrow(() -> new IllegalArgumentException("Document not found with id: " + documentId));

        user user = userRepository.findByUsername(username);
        if (user == null) {
            throw new IllegalArgumentException("User not found with username: " + username);
        }

        document_permission documentPermission = new document_permission();
        document_permission_id dpID = new document_permission_id();
        dpID.setDocumentId(document.getId());
        dpID.setUser_Id(user.getId());
        documentPermission.setId(dpID);
        documentPermission.setDocument(document);
        documentPermission.setUser(user);
        documentPermission.setPermissionType(permissionType);

        documentPermissionRepository.save(documentPermission);
    }

    @Transactional
    public void deleteDocumentPermission(int id, String username) {
        user user = userRepository.findByUsername(username);
        if (user != null) {
            documentPermissionRepository.deleteByDocumentIdAndUserId(id, user.getId());
        }
        if (user == null) {
            throw new IllegalArgumentException("User not found with username: " + username);
        }
    }

    public Iterable<document_permission> getEditorDocumentsByUsername(String username) {
        user user = userRepository.findByUsername(username);

        return documentPermissionRepository.findByUser_IdAndPermissionType(user.getId(), "editor");
    }

    public Iterable<document_permission> getViewerDocumentsByUsername(String username) {
        user user = userRepository.findByUsername(username);
        return documentPermissionRepository.findByUser_IdAndPermissionType(user.getId(), "viewer");
    }

}
