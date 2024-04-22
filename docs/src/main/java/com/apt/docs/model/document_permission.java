package com.apt.docs.model;

import javax.swing.text.Document;

import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

import jakarta.annotation.Generated;
import jakarta.persistence.EmbeddedId;
//import org.springframework.data.relational.core.mapping.Table;
// import javax.persistence.Entity;
// import javax.persistence.Table;
// @Table(name = "DOCUMENT_PERMISSIONS")
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "document_permissions")
public class document_permission {
    @EmbeddedId
    private document_permission_id id;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name = "document_id", referencedColumnName = "id",insertable = false, updatable = false)
    private document document;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id",insertable = false, updatable = false)
    private user user;
    private String permissionType;
    
    
    
    /**
     * ---bahebokokooooooo
     * ---moooootttttttt
     * 
     */
    
    public document_permission_id getId() {
        return id;
    }

    public void setId(document_permission_id id) {
        this.id = id;
    }
    public String getPermissionType() {
        return permissionType;
    }

    public void setPermissionType(String permissionType) {
        this.permissionType = permissionType;
    }

    public document getDocument() {
        return document;
    }

    public void setDocument(document document) {
        this.document = document;
    }

    public user getUser() {
        return user;
    }

    public void setUser(user user) {
        this.user = user;
    }

}
