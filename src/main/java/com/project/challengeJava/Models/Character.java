package com.project.challengeJava.Models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data

@Entity
@NoArgsConstructor
public class Character {
    @Id
    private Integer id;

    private String name;
    private String image;
    private Integer age;
    private float weight;
    private String story;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id", referencedColumnName = "id")
    private List<Movie> movies;
}
