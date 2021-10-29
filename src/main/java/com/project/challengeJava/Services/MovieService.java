package com.project.challengeJava.Services;

import com.project.challengeJava.DTO.MovieDTO;
import com.project.challengeJava.Models.Genre;
import com.project.challengeJava.Models.Movie;
import com.project.challengeJava.Repositories.MovieRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<MovieDTO> getAll(){
        final List<Movie> movies = movieRepository.findAll();

        return toListDTO(movies);
    }

    public List<MovieDTO> getWithQuery(Map<String,Object> parameters){
        List<Movie> movies = null;

        if(parameters.containsKey("genre")){
            movies = movieRepository.findByGenreId(Long.parseLong((String)parameters.get("movie")));
        }else if(parameters.containsKey("tile")){
            movies = movieRepository.findByTitle((String) parameters.get("title"));
        }else if(parameters.containsKey("order")){
            movies = order((String) parameters.get("order"));
        }

        return toListDTO(movies);
    }

    public Optional<Movie> getById(Long id){
        final Optional<Movie> movie = movieRepository.findById(id);

        return movie;
    }

    public void delete(Long id){
        movieRepository.deleteById(id);
    }

    public void save(MultipartFile image, String title, Date premiere, float rate, String genre){
        Movie movie = new Movie();
        Genre gen = new Genre();
        gen.setName(genre);

        movie.setTitle(title);
        movie.setPremiere(premiere);
        movie.setRate(rate);
        movie.setImage(encodeImage(image));
        movie.setGenre(gen);

        movieRepository.save(movie);
    }

    public void update(Long id, Movie updated){
        Optional<Movie> movie = movieRepository.findById(id);

        movie.get().setTitle(updated.getTitle());
        movie.get().setPremiere(updated.getPremiere());
        movie.get().setRate(updated.getRate());
        movie.get().setCharacters(updated.getCharacters());
        movie.get().setGenre(updated.getGenre());

        movieRepository.save(movie.get());
    }

    private List<Movie> order(String direction){
        List<Movie> movies = null;

        if(direction.equals("ASC")){
            movies = movieRepository.findAll(Sort.by(Sort.Direction.ASC,"name"));
        }else if(direction.equals("DESC")){
            movies = movieRepository.findAll(Sort.by(Sort.Direction.DESC,"name"));
        }

        return movies;
    }

    private List<MovieDTO> toListDTO(List<Movie> movies){
        return movieRepository.findAll().stream()
                .map(movie -> modelMapper.map(movie,MovieDTO.class))
                .collect(Collectors.toList());
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

    public void save(MultipartFile image,Movie movie){
        movie.setImage(encodeImage(image));

        movieRepository.save(movie);
    }
}
