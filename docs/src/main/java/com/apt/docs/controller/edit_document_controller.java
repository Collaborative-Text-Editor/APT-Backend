package com.apt.docs.controller;

import com.apt.docs.service.document_service;
import com.apt.docs.service.edit_document_service;
import com.apt.docs.model.DocumentUpdate; // import the new class
import com.apt.docs.model.document;
import com.apt.docs.model.DocumentDelete; // import the new class

import org.springframework.web.bind.annotation.RestController;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class edit_document_controller {
    private final edit_document_service editDocumentService;
    private final document_service documentService;

    public edit_document_controller(edit_document_service editDocumentService, document_service documentService) {
        this.editDocumentService = editDocumentService;
        this.documentService = documentService;
    }
    
    @MessageMapping("/insertInDocument")
    @SendTo("/topic/document")
    public document insertInDocument(@Payload DocumentUpdate update) {
        
        editDocumentService.insertTextInDocument(update.getId(), update.getIndex(), update.getNewContent());
        System.out.println("zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz: " + update.getNewContent());
        return documentService.getDocumentById(update.getId());
    }

    @MessageMapping("/deleteFromDocument")
    @SendTo("/topic/document")
    public document deleteFromDocument(@RequestBody DocumentDelete delete) {
        editDocumentService.deleteTextFromDocument(delete.getId(), delete.getIndex(), delete.getLength());
        return documentService.getDocumentById(delete.getId());
    }
}