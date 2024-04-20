package com.apt.docs.model;

import java.time.LocalDateTime;
// import javax.persistence.Entity;
// import javax.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

// @Table(name = "DOCUMENTS")
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class document {
    @Id
    private int id;
    @NotEmpty
    private String title;
    private byte[] content;
    private LocalDateTime createdAt;
    private String ownerUsername;
    private int ownerId;

    public document() {

    }

    public String getOwnerUsername() {
        return ownerUsername;
    }

    public void setOwnerUsername(String ownerUsername) {
        this.ownerUsername = ownerUsername;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerid) {
        this.ownerId = ownerid;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime created_at) {
        this.createdAt = createdAt;
    }

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

}
