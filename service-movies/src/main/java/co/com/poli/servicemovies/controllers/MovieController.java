package co.com.poli.servicemovies.controllers;

import co.com.poli.servicemovies.entities.Movie;
import co.com.poli.servicemovies.services.MovieService;
import com.example.multimodule.service.MyService;
import com.example.multimodule.service.utils.Response;
import com.example.multimodule.service.utils.ResponseBuilder;
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
    private final ResponseBuilder responseBuilder;
    private final MyService myService;

    @PostMapping
    public Response createMovie(@Valid @RequestBody Movie movie, BindingResult result){
        if (result.hasErrors()) {
            return responseBuilder.failed(myService.formatMessage(result));
        }

        movieService.save(movie);
        return responseBuilder.success(movie);
    }

    @GetMapping
    public Response getMovies() {
        List<Movie> movies = movieService.findAll();

        if (movies.isEmpty()) {
            return responseBuilder.failed(null);
        }

        return responseBuilder.success(movies);
    }

    @GetMapping("/{id}")
    public Response getMovieById(@PathVariable("id") Long id) {
        Movie movie = movieService.findById(id);

        if (movie == null) {
            return responseBuilder.failed(null);
        }

        return responseBuilder.success(movie);
    }

    @DeleteMapping("/{id}")
    public Response deleteMovie(@PathVariable("id") Long id) {
        Movie movie = movieService.findById(id);

        if (movie == null) {
            return responseBuilder.failed(null);
        }

        movieService.delete(movie);
        return responseBuilder.success(movie);
    }
}
