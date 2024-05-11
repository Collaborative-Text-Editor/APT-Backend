package com.apt.docs.controller;

import com.apt.docs.model.DocumentUpdate;
import com.apt.docs.service.edit_document_service;
import com.apt.docs.DocumentEditWebSocketHandler;
import com.apt.docs.model.DocumentDelete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EditDocumentController {

    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    private DocumentEditWebSocketHandler documentEditWebSocketHandler;

    @Autowired
    private edit_document_service editDocumentService;

    @PostMapping("/insertInDocument")
    public void insertInDocument(@RequestBody DocumentUpdate update) {
        // apply updates to the document
        editDocumentService.insertTextInDocument(update.getId(), update.getIndex(), update.getNewContent());
    }

    @PostMapping("/deleteFromDocument")
    public void deleteFromDocument(@RequestBody DocumentDelete delete) {
        // Send the delete to the WebSocketHandler
        template.convertAndSend("/document-edit", delete);
    }
} 