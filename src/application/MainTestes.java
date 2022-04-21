package application;

import java.time.LocalDate;
import java.util.Locale;
import java.util.Scanner;

import models.Usuario;

public class MainTestes {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner teclado = new Scanner(System.in);
		
		Usuario usuario1 = new Usuario("João", "Souza", LocalDate.parse("2003-08-06"), "joao.souza@gmail.com", "123.456.789-10", 51, 998348209);
		usuario1.calculaIdade();
		
		Usuario usuario2 = new Usuario("Pedro", "Silva", LocalDate.parse("2000-03-29"), "pedrosilva234@gmail.com", "987.654.321-01", 45, 954236754);
		usuario2.calculaIdade();
		
		Usuario usuario3 = new Usuario();
	
		usuario3.setNome("José");
		usuario3.setEmail("josericardo@hotmail.com");
		usuario3.setDataNascimento(LocalDate.parse("1995-02-01"));
		usuario3.calculaIdade();
		
		System.out.println(usuario1);
		System.out.println(usuario2);
		System.out.println(usuario3);
		
		teclado.close();
	}

}
