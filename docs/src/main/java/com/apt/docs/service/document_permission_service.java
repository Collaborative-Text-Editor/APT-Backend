package com.apt.docs.service;

import org.springframework.stereotype.Service;

import com.apt.docs.model.document_permission;
import com.apt.docs.repository.document_permission_repository;

@Service
public class document_permission_service {
    private final document_permission_repository documentPermissionRepository;
    public document_permission_service(document_permission_repository documentPermissionRepository) {
        this.documentPermissionRepository = documentPermissionRepository;
    }
    public void deleteDocumentPermissionById(int id) {
        documentPermissionRepository.deleteById(id);
    }
    public void saveDocumentPermission(int document_id, int user_id, String permission_type) {
        document_permission documentPermission = new document_permission();
        documentPermission.setDocument_id(document_id);
        documentPermission.setUser_id(user_id);
        documentPermission.setPermission_type(permission_type);
        documentPermissionRepository.save(documentPermission);
    }
    public Iterable<document_permission> getDocumentPermissions() {
        return documentPermissionRepository.findAll();
    }
    public document_permission getDocumentPermissionById(int id) {
        return documentPermissionRepository.findById(id).orElse(null);
    }
    // //get owner of doc
    // public Iterable<document_permission> getOwnerOfDocument(int document_id) {
    //     return documentPermissionRepository.findByDocument_idAndPermission_type(document_id, "owner");
    // }
    // //get editors of doc
    // public Iterable<document_permission> getEditorsOfDocument(int document_id) {
    //     return documentPermissionRepository.findByDocument_idAndPermission_type(document_id, "editor");
    // }
    //remove editor from doc
    // public void removeEditorFromDocument(int document_id, int user_id) {
    //     Iterable<document_permission> editors = documentPermissionRepository.findByDocument_idAndPermission_type(document_id, "editor");
    //     for (document_permission editor : editors) {
    //         if (editor.getUser_id() == user_id) {
    //             documentPermissionRepository.deleteById(editor.getId());
    //         }
    //     }
    // }


    
    
}
