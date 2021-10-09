package com.project.challengeJava.Services;

import com.project.challengeJava.Models.Personaje;
import com.project.challengeJava.Models.PersonajeDTO;
import com.project.challengeJava.Repositories.PersonajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PersonajeService {

    @Autowired
    private PersonajeRepository personajeRepository;


    public void save(Personaje character){
        personajeRepository.save(character);
    }

    public void delete(Integer id){
        personajeRepository.deleteById(id);
    }

    public void update(Integer id,Personaje updated) throws Exception{
        Optional<Personaje> character = personajeRepository.findById(id);

        if(character.isEmpty()){
            throw new Exception("Character not found");
        }
        character.get().setName(updated.getName());
        character.get().setImage(updated.getImage());
        character.get().setStory(updated.getStory());
        character.get().setAge(updated.getAge());
        character.get().setWeight(updated.getWeight());

        personajeRepository.save(character.get());
    }

    public Optional<Personaje> getById(Integer id){
        Optional<Personaje> character = personajeRepository.findById(id);

        return character;
    }

    public List<PersonajeDTO> getWithQuery(Map<String,Object> parameters){
        List<PersonajeDTO> characters = null;

        if(parameters.containsKey("name")){
           characters = personajeRepository.findByName((String) parameters.get("name"));
        }else if(parameters.containsKey("age")){
            characters = personajeRepository.findByAge((String) parameters.get("age"));
        }else if(parameters.containsKey("movie")){
            characters = personajeRepository.findByMovieId(Integer.parseInt((String)parameters.get("movie")));
        }

        return characters;
    }

    public List<PersonajeDTO> getAll(){
        return personajeRepository.getAll();
    }
}
