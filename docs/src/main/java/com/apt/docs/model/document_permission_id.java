package com.apt.docs.model;

import java.io.Serializable;
import jakarta.persistence.Embeddable;

@Embeddable
public class document_permission_id implements Serializable {
    private int document_Id;
    private int user_Id;
    public int getDocumentId() {
        return document_Id;
    }
    public void setDocumentId(int documentId) {
        this.document_Id = documentId;
    }
    public int getUser_Id() {
        return user_Id;
    }
    public void setUser_Id(int userId) {
        this.user_Id = userId;
    }

}