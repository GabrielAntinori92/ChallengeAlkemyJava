package com.project.challengeJava.Repositories;

import com.project.challengeJava.Models.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre,Long> {

    void deleteById(Long id);
    Genre findByName(String name);
}
