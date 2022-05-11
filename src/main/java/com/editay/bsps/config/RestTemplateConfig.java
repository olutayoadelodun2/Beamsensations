package com.editay.bsps.config;

import java.time.Duration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
   @Bean
   public RestTemplate restTemplate(RestTemplateBuilder builder) {
      return builder.setConnectTimeout(Duration.ofMillis(60000L)).setReadTimeout(Duration.ofMillis(60000L)).build();
   }
}
