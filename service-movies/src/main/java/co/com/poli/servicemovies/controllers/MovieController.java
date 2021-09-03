package co.com.poli.servicemovies.controllers;

import co.com.poli.servicemovies.entities.Movie;
import co.com.poli.servicemovies.services.MovieServiceImpl;
import co.com.poli.servicemovies.utils.Response;
import co.com.poli.servicemovies.utils.ResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieServiceImpl movieService;
    private final ResponseBuilder builder;

    @GetMapping
    public Response getMovies() {
        List<Movie> movies = movieService.findAll();
        if (movies.isEmpty()) {
            return builder.success(null, HttpStatus.NOT_FOUND);
        }

        return builder.success(movies);
    }
}
