package com.project.challengeJava.Controllers;

import com.project.challengeJava.DTO.MovieDTO;
import com.project.challengeJava.Models.Genre;
import com.project.challengeJava.Models.Movie;
import com.project.challengeJava.Services.MovieService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/v1/api/movies")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping("")
    public ResponseEntity<List<MovieDTO>> getAllOrQuery(@RequestParam(required = false) Map<String,Object> params){
        if(params.isEmpty()){
            return ResponseEntity.ok(movieService.getAll());
        }
        return ResponseEntity.ok(movieService.getWithQuery(params));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getById(@PathVariable Long id){
        return ResponseEntity.ok(movieService.getById(id).get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        try{
            movieService.delete(id);
            return ResponseEntity.ok(id);
        }catch (EmptyResultDataAccessException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("")
    public ResponseEntity save(@RequestPart MultipartFile image, @RequestParam String title,
                               @RequestParam("premiere") @DateTimeFormat(pattern = "MM-dd-yyyy") Date premiere,
                               @RequestParam float rate, @RequestParam(required = false) String genreName){
        movieService.save(image,title,premiere,rate,genreName);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody Movie movie){
        try{
            movieService.update(id,movie);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity(HttpStatus.OK);
    }

}
