package com.co.poli.showtimes.clients;

import com.co.poli.showtimes.models.Movie;
import com.example.multimodule.service.utils.Response;
import com.example.multimodule.service.utils.ResponseBuilder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class MovieFallBackHystrix implements MovieClient{
  private final ResponseBuilder responseBuilder;

  @Override
  public Response getMovieById(Long id) {
    return responseBuilder.success(new Movie());
  }
}
