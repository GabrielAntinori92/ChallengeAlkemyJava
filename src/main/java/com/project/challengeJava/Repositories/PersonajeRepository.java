package com.project.challengeJava.Repositories;

import com.project.challengeJava.Models.Personaje;
import com.project.challengeJava.Models.PersonajeDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;


public interface PersonajeRepository extends JpaRepository<Personaje,Integer> {

    @Query(value = "SELECT id,name,image FROM personaje", nativeQuery = true)
    List<PersonajeDTO> getAll();
}
