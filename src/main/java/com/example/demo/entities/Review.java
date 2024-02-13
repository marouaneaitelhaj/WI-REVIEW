package com.example.demo.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Temporal(TemporalType.DATE)
    private LocalDate date;
    private String Title;
    private String message;
    //@Enumerated(EnumType.STRING)
    //private Reaction reaction;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reviewer")
    private DbUser user;
}
