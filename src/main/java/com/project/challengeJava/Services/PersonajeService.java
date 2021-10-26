package com.project.challengeJava.Services;

import com.project.challengeJava.DTO.PersonajeDTO;
import com.project.challengeJava.Models.Personaje;
import com.project.challengeJava.Repositories.PersonajeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonajeService {

    @Autowired
    private PersonajeRepository personajeRepository;

    @Autowired
    private ModelMapper modelMapper;

    public void save(Personaje character){
        personajeRepository.save(character);
    }

    public void delete(Long id){
        personajeRepository.deleteById(id);
    }

    public void update(Long id,Personaje updated) throws Exception{
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

    public Optional<Personaje> getById(Long id){
        Optional<Personaje> character = personajeRepository.findById(id);

        return character;
    }

    public List<PersonajeDTO> getWithQuery(Map<String,Object> parameters){
        List<Personaje> characters = null;

        if(parameters.containsKey("name")){
           characters = personajeRepository.findByName((String) parameters.get("name"));
        }else if(parameters.containsKey("age")){
            characters = personajeRepository.findByAge((String) parameters.get("age"));
        }else if(parameters.containsKey("movie")){
            characters = personajeRepository.findByMovieId(Long.parseLong((String)parameters.get("movie")));
        }

        return toListDto(characters);
    }

    public List<PersonajeDTO> getAll(){
        return toListDto(personajeRepository.findAll());
    }

    private List<PersonajeDTO> toListDto(List<Personaje> personajes){
        List<PersonajeDTO> dtos = personajes.stream()
                .map(personaje -> modelMapper.map(personaje,PersonajeDTO.class))
                .collect(Collectors.toList());

        return dtos;
    }
}
