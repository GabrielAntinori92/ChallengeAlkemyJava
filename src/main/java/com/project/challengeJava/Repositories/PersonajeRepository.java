package com.project.challengeJava.Repositories;

import com.project.challengeJava.Models.Personaje;
import com.project.challengeJava.DTO.PersonajeDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;


public interface PersonajeRepository extends JpaRepository<Personaje,Integer> {

    @Query(value = "SELECT id,name,image FROM personaje", nativeQuery = true)
    List<PersonajeDTO> getAll();

    @Query(value = "SELECT id,name,image FROM personaje where name LIKE ?1", nativeQuery = true)
    List<PersonajeDTO> findByName(String name);

    @Query(value = "SELECT id,name,image FROM personaje where age LIKE ?1", nativeQuery = true)
    List<PersonajeDTO> findByAge(String age);

    @Query(value = "SELECT id,name,image FROM personaje p " +
            "JOIN movie_personaje mp on p.id = mp.character_id " +
            "WHERE mp.movie_id = ?1", nativeQuery = true)
    List<PersonajeDTO> findByMovieId(Integer id);
}
