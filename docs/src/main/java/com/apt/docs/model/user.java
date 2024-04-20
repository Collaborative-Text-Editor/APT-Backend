package com.apt.docs.model;

//import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.jpa.repository.JpaRepository;
// import javax.persistence.Entity;
// import javax.persistence.Table;
import jakarta.validation.constraints.NotNull;

// @Table(name = "USERS")
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class user {
    @Id
    private int id;
    @NotNull
    private String username;
    @NotNull
    private String password;

    public user() {
    }

    public user(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}