package com.project.challengeJava.Repositories;

import com.project.challengeJava.Models.Personaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonajeRepository extends JpaRepository<Personaje,Long> {

    @Query(value = "SELECT id,name,image,age,weight,story FROM Personaje WHERE name LIKE ?1",nativeQuery = true)
    List<Personaje> findByName(String name);

    @Query(value = "SELECT id,name,image,age,weight,story FROM Personaje WHERE age LIKE ?1")
    List<Personaje> findByAge(String age);

    @Query(value= "SELECT id,name,image,age,weight,story From Personaje p " +
                  "JOIN Movie_Personaje mp ON mp.personaje_id = p.id " +
                  "WHERE mp.movie_id = ?1",nativeQuery = true)
    List<Personaje> findByMovieId(Long id);
}
