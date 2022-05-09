package models;

import java.sql.Date;

import utils.enuns.TipoReceita;

public class Receita {
    private int id;

    private TipoReceita tipoReceita;

    private double valorReceita;

    private Date dataRecebimento;

    private String nome;

    private String descricao;
    
    private String emailUsuario;
    
    public Receita(){
	}

	public Receita(int id, TipoReceita tipoReceita, double valorReceita, Date dataRecebimento, String nome, String descricao,
				   String emailUsuaro) {
        this.id = id;
        this.tipoReceita = tipoReceita;
        this.valorReceita = valorReceita;
        this.dataRecebimento = dataRecebimento;
        this.nome = nome;
        this.descricao = descricao;
		this.emailUsuario = emailUsuaro;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public TipoReceita getTipoReceita() {
		return tipoReceita;
	}

	public void setTipoReceita(TipoReceita tipoReceita) {
		this.tipoReceita = tipoReceita;
	}
	
	public void setTipoReceita2(Object tipoReceita) {
		this.tipoReceita = (TipoReceita) tipoReceita;
	}

	public double getValorReceita() {
		return valorReceita;
	}

	public void setValorReceita(double valorReceita) {
		this.valorReceita = valorReceita;
	}

	public Date getDataRecebimento() {
		return dataRecebimento;
	}

	public void setDataRecebimento(Date dataRecebimento) {
		this.dataRecebimento = dataRecebimento;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	@Override
	public String toString() {
		return "Receita [id=" + id + ", tipoReceita=" + tipoReceita + ", valorReceita=" + valorReceita
				+ ", dataRecebimento=" + dataRecebimento + ", nome=" + nome + ", descricao=" + descricao + "]";
	}

	public String getEmailUsuario() {
		return emailUsuario;
	}

	public void setEmailUsuario(String emailUsuaro) {
		this.emailUsuario = emailUsuaro;
	}
}
