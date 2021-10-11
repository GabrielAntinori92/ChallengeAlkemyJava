package com.project.challengeJava.Controllers;

import com.project.challengeJava.Models.Personaje;
import com.project.challengeJava.DTO.PersonajeDTO;
import com.project.challengeJava.Services.PersonajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/characters")
public class PersonajeController {

    @Autowired
    private PersonajeService personajeService;

    @PostMapping("")
    public ResponseEntity save(@RequestBody Personaje character){
        personajeService.save(character);

        return new ResponseEntity(HttpStatus.CREATED);
    }


    @GetMapping("")
    public ResponseEntity<List<PersonajeDTO>> getByQueryParameters(@RequestParam(required = false) Map<String,Object> params){
        if(params.isEmpty()){
            return getAll();
        }

        return ResponseEntity.ok(personajeService.getWithQuery(params));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Personaje> getDetails(@PathVariable Integer id){
        final Optional<Personaje> character = personajeService.getById(id);

        if(character.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(character.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> delete(@PathVariable Integer id){
        personajeService.delete(id);

        return new ResponseEntity<>(id,HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Integer> update(@PathVariable Integer id, @RequestBody Personaje character){
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
