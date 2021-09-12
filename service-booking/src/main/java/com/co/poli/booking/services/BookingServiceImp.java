package com.co.poli.booking.services;

import com.co.poli.booking.clients.MovieClient;
import com.co.poli.booking.clients.ShowtimeClient;
import com.co.poli.booking.clients.UserClient;
import com.co.poli.booking.entities.Booking;
import com.co.poli.booking.models.Movie;
import com.co.poli.booking.models.Showtime;
import com.co.poli.booking.models.User;
import com.co.poli.booking.repositories.BookingRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookingServiceImp implements BookingService {
  private final BookingRepository bookingRepository;
  private final MovieClient movieClient;
  private final UserClient userClient;
  private final ShowtimeClient showtimeClient;
  private final ModelMapper modelMapper;

  @Override
  public Booking createBooking(Booking booking) {
    return bookingRepository.save(booking);
  }

  @Override
  public List<Booking> getAllBookings() {
    return bookingRepository.findAll().stream()
            .map(booking ->
                    booking.toBuilder()
                            .moviesObj(buildMovies(booking))
                            .user(modelMapper.map(userClient.getUserById(booking.getUserId()).getData(), User.class))
                            .showtime(modelMapper.map(showtimeClient.findShowtimeById(booking.getShowtimeId()).getData(), Showtime.class))
                            .build()
            ).collect(Collectors.toList());
  }

  @Override
  public Booking findBookingById(Long id) {
    Booking booking = bookingRepository.findById(id).orElse(null);

    if (Objects.nonNull(booking)) {
      return booking.toBuilder()
              .moviesObj(buildMovies(booking))
              .user(modelMapper.map(userClient.getUserById(booking.getUserId()).getData(), User.class))
              .showtime(modelMapper.map(showtimeClient.findShowtimeById(booking.getShowtimeId()).getData(), Showtime.class))
              .build();
    }
    return null;
  }

  @Override
  public Booking deleteBooking(Long id) {
    Booking booking = bookingRepository.findById(id).orElse(null);
    bookingRepository.delete(booking);

    return booking;
  }

  @Override
  public Booking findByUserId(Long userId) {
    Booking booking = bookingRepository.findByUserId(userId);

    if (Objects.nonNull(booking)) {
      return booking.toBuilder()
              .moviesObj(buildMovies(booking))
              .user(modelMapper.map(userClient.getUserById(booking.getUserId()).getData(), User.class))
              .showtime(modelMapper.map(showtimeClient.findShowtimeById(booking.getShowtimeId()).getData(), Showtime.class))
              .build();
    }
    return null;
  }

  private List<Movie> buildMovies(Booking booking) {
    List<Movie> movies = new ArrayList<>();

    if (booking.getMovies() != null){
      for (Long movieId : booking.getMovies()) {
        Movie movie = modelMapper.map(movieClient.getMovieById(movieId).getData(), Movie.class);
        movies.add(movie);
      }

    }
    return movies;
  }
}
