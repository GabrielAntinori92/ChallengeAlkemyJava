package com.project.challengeJava.Controllers;

import com.project.challengeJava.DTO.MovieDTO;
import com.project.challengeJava.DTO.PersonajeDTO;
import com.project.challengeJava.Models.Movie;
import com.project.challengeJava.Models.Personaje;
import com.project.challengeJava.Services.PersonajeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("v1/api/characters")
public class PersonajeController {

    @Autowired
    private PersonajeService personajeService;

    @PostMapping(value = "",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @Operation(summary = "Creates a character")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Character created"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    public ResponseEntity save(@RequestPart MultipartFile image, @RequestParam String name, @RequestParam String age,
                               @RequestParam String story, @RequestParam float weight){
        personajeService.save(image,name,age,story,weight);

        return new ResponseEntity(HttpStatus.CREATED);
    }


    @GetMapping("")
    @Operation(summary = "Filters Characters")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Character found",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = MovieDTO.class)))
            ),
            @ApiResponse(responseCode = "204", description = "No content")})
    public ResponseEntity<List<PersonajeDTO>> getByQueryParameters(@RequestParam(required = false) Map<String,Object> params){
        if(params.isEmpty()){
            return getAll();
        }

        return ResponseEntity.ok(personajeService.getWithQuery(params));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Finds a character")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Character found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Movie.class))}),
            @ApiResponse(responseCode = "204", description = "No content")
    })
    public ResponseEntity<Personaje> getDetails(@PathVariable Long id){
        final Optional<Personaje> character = personajeService.getById(id);

        if(character.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(character.get());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletes a character")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Character deleted"),
            @ApiResponse(responseCode = "404", description = "Character not found")
    })
    public ResponseEntity<Long> delete(@PathVariable Long id){
        try{
            personajeService.delete(id);
            return ResponseEntity.ok(id);
        }catch (EmptyResultDataAccessException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> update(@PathVariable Long id, @RequestBody Personaje character){
        try{
            personajeService.update(id,character);
        }catch (Exception e) {
            return new ResponseEntity<>(id,HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(id,HttpStatus.OK);
    }

    private ResponseEntity<List<PersonajeDTO>> getAll(){
        List<PersonajeDTO> characters = personajeService.getAll();

        if(characters.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(characters);
        }

        return ResponseEntity.ok(characters);
    }
}
