package com.project.challengeJava.Repositories;

import com.project.challengeJava.Models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie,Integer> {

    List<Movie> findByGenre(Integer id);
    List<Movie> findByTitle(String title);

}
