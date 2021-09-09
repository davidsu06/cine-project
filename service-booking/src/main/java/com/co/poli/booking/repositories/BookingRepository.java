package com.co.poli.booking.repositories;

import com.co.poli.booking.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Long> {
  Booking findByUserId(Long userId);
}
