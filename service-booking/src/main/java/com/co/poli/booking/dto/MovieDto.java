package com.co.poli.booking.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Data
public class MovieDto {

    private Long id;

    @NotEmpty(message = "El titulo no debe estar vacío")
    private String title;

    @NotEmpty(message = "El director no debe estar vacio")
    private String director;

    @Min(value = 1)
    @Max(value = 5)
    private Integer rating;

}
