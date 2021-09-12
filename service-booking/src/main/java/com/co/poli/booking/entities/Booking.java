package com.co.poli.booking.entities;

import com.co.poli.booking.models.Movie;
import com.co.poli.booking.models.Showtime;
import com.co.poli.booking.models.User;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@Builder(toBuilder = true)
@Table(name = "bookings")
@AllArgsConstructor
@NoArgsConstructor
public class Booking {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "user_id", nullable = false)
  @NotNull(message = "El id del usuario es obligatorio")
  private Long userId;
  @Transient
  private User user;

  @Column(name = "showtime_id", nullable = false)
  @NotNull(message = "El id de la programación es obligatorio")
  private Long showtimeId;
  @Transient
  private Showtime showtime;

  @Column(name = "movies", nullable = false)
  @NotNull
  private Long[] movies;
  @Transient
  private List<Movie> moviesObj;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Booking booking = (Booking) o;
    return Objects.equals(id, booking.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
