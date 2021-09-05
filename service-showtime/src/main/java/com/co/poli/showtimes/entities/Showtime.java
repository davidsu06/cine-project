package com.co.poli.showtimes.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Setter
@Getter
@Table(name = "showtimes")
public class Showtime {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, unique = true)
  private Long id;

  @Column(name = "date", nullable = false)
  @NotEmpty(message = "La fecha de la reserva es obligatoria")
  private Date date;

  @Column(name = "movies")
  @JoinColumn(name = "movie_id")
  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
  private List<Object> movies;

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
