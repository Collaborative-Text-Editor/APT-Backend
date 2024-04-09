package com.apt.docs.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import jakarta.validation.constraints.NotEmpty;

@Table( "DOCUMENTS")
public class document {
    @Id
    private int id;
    @NotEmpty
    private String title;
    private byte[] content;
    private LocalDateTime created_at;
    public document() {
    }
    public LocalDateTime getCreated_at() {
        return created_at;
    }
    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }
    private int owner_id;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public byte[] getContent() {
        return content;
    }
    public void setContent(byte[] content) {
        this.content = content;
    }
    public int getOwner_id() {
        return owner_id;
    }
    public void setOwner_id(int owner_id) {
        this.owner_id = owner_id;
    }
}
