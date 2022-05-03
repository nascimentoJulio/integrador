package models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Usuario {
	
    private String nome;
    private String sobrenome;
    private LocalDate dataNascimento;
    private String email;
    private String cpf;
    private int id;
    private int ddd;
    private int numeroCelular;
    private int idade;
    private String senha;
    
	public Usuario() {
	}

	public Usuario(String nome, String sobrenome, LocalDate dataNascimento, String email, String cpf, int ddd, int numeroCelular) {
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.dataNascimento = dataNascimento;
		this.email = email;
		this.cpf = cpf;
		this.ddd = ddd;
		this.numeroCelular = numeroCelular;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public int getDdd() {
		return ddd;
	}

	public void setDdd(int ddd) {
		this.ddd = ddd;
	}

	public int getNumeroCelular() {
		return numeroCelular;
	}

	public void setNumeroCelular(int numeroCelular) {
		this.numeroCelular = numeroCelular;
	}
	
	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
	
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public void calculaIdade() {
		idade = (int)ChronoUnit.YEARS.between(dataNascimento, LocalDate.now());
	}

	@Override
	public String toString() {
		return "Usuario [nome = " + nome + ", sobrenome = " + sobrenome + 
				", dataNascimento = " + dataNascimento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + ", idade = " + idade +
				", email = " + email + ", cpf = " + cpf + ", ddd = " + ddd + ", numeroCelular = " + numeroCelular + ", id = " + id + "]";
	}
    
}
