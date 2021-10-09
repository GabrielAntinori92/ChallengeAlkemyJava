package com.project.challengeJava.Controllers;

import com.project.challengeJava.Models.Personaje;
import com.project.challengeJava.Models.PersonajeDTO;
import com.project.challengeJava.Services.PersonajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/characters")
public class PersonajeController {

    @Autowired
    private PersonajeService personajeService;

    @PostMapping("")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void save(@RequestBody Personaje character){
        personajeService.save(character);
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
        return ResponseEntity.ok(personajeService.getById(id).get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> delete(@PathVariable Integer id){
        Optional<Personaje> character = personajeService.getById(id);

        if(character.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        personajeService.delete(id);
        return new ResponseEntity<>(id,HttpStatus.OK);
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
