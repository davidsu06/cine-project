package com.co.poli.showtimes.services;

import com.co.poli.showtimes.entities.Showtime;

import java.util.List;

public interface ShowtimeService {
  Showtime createShowtime(Showtime showtime);
  List<Showtime> getListShowtime();
  Showtime getShowtimeById(Long id);
  Showtime updateShowtime(Long id, Showtime newShowtime);
}
