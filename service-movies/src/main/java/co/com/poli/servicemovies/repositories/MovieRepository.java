package co.com.poli.servicemovies.repositories;

import co.com.poli.servicemovies.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
