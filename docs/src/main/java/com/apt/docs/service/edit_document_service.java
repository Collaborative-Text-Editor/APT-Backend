package com.apt.docs.service;

import org.springframework.stereotype.Service;

import com.apt.docs.model.document;
import com.apt.docs.repository.document_repository;

@Service
public class edit_document_service {
    private final document_repository documentRepository;

    public edit_document_service(document_repository documentRepository) {
        this.documentRepository = documentRepository;
    }

    public void insertTextInDocument(int id, int index, byte[] newContent) {
        documentRepository.findById(id).ifPresent(document -> {
            byte[] oldContent = document.getContent();
            byte[] updatedContent = new byte[oldContent.length + newContent.length];

            System.arraycopy(oldContent, 0, updatedContent, 0, index);
            System.arraycopy(newContent, 0, updatedContent, index, newContent.length);
            System.arraycopy(oldContent, index, updatedContent, index + newContent.length, oldContent.length - index);

            document.setContent(updatedContent);
            documentRepository.save(document);

        });
        

    }

    public void deleteTextFromDocument(int id, int index, int length) {
        documentRepository.findById(id).ifPresent(document -> {
           
        });
    }

}
