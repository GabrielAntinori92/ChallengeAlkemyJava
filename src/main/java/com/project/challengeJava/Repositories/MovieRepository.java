package com.project.challengeJava.Repositories;

import com.project.challengeJava.Models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Long> {

    List<Movie> findByGenreId(Long id);
    List<Movie> findByTitle(String title);



}
