package com.apt.docs.controller;

import com.apt.docs.model.DocumentUpdate;
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

    @PostMapping("/insertInDocument")
    public void insertInDocument(@RequestBody DocumentUpdate update) {
        // Send the update to the WebSocketHandler
        template.convertAndSend("/document-edit", update);
    }

    @PostMapping("/deleteFromDocument")
    public void deleteFromDocument(@RequestBody DocumentDelete delete) {
        // Send the delete to the WebSocketHandler
        template.convertAndSend("/document-edit", delete);
    }
}