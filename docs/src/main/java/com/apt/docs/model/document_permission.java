package com.apt.docs.model;

import org.springframework.data.relational.core.mapping.Table;

@Table("DOCUMENT_PERMISSIONS")
public class document_permission {
    private int id;
    private int document_id;
    private int user_id;
    private String permission_type;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getDocument_id() {
        return document_id;
    }
    public void setDocument_id(int document_id) {
        this.document_id = document_id;
    }
    public int getUser_id() {
        return user_id;
    }
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    public String getPermission_type() {
        return permission_type;
    }
    public void setPermission_type(String permission_type) {
        this.permission_type = permission_type;
    }
    
    
}
