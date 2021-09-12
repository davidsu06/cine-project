package com.co.poli.booking.clients;

import com.co.poli.booking.models.Showtime;
import com.example.multimodule.service.utils.Response;
import com.example.multimodule.service.utils.ResponseBuilder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ShowtimeFallBackHystrix implements ShowtimeClient {
  private final ResponseBuilder responseBuilder;

  @Override
  public Response findShowtimeById(Long id) {
    return responseBuilder.success(new Showtime());
  }
}
