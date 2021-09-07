package com.co.poli.showtimes.services;

import com.co.poli.showtimes.entities.Showtime;
import com.co.poli.showtimes.repositories.ShowtimeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class ShowtimeServiceImp implements ShowtimeService {
  private final ShowtimeRepository showtimeRepository;

  @Override
  public Showtime createShowtime(Showtime showtime) {
    showtime.setDate(new Date());
    return showtimeRepository.save(showtime);
  }

  @Override
  public List<Showtime> getListShowtime() {
    return showtimeRepository.findAll();
  }

  @Override
  public Showtime getShowtimeById(Long id) {
    return showtimeRepository.findById(id).orElse(null);
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
}
