package com.apt.docs.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.apt.docs.model.document;
import com.apt.docs.model.document_permission;
import com.apt.docs.service.document_permission_service;
import com.apt.docs.service.document_service;

@RestController
public class document_controller {
    private final document_service documentService;
    private final document_permission_service documentPermissionService;

    public document_controller(document_service documentService,
            document_permission_service documentPermissionService) {
        this.documentService = documentService;
        this.documentPermissionService = documentPermissionService;
    }

    @GetMapping("/document")
    public Iterable<document> getDocuments() {
        return documentService.getDocuments();
    }

    @GetMapping("/document/{id}")
    public document getDocumentById(@PathVariable int id) {
        return documentService.getDocumentById(id);
    }
    @GetMapping("/document/{id}/editors")
    public Iterable<document_permission> getEditorsByDocumentId(@PathVariable int id) {
        return documentPermissionService.getEditorsByDocumentId(id);
    }
    //get owner
    @GetMapping("/document/{id}/owner")
    public Iterable<document_permission> getOwnerOfDocument(@PathVariable int id) {
        return documentPermissionService.getOwnerOfDocument(id);
    }
    @DeleteMapping("/document/{id}")
    public void deleteDocumentById(@PathVariable int id) {
        documentService.deleteDocumentById(id);
    }

    @PostMapping("/document")
    public document saveDocument(@RequestBody document document) {
        return documentService.saveDocument(document.getTitle(), document.getContent(),
                document.getOwner().getId());
    }

    @PostMapping("/document/{id}/editor/{username}")
    public void addEditorToDocument(@PathVariable int id, @PathVariable String username) {
        documentPermissionService.saveDocumentPermission(id, username, "editor");
    }

}
// get owner of doc
// get editors of doc
