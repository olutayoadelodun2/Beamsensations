package com.editay.bsps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;




@Configuration
@EnableJpaRepositories
@SpringBootApplication
@EnableCaching
public class BeamsensationsApplication {

	public static void main(String[] args) {
		SpringApplication.run(BeamsensationsApplication.class, args);
	}

}
