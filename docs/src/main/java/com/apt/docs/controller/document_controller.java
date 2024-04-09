package com.apt.docs.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.apt.docs.model.document;
import com.apt.docs.service.document_service;

@RestController
public class document_controller {
    private final document_service documentService;
    public document_controller(document_service documentService) {
        this.documentService = documentService;
    }
    @GetMapping("/document")
    public Iterable<document> getDocuments(){
        return documentService.getDocuments();
    }
    @GetMapping("/document/{id}")
    public document getDocumentById(@PathVariable int id){
        return documentService.getDocumentById(id);
    }
    @DeleteMapping("/document/{id}")
    public void deleteDocumentById(@PathVariable int id){
        documentService.deleteDocumentById(id);
    }
    @PostMapping("/document")
    public document saveDocument(@RequestBody document document){
        return documentService.saveDocument(document.getTitle(), document.getContent(), document.getOwner_id());
    }
        

}
