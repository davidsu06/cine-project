package com.co.poli.booking.controllers;

import com.co.poli.booking.dto.BookingDto;
import com.co.poli.booking.entities.Booking;
import com.co.poli.booking.services.BookingService;
import com.example.multimodule.service.CommonService;
import com.example.multimodule.service.utils.Response;
import com.example.multimodule.service.utils.ResponseBuilder;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/bookings")
public class BookingController {
  private final BookingService bookingService;
  private final ResponseBuilder responseBuilder;
  private final CommonService commonService;
  private final ModelMapper modelMapper;


  @PostMapping
  public Response createBooking (@Valid @RequestBody BookingDto bookingDto, BindingResult result) {
    if (result.hasErrors()) {
      return responseBuilder.failed(commonService.formatMessage(result));
    }

    modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

    Booking booking = modelMapper.map(bookingDto,Booking.class);
    bookingService.createBooking(booking);
    BookingDto bookingDtoRespuesta = modelMapper.map(booking,BookingDto.class);

    return responseBuilder.success(bookingDtoRespuesta);
  }

  @GetMapping
  public Response getAllBookings () {
    List<Booking> allBookings = bookingService.getAllBookings();

    if (allBookings.isEmpty()) {
      return responseBuilder.failed(null);
    }

    modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

    List<BookingDto> bookingDtos = allBookings.stream()
            .map(booking -> modelMapper.map(booking,BookingDto.class))
            .collect(Collectors.toList());

    return responseBuilder.success(bookingDtos);
  }

  @GetMapping("/{id}")
  public Response findBookingById (@PathVariable("id") Long id) {
    Booking booking = bookingService.findBookingById(id);
    if (booking == null) {
      return responseBuilder.failed(null);
    }

    modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

    BookingDto bookingDtoRespuesta = modelMapper.map(booking,BookingDto.class);

    return responseBuilder.success(bookingDtoRespuesta);
  }

  @GetMapping("/users/{id}")
  public Response findBookingByUserId (@PathVariable("id") Long userId) {
    Booking booking = bookingService.findByUserId(userId);
    if (booking == null) {
      return responseBuilder.failed(null);
    }

    modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

    BookingDto bookingDtoRespuesta = modelMapper.map(booking,BookingDto.class);

    return responseBuilder.success(bookingDtoRespuesta);
  }

  @DeleteMapping("/{id}")
  public Response deleteBooking (@PathVariable("id") Long id) {
    Booking booking = bookingService.findBookingById(id);

    if (booking == null) {
      return responseBuilder.failed(null);
    }

    modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

    bookingService.deleteBooking(id);
    BookingDto bookingDtoRespuesta = modelMapper.map(booking,BookingDto.class);

    return responseBuilder.success(bookingDtoRespuesta);
  }
}
