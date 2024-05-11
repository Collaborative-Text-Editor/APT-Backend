package com.apt.docs.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.apt.docs.DocumentEditWebSocketHandler;
import com.apt.docs.model.DocumentUpdate;
import com.apt.docs.model.document;
import com.apt.docs.repository.document_repository;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class edit_document_service {
    private final document_repository documentRepository;
    private static final ReentrantLock lock = new ReentrantLock();

        @Autowired
    private SimpMessagingTemplate template;

    // @Autowired
    // private DocumentEditWebSocketHandler documentEditWebSocketHandler;

    public edit_document_service(document_repository documentRepository) {
        this.documentRepository = documentRepository;
    }

    public void broadcastUpdate(byte[] update) {
        template.convertAndSend("/topic/document-edit", update);
    }

    public void insertTextInDocument(int id, int index, byte[] newContent) {
        lock.lock();
        try {
            documentRepository.findById(id).ifPresent(document -> {
                byte[] oldContent = document.getContent();
                byte[] updatedContent = new byte[oldContent.length + newContent.length];

                System.arraycopy(oldContent, 0, updatedContent, 0, index);
                System.arraycopy(newContent, 0, updatedContent, index, newContent.length);
                System.arraycopy(oldContent, index, updatedContent, index + newContent.length, oldContent.length - index);

                document.setContent(updatedContent);
                documentRepository.save(document);
                broadcastUpdate(updatedContent);
            });
        } finally {
            lock.unlock();
        }
    }

    public void deleteTextFromDocument(int id, int index, int length) {
        lock.lock();
        try {
            documentRepository.findById(id).ifPresent(document -> {
                byte[] oldContent = document.getContent();
                byte[] updatedContent = new byte[oldContent.length - length];

                System.arraycopy(oldContent, 0, updatedContent, 0, index);
                System.arraycopy(oldContent, index + length, updatedContent, index, oldContent.length - index - length);

                document.setContent(updatedContent);
                documentRepository.save(document);
                broadcastUpdate(updatedContent);
            });
        } finally {
            lock.unlock();
        }
    }
    public void applyEdit(DocumentUpdate update) {
    document document = documentRepository.findById(update.getId())
        .orElseThrow(() -> new RuntimeException("Document not found"));

    // Apply the update to the document
    document.setContent(update.getNewContent());

    // Save the updated document back to the database
    documentRepository.save(document);
}

}