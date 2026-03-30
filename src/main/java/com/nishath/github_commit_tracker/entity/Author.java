package com.nishath.github_commit_tracker.entity;
import jakarta.persistence.*;
import lombok.Data;
import java.util.*;
@Entity
@Data
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "author")
    private List<Commit> commits;
}
