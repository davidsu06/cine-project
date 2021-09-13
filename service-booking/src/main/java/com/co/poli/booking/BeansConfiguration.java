package com.co.poli.booking;

import com.example.multimodule.service.CommonService;
import com.example.multimodule.service.utils.ResponseBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfiguration {
  @Bean
  public CommonService myService() {
    return new CommonService();
  }

  @Bean
  public ResponseBuilder responseBuilder(){
    return new ResponseBuilder();
  }

  @Bean
  public ModelMapper modelMapper(){
    return new ModelMapper();
  }
}
