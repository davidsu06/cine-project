package com.co.poli.showtimes.controllers;

import com.co.poli.showtimes.dto.ShowtimeDto;
import com.co.poli.showtimes.entities.Showtime;
import com.co.poli.showtimes.services.ShowtimeService;
import com.example.multimodule.service.CommonService;
import com.example.multimodule.service.utils.Response;
import com.example.multimodule.service.utils.ResponseBuilder;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/showtimes")
public class ShowtimeController {
  private  final ShowtimeService showtimeService;
  private  final ResponseBuilder responseBuilder;
  private  final CommonService commonService;
  private final ModelMapper modelMapper;

  @PostMapping
  public Response createShowtime (@Valid @RequestBody ShowtimeDto showtimeDto, BindingResult result) {
    if (result.hasErrors()) {
      return responseBuilder.failed(commonService.formatMessage(result));
    }

    Showtime showtime = modelMapper.map(showtimeDto, Showtime.class);
    showtimeService.createShowtime(showtime);
    ShowtimeDto showtimeDtoRespuesta = modelMapper.map(showtime, ShowtimeDto.class);

    return responseBuilder.success(showtimeDtoRespuesta);
  }

  @GetMapping
  public Response getListShowtime () {
    List<Showtime> listShowtime = showtimeService.getListShowtime();

    if (listShowtime.isEmpty()) {
      return responseBuilder.failed(null);
    }

    List<ShowtimeDto> showtimeDtos = listShowtime.stream()
            .map(showtime -> modelMapper.map(showtime,ShowtimeDto.class))
            .collect(Collectors.toList());

    return responseBuilder.success(showtimeDtos);
  }

  @GetMapping("/{id}")
  public Response findShowtimeById (@PathVariable("id") Long id) {
    Showtime showtime = showtimeService.getShowtimeById(id);

    if (showtime == null) { return responseBuilder.failed(null); }

    ShowtimeDto showtimeDtoRespuesta = modelMapper.map(showtime, ShowtimeDto.class);

    return responseBuilder.success(showtimeDtoRespuesta);
  }

  @PutMapping("/{id}")
  public Response updateShowtime (@Valid @PathVariable("id") Long id, @RequestBody ShowtimeDto showtimeDto, BindingResult result) {
    if (result.hasErrors()) {
      return responseBuilder.failed(result);
    }

    Showtime showtime = modelMapper.map(showtimeDto, Showtime.class);
    showtimeService.updateShowtime(id, showtime);
    ShowtimeDto showtimeDtoRespuesta = modelMapper.map(showtime, ShowtimeDto.class);

    return responseBuilder.success(showtimeDtoRespuesta);
  }
}
