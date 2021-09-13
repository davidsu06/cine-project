package co.com.poli.servicemovies.controllers;

import co.com.poli.servicemovies.dto.MovieDto;
import co.com.poli.servicemovies.entities.Movie;
import co.com.poli.servicemovies.services.MovieService;
import com.example.multimodule.service.CommonService;
import com.example.multimodule.service.utils.Response;
import com.example.multimodule.service.utils.ResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;
    private final ResponseBuilder responseBuilder;
    private final CommonService commonService;
    private final ModelMapper modelMapper;

    @PostMapping
    public Response createMovie(@Valid @RequestBody MovieDto movieDto, BindingResult result){
        if (result.hasErrors()) {
            return responseBuilder.failed(commonService.formatMessage(result));
        }

        Movie movie = modelMapper.map(movieDto,Movie.class);
        movieService.save(movie);
        MovieDto movieDtoRespuesta = modelMapper.map(movie, MovieDto.class);

        return responseBuilder.success(movieDtoRespuesta);
    }

    @GetMapping
    public Response getMovies() {
        List<Movie> movies = movieService.findAll();

        if (movies.isEmpty()) {
            return responseBuilder.failed(null);
        }

        List <MovieDto> moviesDto = movies.stream()
                .map(movie -> modelMapper.map(movie,MovieDto.class))
                .collect(Collectors.toList());

        return responseBuilder.success(moviesDto);
    }

    @GetMapping("/{id}")
    public Response getMovieById(@PathVariable("id") Long id) {
        Movie movie = movieService.findById(id);

        if (movie == null) {
            return responseBuilder.failed(null);
        }

        MovieDto movieDto = modelMapper.map(movie,MovieDto.class);

        return responseBuilder.success(movieDto);
    }

    @DeleteMapping("/{id}")
    public Response deleteMovie(@PathVariable("id") Long id) {
        Movie movie = movieService.findById(id);

        if (movie == null) {
            return responseBuilder.failed(null);
        }

        movieService.delete(movie);
        MovieDto movieDto = modelMapper.map(movie,MovieDto.class);

        return responseBuilder.success(movieDto);
    }
}
