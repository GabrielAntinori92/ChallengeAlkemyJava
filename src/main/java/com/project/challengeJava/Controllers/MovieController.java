package com.project.challengeJava.Controllers;

import com.project.challengeJava.Models.Movie;
import com.project.challengeJava.Services.MovieService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/movies")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping("")
    public ResponseEntity<List<Movie>> getAllOrQuery(@RequestParam(required = false) Map<String,Object> params){
        if(params.isEmpty()){
            return ResponseEntity.ok(movieService.getAll());
        }
        return ResponseEntity.ok(movieService.getWithQuery(params));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getById(@PathVariable Integer id){
        return ResponseEntity.ok(movieService.getById(id).get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer id){
        movieService.delete(id);

        return new ResponseEntity<>(id,HttpStatus.NO_CONTENT);
    }

    @PostMapping("")
    public ResponseEntity save(@RequestBody Movie movie){
        movieService.save(movie);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable Integer id, @RequestBody Movie movie){
        try{
            movieService.update(id,movie);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity(HttpStatus.OK);
    }

}
