package com.co.poli.booking.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class UserDto {

    private Long id;

    @NotEmpty(message = "El nombre del usuario es obligatorio")
    private String name;

    @NotEmpty(message = "El apellido del usuario es obligatorio")
    private String lastname;

}
