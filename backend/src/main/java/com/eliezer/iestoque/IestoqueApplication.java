package com.eliezer.iestoque;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class IestoqueApplication {

	public static void main(String[] args) {
		SpringApplication.run(IestoqueApplication.class, args);
	}
}
