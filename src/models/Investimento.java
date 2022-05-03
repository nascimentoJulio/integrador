package models;

import utils.enuns.TipoInvestimento;

import java.sql.Date;

public class Investimento {

    private int id;

    private TipoInvestimento tipoInvestimento;

    private double valorInvestido;

    private double taxaRendimento;

    private Date dataInvestimento;

    private String nome;
    
    private String descricao;
    
    private Usuario usuario;

    public Investimento(int id, TipoInvestimento tipoInvestimento, double valorInvestido, double taxaRendimento,
    		Date dataInvestimento, String nome, String descricao, Usuario usuario) {
        this.id = id;
        this.tipoInvestimento = tipoInvestimento;
        this.valorInvestido = valorInvestido;
        this.taxaRendimento = taxaRendimento;
        this.dataInvestimento = dataInvestimento;
        this.nome = nome;
        this.descricao = descricao;
        this.usuario = usuario;
    }

	public Investimento() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public TipoInvestimento getTipoInvestimento() {
		return tipoInvestimento;
	}

	public void setTipoInvestimento(TipoInvestimento tipoInvestimento) {
		this.tipoInvestimento = tipoInvestimento;
	}

	public double getValorInvestido() {
		return valorInvestido;
	}

	public void setValorInvestido(double valorInvestido) {
		this.valorInvestido = valorInvestido;
	}

	public double getTaxaRendimento() {
		return taxaRendimento;
	}

	public void setTaxaRendimento(double taxaRendimento) {
		this.taxaRendimento = taxaRendimento;
	}

	public Date getDataInvestimento() {
		return dataInvestimento;
	}

	public void setDataInvestimento(Date dataInvestimento) {
		this.dataInvestimento = dataInvestimento;
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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
    
    
}

