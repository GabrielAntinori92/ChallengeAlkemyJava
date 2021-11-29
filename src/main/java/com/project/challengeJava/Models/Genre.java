package com.project.challengeJava.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter

@Entity
@NoArgsConstructor
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private byte[] image;

    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "genre",cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private List<Movie> movies;
}
