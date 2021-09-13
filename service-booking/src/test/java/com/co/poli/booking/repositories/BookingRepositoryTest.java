package com.co.poli.booking.repositories;

import com.co.poli.booking.entities.Booking;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class BookingRepositoryTest {

    @Autowired
    private BookingRepository bookingRepository;

    @Test
    void when_saveBooking_return_bookingDB(){
        Long[] moviesIDS = new Long[2];
        moviesIDS[0] = 1L;
        moviesIDS[1] = 1L;

        Booking booking = Booking.builder()
                .id(1L)
                .userId(1L)
                .showtimeId(1L)
                .movies(moviesIDS)
                .build();

        bookingRepository.save(booking);
        Booking bookingDB = bookingRepository.findByUserId(booking.getUserId());
        Assertions.assertThat(bookingDB).isNotNull();
    }
}
