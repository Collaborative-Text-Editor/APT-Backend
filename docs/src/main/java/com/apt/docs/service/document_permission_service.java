package com.apt.docs.service;
import org.springframework.stereotype.Service;

import com.apt.docs.model.document;
import com.apt.docs.model.document_permission;
import com.apt.docs.model.user;
import com.apt.docs.repository.document_permission_repository;
import com.apt.docs.repository.document_repository;
import com.apt.docs.repository.user_repository;

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

    // public void saveDocumentPermission(int document_id,String username,String permission_type) {
    //     document_permission documentPermission = new document_permission();
    //     documentPermission.setDocumentId(document_id);
    //     documentPermission.setUsername(username);
    //     documentPermission.setPermissionType(permission_type);
    //     documentPermissionRepository.save(documentPermission);
    // }

    public Iterable<document_permission> getDocumentPermissions() {
        return documentPermissionRepository.findAll();
    }

    public document_permission getDocumentPermissionById(int id) {
        return documentPermissionRepository.findById(id).orElse(null);
    }
    // //get owner of doc
    // public Iterable<document_permission> getOwnerOfDocument(int document_id) {
    // return
    // documentPermissionRepository.findByDocument_idAndPermission_type(document_id,
    // "owner");
    // }
    // //get editors of doc
    // public Iterable<document_permission> getEditorsOfDocument(int document_id) {
    // return
    // documentPermissionRepository.findByDocument_idAndPermission_type(document_id,
    // "editor");
    // }
    // remove editor from doc
    // public void removeEditorFromDocument(int document_id, int user_id) {
    // Iterable<document_permission> editors =
    // documentPermissionRepository.findByDocument_idAndPermission_type(document_id,
    // "editor");
    // for (document_permission editor : editors) {
    // if (editor.getUser_id() == user_id) {
    // documentPermissionRepository.deleteById(editor.getId());
    // }
    // }
    // }

    public Iterable<document_permission> getEditorsByDocumentId(int id) {
        return documentPermissionRepository.findByDocumentIdAndPermissionType(id, "editor");
    }

    public Iterable<document_permission> getOwnerOfDocument(int id) {
        return documentPermissionRepository.findByDocumentIdAndPermissionType(id, "owner");
    }

    public void saveDocumentPermission(int documentId, String username, String permissionType) {
        // Find the document by ID
        document document = documentRepository.findById(documentId)
                .orElseThrow(() -> new IllegalArgumentException("Document not found with id: " + documentId));

        // Find the user by username
        user user = userRepository.findByUsername(username);
        if(user == null) {
            throw new IllegalArgumentException("User not found with username: " + username);
        }
        System.out.println("user: " + user);

        // Create a new DocumentPermission object
        document_permission documentPermission = new document_permission();
        documentPermission.setDocument(document);
        documentPermission.setUser(user);
        documentPermission.setPermissionType(permissionType); // Assuming PermissionType enum exists

        // Save the document permission
        documentPermissionRepository.save(documentPermission);
    }

}
