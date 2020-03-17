package com.cts.am.pmsvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan (basePackages = { "com.cts.am.pmsvc"})
@EnableJpaRepositories (basePackages = { "com.cts.am.pmsvc"})
@Configuration
public class PmSvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(PmSvcApplication.class, args);
	}

}
