package models;

import utils.enuns.TipoDespesa;

import java.sql.Date;

public class Despesa {

    private int id;

    private String nome;

    private String descricao;

    private Date dataDespesa;

    private double valorDespesa;

    private TipoDespesa tipoDespesa;
    
    public Despesa() {
	}

	public Despesa(int id, String nome, String descricao, Date dataDespesa, double valorDespesa, TipoDespesa tipoDespesa) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.dataDespesa = dataDespesa;
        this.valorDespesa = valorDespesa;
        this.tipoDespesa = tipoDespesa;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Date getDataDespesa() {
		return dataDespesa;
	}

	public void setDataDespesa(Date dataDespesa) {
		this.dataDespesa = dataDespesa;
	}

	public double getValorDespesa() {
		return valorDespesa;
	}

	public void setValorDespesa(double valorDespesa) {
		this.valorDespesa = valorDespesa;
	}

	public TipoDespesa getTipoDespesa() {
		return tipoDespesa;
	}

	public void setTipoDespesa(TipoDespesa tipoDespesa) {
		this.tipoDespesa = tipoDespesa;
	}
	
	public void setTipoDespesa2(Object tipoDespesa) {
		this.tipoDespesa = (TipoDespesa) tipoDespesa;
	}

	@Override
	public String toString() {
		return "Despesa [id=" + id + ", nome=" + nome + ", descricao=" + descricao + ", dataDespesa=" + dataDespesa
				+ ", valorDespesa=" + valorDespesa + ", tipoDespesa=" + tipoDespesa + "]";
	}
    
}
