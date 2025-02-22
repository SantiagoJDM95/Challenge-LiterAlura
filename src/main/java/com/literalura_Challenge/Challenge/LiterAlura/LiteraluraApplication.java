package com.literalura_Challenge.Challenge.LiterAlura;

import com.literalura_Challenge.Challenge.LiterAlura.Main.Main;
import com.literalura_Challenge.Challenge.LiterAlura.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

	@Autowired
	private BookRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Override
	public void run ( String... args ) throws Exception {
		var data = new Main(repository);
		data.showMenu();
	}
}
