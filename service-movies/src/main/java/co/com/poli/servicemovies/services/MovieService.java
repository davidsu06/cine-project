package co.com.poli.servicemovies.services;

import co.com.poli.servicemovies.entities.Movie;

import java.util.List;

public interface MovieService {

    void save(Movie movie);
    void delete(Movie movie);
    List<Movie> findAll();
    Movie findById(Long id);

}
