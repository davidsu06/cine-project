package com.co.poli.booking.clients;

import com.example.multimodule.service.utils.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "service-showtime", fallback = ShowtimeFallBackHystrix.class)
public interface ShowtimeClient {
  @GetMapping("/showtimes/{id}")
  Response findShowtimeById (@PathVariable("id") Long id);
}
