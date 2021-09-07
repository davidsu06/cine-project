package com.co.poli.showtimes.controllers;

import com.co.poli.showtimes.entities.Showtime;
import com.co.poli.showtimes.services.ShowtimeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "showtime")
public class ShowtimeController {
  private  final ShowtimeService showtimeService;

  @PostMapping
  public ResponseEntity<Showtime> createShowtime (@Valid @RequestBody Showtime showtime, BindingResult result) {
    if (result.hasErrors()) {
      return ResponseEntity.badRequest().build();
    }

    showtimeService.createShowtime(showtime);
    return ResponseEntity.ok(showtime);
  }

  @GetMapping
  public ResponseEntity<List<Showtime>> getListShowtime () {
    List<Showtime> listShowtime = showtimeService.getListShowtime();

    if (listShowtime.isEmpty()) {
      return ResponseEntity.noContent().build();
    }

    return ResponseEntity.ok(listShowtime);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Showtime> findShowtimeById (@PathVariable("id") Long id) {
    Showtime showtime = showtimeService.getShowtimeById(id);

    if (showtime == null) { return ResponseEntity.noContent().build(); }

    return ResponseEntity.ok(showtime);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Showtime> updateShowtime (@Valid @PathVariable("id") Long id, @RequestBody Showtime showtime, BindingResult result) {
    if (result.hasErrors()) {
      return ResponseEntity.badRequest().build();
    }

    showtimeService.updateShowtime(id, showtime);
    return ResponseEntity.ok(showtime);
  }
}
