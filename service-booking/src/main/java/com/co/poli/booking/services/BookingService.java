package com.co.poli.booking.services;

import com.co.poli.booking.entities.Booking;

import java.util.List;

public interface BookingService {
  Booking createBooking(Booking booking);
  List<Booking> getAllBookings();
  Booking findBookingById(Long id);
  Booking deleteBooking(Long id);
  Booking findByUserId(Long userId);
}
