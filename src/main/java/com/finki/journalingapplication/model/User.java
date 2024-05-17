package com.finki.journalingapplication.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "userjournal")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String name;
    private String surname;
    private String password;

    public User(String name, String surname, String username, String password) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
    }

    public User() {
    }
}
