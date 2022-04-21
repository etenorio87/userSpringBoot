package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class PruebaApplication implements CommandLineRunner {
	public static final Logger log = LoggerFactory.getLogger(PruebaApplication.class);

	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(PruebaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("Encoding password '1qazxsw2' => '{}'", passwordEncoder.encode("1qazxsw2") );
	}
}
