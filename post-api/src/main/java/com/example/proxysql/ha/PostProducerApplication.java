package com.example.proxysql.ha;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.proxysql.ha", "core"})
public class PostProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PostProducerApplication.class, args);
	}

}
