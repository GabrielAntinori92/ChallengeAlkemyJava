package com.project.challengeJava.Models;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Columns;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter

@Entity
@NoArgsConstructor
public class Personaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String image;
    private String age;
    private float weight;
    private String story;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id", referencedColumnName = "id")
    private List<Movie> movies;


}
