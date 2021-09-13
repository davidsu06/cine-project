package co.com.poli.servicemovies.repositories;

import co.com.poli.servicemovies.entities.Movie;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class MovieRepositoryTest {

    @Autowired
    private MovieRepository movieRepository;

    @Test
    void when_saveMovie_return_movieDB(){
        Movie movie = Movie.builder()
                .id(1L)
                .title("Title test")
                .director("Director test")
                .rating(5)
                .build();

        movieRepository.save(movie);
        Movie movieDB = movieRepository.findById(movie.getId()).orElse(null);

        Assertions.assertThat(movieDB).isNotNull();
    }
}
