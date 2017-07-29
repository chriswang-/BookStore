/**
 * Chris Book Store.
 */

package com.chris.bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.chris.bookstore")
@EntityScan(basePackages = "com.chris.bookstore")
public class BookStoreApplication implements CommandLineRunner{
	
	@Override
	public void run(String... args) {
		if (args.length > 0 && args[0].equals("?")) {
			//TODO: do it if the parameter transfer behind the jar file.
		}
	}
	
	public static void main(String[] args) {
        System.out.print("bookstore starting.");
        SpringApplication app = new SpringApplication(BookStoreApplication.class);
        app.setBannerMode(org.springframework.boot.Banner.Mode.OFF);
        app.run(args);
        System.out.print("bookstore started.");
	}
}

