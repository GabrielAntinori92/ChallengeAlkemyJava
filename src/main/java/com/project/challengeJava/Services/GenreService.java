package com.project.challengeJava.Services;

import com.project.challengeJava.Models.Genre;
import com.project.challengeJava.Repositories.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService {

    @Autowired
    private GenreRepository genreRepository;

    public void save(Genre genre){
        genreRepository.save(genre);
    }

    public List<Genre> findAll(){
        return genreRepository.findAll();
    }
}
