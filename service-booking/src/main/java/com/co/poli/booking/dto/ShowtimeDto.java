package com.co.poli.booking.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
public class ShowtimeDto {

    private Long id;
    private Date date;

    @NotNull
    private Long[] movies;

    private List<MovieDto> moviesObj;

}
