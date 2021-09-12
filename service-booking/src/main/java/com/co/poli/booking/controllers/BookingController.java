package com.co.poli.booking.controllers;

import com.co.poli.booking.entities.Booking;
import com.co.poli.booking.services.BookingService;
import com.example.multimodule.service.MyService;
import com.example.multimodule.service.utils.Response;
import com.example.multimodule.service.utils.ResponseBuilder;
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
  private final ResponseBuilder responseBuilder;
  private final MyService myService;

  @PostMapping
  public Response createBooking (@Valid @RequestBody Booking booking, BindingResult result) {
    if (result.hasErrors()) {
      return responseBuilder.failed(myService.formatMessage(result));
    }

    bookingService.createBooking(booking);
    return responseBuilder.success(booking);
  }

  @GetMapping
  public Response getAllBookings () {
    List<Booking> allBookings = bookingService.getAllBookings();

    if (allBookings.isEmpty()) {
      return responseBuilder.failed(null);
    }

    return responseBuilder.success(allBookings);
  }

  @GetMapping("/{id}")
  public Response findBookingById (@PathVariable("id") Long id) {
    Booking booking = bookingService.findBookingById(id);
    if (booking == null) {
      return responseBuilder.failed(null);
    }

    return responseBuilder.success(booking);
  }

  @GetMapping("/users/{id}")
  public Response findBookingByUserId (@PathVariable("id") Long userId) {
    Booking booking = bookingService.findByUserId(userId);
    if (booking == null) {
      return responseBuilder.failed(null);
    }

    return responseBuilder.success(booking);
  }

  @DeleteMapping("/{id}")
  public Response deleteBooking (@PathVariable("id") Long id) {
    Booking booking = bookingService.deleteBooking(id);
    if (booking == null) {
      return responseBuilder.failed(null);
    }

    return responseBuilder.success(booking);
  }
}
