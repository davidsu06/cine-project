package com.co.poli.booking.clients;

import com.example.multimodule.service.utils.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "service-movies", fallback = MovieFallBackHystrix.class)
public interface MovieClient {
  @GetMapping("/movies/{id}")
  Response getMovieById(@PathVariable("id") Long id);
}
