package com.project.challengeJava.Services;

import com.project.challengeJava.Models.Personaje;
import com.project.challengeJava.Models.PersonajeDTO;
import com.project.challengeJava.Repositories.PersonajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public List<PersonajeDTO> getAll(){
        return personajeRepository.getAll();
    }
}
