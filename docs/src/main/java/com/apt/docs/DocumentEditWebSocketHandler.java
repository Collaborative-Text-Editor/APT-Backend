package com.apt.docs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import com.apt.docs.service.edit_document_service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.apt.docs.model.DocumentUpdate;

public class DocumentEditWebSocketHandler extends TextWebSocketHandler {

    @Autowired
    private edit_document_service editDocumentService;

    @Autowired
    private SimpMessagingTemplate template;

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // Parse the message to a DocumentUpdate
        DocumentUpdate update = parseMessage(message.getPayload());
    
        // Determine whether it's an insert or delete operation
        if (update.getOperation().equals("insert")) {
            // Apply the insert operation using the service
            editDocumentService.insertTextInDocument(update.getId(), update.getIndex(), update.getNewContent());
        } else if (update.getOperation().equals("delete")) {
            // Apply the delete operation using the service
            editDocumentService.deleteTextFromDocument(update.getId(), update.getIndex(), update.getLength());
        }
    
        // Broadcast the update to all other clients
        template.convertAndSend("/topic/document-edit", update);
    }

    //hatboooz mot
    private DocumentUpdate parseMessage(String message) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(message, DocumentUpdate.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to parse message", e);
        }
    }
}