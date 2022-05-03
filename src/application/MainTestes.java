package application;

import java.sql.Connection;
import java.util.Locale;
import java.util.Scanner;

import db.*;
import utils.layouts.Console;

public class MainTestes {

	public static void main(String[] args) {
		Scanner teclado = new Scanner(System.in);
		
		Console.imprimirMenuInicial();
		
		System.out.print("Opção: ");
		
		int opcao = teclado.nextInt();
		
		switch(opcao) {
			case 1:{
				Console.imprimirOpcao1();
				
				System.out.print("E-mail: ");
				
				String email = teclado.next();
				
				System.out.print("Senha: ");
				
				String senha = teclado.next();
				
			}
			case 2:{
				Console.imprimirOpcao2();
			}
			case 3:{
				Console.imprimirOpcao3();
			}
		}
		
		teclado.close();
	}

}
