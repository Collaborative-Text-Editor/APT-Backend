package com.apt.docs.service;

import java.util.Arrays;

import org.springframework.stereotype.Service;

import com.apt.docs.model.document;
import com.apt.docs.repository.document_repository;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class edit_document_service {
    private final document_repository documentRepository;
    private static final ReentrantLock lock = new ReentrantLock();

    public edit_document_service(document_repository documentRepository) {
        this.documentRepository = documentRepository;
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
            });
        } finally {
            lock.unlock();
        }
    }
}