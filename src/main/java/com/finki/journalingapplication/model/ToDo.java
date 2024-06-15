package com.finki.journalingapplication.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data

public class ToDo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private boolean completed;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public ToDo() {
    }

    public ToDo(String title) {
        this.title = title;
        this.completed = false;
    }
}
