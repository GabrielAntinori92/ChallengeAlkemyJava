package com.project.challengeJava.Models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data

@Entity
@NoArgsConstructor
public class Genre {
    @Id
    private Integer id;

    private String image;
    private String name;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id", referencedColumnName = "id")
    private List<Movie> movies;
}
