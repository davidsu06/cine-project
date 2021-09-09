package com.co.poli.booking.controllers;

import com.co.poli.booking.entities.Booking;
import com.co.poli.booking.services.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/bookings")
public class BookingController {
  private final BookingService bookingService;

  @PostMapping
  public ResponseEntity<Booking> createBooking (@Valid @RequestBody Booking booking, BindingResult result) {
    if (result.hasErrors()) {
      return ResponseEntity.badRequest().build();
    }

    bookingService.createBooking(booking);
    return ResponseEntity.ok(booking);
  }

  @GetMapping
  public ResponseEntity<List<Booking>> getAllBookings () {
    List<Booking> allBookings = bookingService.getAllBookings();

    if (allBookings.isEmpty()) {
      return ResponseEntity.noContent().build();
    }

    return ResponseEntity.ok(allBookings);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Booking> findBookingById (@PathVariable("id") Long id) {
    Booking booking = bookingService.findBookingById(id);
    if (booking == null) {
      return ResponseEntity.noContent().build();
    }

    return ResponseEntity.ok(booking);
  }

  @GetMapping("/Users/{userid}")
  public ResponseEntity<Booking> findBookingByUserId (@PathVariable("userid") Long userId) {
    Booking booking = bookingService.findBookingByUserId(userId);
    if (booking == null) {
      return ResponseEntity.noContent().build();
    }

    return ResponseEntity.ok(booking);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Booking> deleteBooking (@PathVariable("id") Long id) {
    Booking booking = bookingService.deleteBooking(id);
    if (booking == null) {
      return ResponseEntity.noContent().build();
    }

    return ResponseEntity.ok(booking);
  }
}
