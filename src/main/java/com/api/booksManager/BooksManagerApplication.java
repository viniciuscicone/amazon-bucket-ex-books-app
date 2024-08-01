package com.api.booksManager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class BooksManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BooksManagerApplication.class, args);
	}

}
