package com.project.challengeJava.Controllers;

import com.project.challengeJava.DTO.MovieDTO;
import com.project.challengeJava.Models.Genre;
import com.project.challengeJava.Models.Movie;
import com.project.challengeJava.Services.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.apache.coyote.Response;
import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.util.*;

@RestController
@RequestMapping("/v1/api/movies")
public class MovieController {
    @Autowired
    private MovieService movieService;


    @Operation(summary = "Filters movies")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movie found",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = MovieDTO.class)))
            ),
            @ApiResponse(responseCode = "204", description = "No content")})
    @GetMapping(value = "",produces = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity<List<MovieDTO>> getAllOrQuery(@Parameter @RequestParam(required = false) Map<String,Object> params){
        if(params.isEmpty()){
            return ResponseEntity.ok(movieService.getAll());
        }
        return ResponseEntity.ok(movieService.getWithQuery(params));
    }


    @Operation(summary = "Finds a movie")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movie found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Movie.class))}),
            @ApiResponse(responseCode = "204", description = "Movie not found")
    })
    @GetMapping(value = "/{id}",produces = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity<Movie> getById(@Parameter(description = "Id of the movie to be found") @PathVariable Long id){
        try{
            return ResponseEntity.ok(movieService.getById(id).get());
        }catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Deletes a movie")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movie deleted"),
            @ApiResponse(responseCode = "404", description = "Movie not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@Parameter(description = "Id of the book to be deleted") @PathVariable Long id){
        try{
            movieService.delete(id);
            return ResponseEntity.ok(id);
        }catch (EmptyResultDataAccessException e){
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Creates a movie")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Movie created"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping(value = "", consumes = {
            MediaType.MULTIPART_FORM_DATA_VALUE
    })
    public ResponseEntity save(@RequestPart MultipartFile image, @RequestParam String title,
                               @RequestParam("premiere") @DateTimeFormat(pattern = "MM-dd-yyyy") Date premiere,
                               @RequestParam float rate, @RequestParam(required = false) String genreName){

        movieService.save(image,title,premiere,rate,genreName);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @Operation(summary = "Updates a movie")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movie updated"),
            @ApiResponse(responseCode = "404", description = "Movie not found")
    })
    @PutMapping(value = "/{id}", consumes = {
            MediaType.MULTIPART_FORM_DATA_VALUE
    })
    public ResponseEntity update(@PathVariable Long id, @RequestBody Movie movie){
        try{
            movieService.update(id,movie);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(HttpStatus.OK);
    }

}
