package com.agence.Gr3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.agence.Gr3.frontend.Services.Frontend;

/*
	CommandLineRunner permet l'execution de code après l'initialisation du Contexte de Spring.
	Dans le cas présent, il s'agit de l'affichage et de l'écriture à la console qui simule un frontend.

*/

@SpringBootApplication
public class Gr3Application implements CommandLineRunner {

	@Autowired
	private Frontend frontend;

	public static void main(String[] args) {
		SpringApplication.run(Gr3Application.class, args);
	}

	@Override
	public void run(String... args) {

		frontend.displayMenu();
	}
}
