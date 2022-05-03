package application;

import java.sql.Connection;
import java.util.Locale;
import java.util.Scanner;

import db.*;

public class MainTestes {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner teclado = new Scanner(System.in);
		
		Connection conn = DB.getConnection();
		
		if(conn != null) {
			System.out.println("Conexão com o banco realizada com sucesso!!!");
		}
		
		DB.closeConnection();
		
		teclado.close();
	}

}
