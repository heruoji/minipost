package com.example.minipost.http;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.example.minipost")
@EnableJpaRepositories(basePackages = "com.example.minipost.db.mysql")
@EntityScan(basePackages = "com.example.minipost.db.mysql")
public class MinipostApplication {

	public static void main(String[] args) {
		SpringApplication.run(MinipostApplication.class, args);
	}

}
