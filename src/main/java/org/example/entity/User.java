package org.example.entity;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "users", schema = "public")
public class User {

    public User() {

    }

    @Id
    @Column(name = "id", nullable = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "username", nullable = true)
    private String username;
    @Column(name = "password", nullable = true)
    private String password;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String login) {
        this.username = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String name) {
        this.password = name;
    }

}
