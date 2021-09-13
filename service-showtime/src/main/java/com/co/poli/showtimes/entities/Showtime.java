package com.co.poli.showtimes.entities;

import com.co.poli.showtimes.models.Movie;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@Table(name = "showtimes")
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Showtime {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="id", updatable = false, nullable = false, unique = true)
  private Long id;

  @Column(name = "date", nullable = false)
  private Date date;

  @Column(name = "movies", nullable = false)
  private Long[] movies;

  @Transient
  private List<Movie> moviesObj;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Showtime showtime = (Showtime) o;
    return Objects.equals(id, showtime.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
