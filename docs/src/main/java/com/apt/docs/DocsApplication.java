package com.apt.docs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
@EnableAsync
public class DocsApplication {

	public static void main(String[] args) {
		SpringApplication.run(DocsApplication.class, args);

	}

}
 