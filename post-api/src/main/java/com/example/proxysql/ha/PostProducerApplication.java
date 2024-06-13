package com.example.proxysql.ha;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"core"})
public class PostProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PostProducerApplication.class, args);
	}

}
