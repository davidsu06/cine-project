package com.co.poli.showtimes.controllers;

import com.co.poli.showtimes.entities.Showtime;
import com.co.poli.showtimes.services.ShowtimeService;
import com.example.multimodule.service.MyService;
import com.example.multimodule.service.utils.Response;
import com.example.multimodule.service.utils.ResponseBuilder;
import lombok.AllArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/showtimes")
public class ShowtimeController {
  private  final ShowtimeService showtimeService;
  private  final ResponseBuilder responseBuilder;
  private  final MyService myService;

  @PostMapping
  public Response createShowtime (@Valid @RequestBody Showtime showtime, BindingResult result) {
    if (result.hasErrors()) {
      return responseBuilder.failed(myService.formatMessage(result));
    }

    showtimeService.createShowtime(showtime);
    return responseBuilder.success(showtime);
  }

  @GetMapping
  public Response getListShowtime () {
    List<Showtime> listShowtime = showtimeService.getListShowtime();

    if (listShowtime.isEmpty()) {
      return responseBuilder.failed(null);
    }

    return responseBuilder.success(listShowtime);
  }

  @GetMapping("/{id}")
  public Response findShowtimeById (@PathVariable("id") Long id) {
    Showtime showtime = showtimeService.getShowtimeById(id);

    if (showtime == null) { return responseBuilder.failed(null); }

    return responseBuilder.success(showtime);
  }

  @PutMapping("/{id}")
  public Response updateShowtime (@Valid @PathVariable("id") Long id, @RequestBody Showtime showtime, BindingResult result) {
    if (result.hasErrors()) {
      return responseBuilder.failed(result);
    }

    showtimeService.updateShowtime(id, showtime);
    return responseBuilder.success(showtime);
  }
}
