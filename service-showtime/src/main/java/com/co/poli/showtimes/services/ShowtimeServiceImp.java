package com.co.poli.showtimes.services;

import com.co.poli.showtimes.clients.MovieClient;
import com.co.poli.showtimes.entities.Showtime;
import com.co.poli.showtimes.models.Movie;
import com.co.poli.showtimes.repositories.ShowtimeRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ShowtimeServiceImp implements ShowtimeService {
  private final ShowtimeRepository showtimeRepository;
  private final MovieClient movieClient;
  private final ModelMapper modelMapper;

  @Override
  public Showtime createShowtime(Showtime showtime) {
    showtime.setDate(new Date());
    return showtimeRepository.save(showtime);
  }

  @Override
  public List<Showtime> getListShowtime() {
    return showtimeRepository.findAll().stream()
            .map(showtime ->
                    showtime.toBuilder()
                    .moviesObj(buildMovies(showtime))
                    .build()
            ).collect(Collectors.toList());
  }

  @Override
  public Showtime getShowtimeById(Long id) {
    Showtime showtime = showtimeRepository.findById(id).orElse(null);

    if (Objects.nonNull(showtime)) {
      return showtime.toBuilder()
              .moviesObj(buildMovies(showtime))
              .build();
    }
    return null;
  }

  @Override
  public Showtime updateShowtime(Long id, Showtime newShowtime) {
    Showtime findShowtime = showtimeRepository.findById(id).orElse(null);

    if (findShowtime == null) { return null; }
    findShowtime.setDate(newShowtime.getDate());
    findShowtime.setMovies(newShowtime.getMovies());
    showtimeRepository.save(findShowtime);

    return findShowtime;
  }

  private List<Movie> buildMovies(Showtime showtime) {
    List<Movie> movies = new ArrayList<>();

    if (showtime.getMovies() != null){
      for (Long movieId : showtime.getMovies()) {
        Movie movie = modelMapper.map(movieClient.getMovieById(movieId).getData(), Movie.class);
        movies.add(movie);
      }

    }
    return movies;
  }
}
