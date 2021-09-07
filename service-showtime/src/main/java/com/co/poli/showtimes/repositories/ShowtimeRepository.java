package com.co.poli.showtimes.repositories;

import com.co.poli.showtimes.entities.Showtime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowtimeRepository extends JpaRepository<Showtime,Long> {
}
