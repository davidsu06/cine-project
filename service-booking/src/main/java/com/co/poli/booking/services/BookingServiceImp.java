package com.co.poli.booking.services;

import com.co.poli.booking.entities.Booking;
import com.co.poli.booking.repositories.BookingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookingServiceImp implements BookingService {
  private final BookingRepository bookingRepository;

  @Override
  public Booking createBooking(Booking booking) {
    return bookingRepository.save(booking);
  }

  @Override
  public List<Booking> getAllBookings() {
    return bookingRepository.findAll();
  }

  @Override
  public Booking findBookingById(Long id) {
    return bookingRepository.findById(id).orElse(null);
  }

  @Override
  public Booking deleteBooking(Long id) {
    Booking booking = bookingRepository.findById(id).orElse(null);
    bookingRepository.delete(booking);

    return booking;
  }

  @Override
  public Booking findBookingByUserId(Long userId) {
    return bookingRepository.findByUserId(userId);
  }
}
