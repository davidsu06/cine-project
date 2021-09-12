package com.co.poli.booking.clients;

import com.co.poli.booking.models.User;
import com.example.multimodule.service.utils.Response;
import com.example.multimodule.service.utils.ResponseBuilder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserFallBackHystrix implements UserClient {
  private final ResponseBuilder responseBuilder;

  @Override
  public Response getUserById(Long id) {
    return responseBuilder.success(new User());
  }
}
