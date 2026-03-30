package com.nishath.github_commit_tracker.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "commits")
@Data
public class Commit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String commitId;
    private String message;
    private String repoName;
    private LocalDateTime timestamp;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;


}

