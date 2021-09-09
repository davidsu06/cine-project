package com.co.poli.booking.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

@Entity
@Setter
@Getter
@Table(name = "bookings")
public class Booking {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "user_id", nullable = false)
  @NotNull(message = "El id del usuario es obligatorio")
  private Long userId;

  @Column(name = "showtime_id", nullable = false)
  @NotNull(message = "El id de la programación es obligatorio")
  private Long showtimeId;

  @Valid
  @ElementCollection(targetClass = Long.class, fetch = FetchType.LAZY)
  private List<Long> movies;

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
