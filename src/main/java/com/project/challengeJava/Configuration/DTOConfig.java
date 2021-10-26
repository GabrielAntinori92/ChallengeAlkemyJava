package com.project.challengeJava.Configuration;

import com.project.challengeJava.DTO.MovieDTO;
import com.project.challengeJava.DTO.PersonajeDTO;
import com.project.challengeJava.Models.Movie;
import com.project.challengeJava.Models.Personaje;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ModelMapperConfig {
    @Bean("Model-Mapper")
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.addMappings(new PropertyMap<PersonajeDTO, Personaje>() {
            @Override //Destination = Personaje, Source = PersonajeDTO
            protected void configure() {
                map().setName(source.getPersonajeName());
                map().setImage(source.getImage());
                map().setId(source.getId());
            }
        });

        modelMapper.addMappings(new PropertyMap<Personaje, PersonajeDTO>() {
            @Override//Destination = PersonajeDTO, Source = Personaje
            protected void configure() {
                map().setPersonajeName(source.getName());
                map().setImage(source.getImage());
                map().setId(source.getId());
            }
        });

        modelMapper.addMappings(new PropertyMap<MovieDTO, Movie>() {
            @Override //Destination = Movie, Source = MovieDTO
            protected void configure(){
                map().setTitle(source.getTitle());
                map().setImage(source.getImage());
                map().setId(source.getId());
            }
        });

        return modelMapper;

    }
}
