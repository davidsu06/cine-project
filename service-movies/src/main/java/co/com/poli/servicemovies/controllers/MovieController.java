package co.com.poli.servicemovies.controllers;

import co.com.poli.servicemovies.entities.Movie;
import co.com.poli.servicemovies.services.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @PostMapping
    public ResponseEntity<Movie> createMovie(@Valid @RequestBody Movie movie, BindingResult result){
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }

        movieService.save(movie);
        return ResponseEntity.ok(movie);
    }

    @GetMapping
    public ResponseEntity<List<Movie>> getMovies() {
        List<Movie> movies = movieService.findAll();

        if (movies.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(movies);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable("id") Long id) {
        Movie movie = movieService.findById(id);

        if (movie == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(movie);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Movie> deleteMovie(@PathVariable("id") Long id) {
        Movie movie = movieService.findById(id);

        if (movie == null) {
            return ResponseEntity.notFound().build();
        }

        movieService.delete(movie);
        return ResponseEntity.ok(movie);
    }
}
