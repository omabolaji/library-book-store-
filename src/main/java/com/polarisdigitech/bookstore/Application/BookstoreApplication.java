package com.polarisdigitech.bookstore.Application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@ComponentScan({"com.polarisdigitech.bookstore"})
@EntityScan("com.polarisdigitech.bookstore.Entity")
@EnableJpaRepositories("com.polarisdigitech.bookstore.Repository")
@SpringBootApplication
public class BookstoreApplication {

	public static void main(String[] args) {
		System.setProperty("server.servlet.context-path", "/bookstore/api");
		SpringApplication.run(BookstoreApplication.class, args);
	}

}
