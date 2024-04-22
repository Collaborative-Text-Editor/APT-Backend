package com.apt.docs.model;

//import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.jpa.repository.JpaRepository;
// import javax.persistence.Entity;
// import javax.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.persistence.Column;
// @Table(name = "USERS")
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class user {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotNull
    @Column(unique = true)
    private String username;
    @NotNull
    private String password;

    public user() {
    }

    public user(String username, String password) {
        //this.id = id;
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    // public void setId(int id) {
    //     this.id = id;
    // }

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