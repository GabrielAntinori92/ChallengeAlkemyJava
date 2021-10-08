package com.project.challengeJava.Models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data

@Entity
@NoArgsConstructor
public class Movie {
    @Id
    private Integer id;

    private String title;
    private Date premiere;
    private Integer rate;

    @OneToOne(fetch = FetchType.LAZY)

    private Genre genre;

    @ManyToMany
    @JoinTable(
            name = "movie_characters",
            joinColumns = @JoinColumn(name = "movie_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "character_id", referencedColumnName = "id")
    )
    private List<Character> characters;
}
