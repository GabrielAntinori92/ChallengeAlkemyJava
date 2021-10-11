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
    private Integer id;

    private String image;
    private String name;

    @OneToMany(mappedBy = "genre", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Movie> movies;
}
