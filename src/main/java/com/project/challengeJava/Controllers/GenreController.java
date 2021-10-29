package com.project.challengeJava.Controllers;

import com.project.challengeJava.Models.Genre;
import com.project.challengeJava.Models.Movie;
import com.project.challengeJava.Services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/v1/api/genres")
public class GenreController {
    @Autowired
    private GenreService genreService;

    @PostMapping("")
    private ResponseEntity save(@RequestPart MultipartFile image, @RequestParam String name){
        genreService.save(image,name);

        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    private ResponseEntity update(@PathVariable("id") Long id, @RequestPart("image")MultipartFile image,
                                  @RequestParam String name, @RequestParam(value = "movies", required = false) List<Movie> movies){
        try{
            genreService.update(image,id,name,movies);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        try{
            genreService.delete(id);
            return ResponseEntity.ok(id);
        }catch (EmptyResultDataAccessException e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("")
    public ResponseEntity<List<Genre>> getAll(){
        return ResponseEntity.ok(genreService.findAll());
    }
}
