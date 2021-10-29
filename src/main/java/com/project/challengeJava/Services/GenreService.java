package com.project.challengeJava.Services;

import com.project.challengeJava.DTO.MovieDTO;
import com.project.challengeJava.Models.Genre;
import com.project.challengeJava.Models.Movie;
import com.project.challengeJava.Repositories.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class GenreService {

    @Autowired
    private GenreRepository genreRepository;

    public void save(MultipartFile image, String name){
        Genre newGenre = new Genre();
        newGenre.setName(name);
        newGenre.setImage(encodeImage(image));

        genreRepository.save(newGenre);
    }

    public Optional<Genre> getById(Long id){
        Optional<Genre> genre = genreRepository.findById(id);

        if(genre.isEmpty()) throw new NoSuchElementException("Genre: " + id + "not found");

        return genre;
    }

    public void update(MultipartFile image,Long id, String name, List<Movie> movies){

        Genre toUpdate = new Genre();

        toUpdate.setId(id);
        toUpdate.setName(name);
        toUpdate.setImage(encodeImage(image));
        toUpdate.setMovies(movies);

        genreRepository.save(toUpdate);
    }

    public void delete(Long id){

        genreRepository.deleteById(id);
    }

    public Optional<Genre> getOne(Long id) {
        Optional<Genre> genre = genreRepository.findById(id);

        if(genre.isEmpty()) throw new NoSuchElementException("Genre: "+id+" not found");

        return genre;
    }

    public List<Genre> findAll(){
        return genreRepository.findAll();
    }

    private byte[] encodeImage(MultipartFile image) {
        byte[] encodedImage = null;
        try{
           encodedImage = Base64.getEncoder().encode(image.getBytes());
        }catch (IOException e){
            e.printStackTrace();
        }
        return encodedImage;
    }
}
