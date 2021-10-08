package com.project.challengeJava.Controllers;

import com.project.challengeJava.Models.Genre;
import com.project.challengeJava.Services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GenreController {

    @Autowired
    private GenreService genreService;

    @PostMapping("/genre")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void save(@RequestBody Genre genre){
        genreService.save(genre);
    }

    @GetMapping("/genre")
    public ResponseEntity<List<Genre>> all(){
        List<Genre> genres = genreService.findAll();

        if(genres.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(genres);
        }

        return ResponseEntity.ok(genres);
    }

}
