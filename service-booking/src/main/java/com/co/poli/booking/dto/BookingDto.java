package com.co.poli.booking.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class BookingDto {

    private Long id;

    @NotNull(message = "El id del usuario es obligatorio")
    private Long userId;
    private UserDto user;

    @NotNull(message = "El id de la programación es obligatorio")
    private Long showtimeId;
    private ShowtimeDto showtime;

    @NotNull
    private Long[] movies;
    private List<MovieDto> moviesObj;

}
