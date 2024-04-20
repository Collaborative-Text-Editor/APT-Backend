package com.apt.docs.model;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.relational.core.mapping.Table;
// import javax.persistence.Entity;
// import javax.persistence.Table;
// @Table(name = "DOCUMENT_PERMISSIONS")
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class document_permission {
    @Id
    private int id;
    private int documentId;
    private int userId;
    private String username;
    private String permissionType;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * ---bahebokokooooooo
     * ---moooootttttttt
     * 
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDocumentId() {
        return documentId;
    }

    public void setDocumentId(int documentId) {
        this.documentId = documentId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPermissionType() {
        return permissionType;
    }

    public void setPermissionType(String permissionType) {
        this.permissionType = permissionType;
    }

}
