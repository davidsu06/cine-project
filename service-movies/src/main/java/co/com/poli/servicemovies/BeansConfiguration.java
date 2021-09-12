package co.com.poli.servicemovies;

import com.example.multimodule.service.MyService;
import com.example.multimodule.service.utils.ResponseBuilder;
import org.springframework.context.annotation.Bean;

public class BeansConfiguration {
  @Bean
  public MyService myService() {
    return new MyService();
  }

  @Bean
  public ResponseBuilder responseBuilder(){
    return new ResponseBuilder();
  }
}


