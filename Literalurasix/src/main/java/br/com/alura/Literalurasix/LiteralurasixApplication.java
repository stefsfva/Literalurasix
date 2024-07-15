package br.com.alura.Literalurasix;

import br.com.alura.Literalurasix.Repository.AutoresRepository;
import br.com.alura.Literalurasix.Repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteralurasixApplication implements CommandLineRunner {

	@Autowired
	AutoresRepository repositorioAutor;

	@Autowired
	LivroRepository repositorioLivro;

	public static void main(String[] args) {
		SpringApplication.run(LiteralurasixApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(repositorioAutor, repositorioLivro);
		principal.exibirMenu();
	}
}
