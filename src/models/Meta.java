package models;

import java.sql.Date;

import utils.enuns.TipoMeta;

public class Meta {
    private int id;
    
    private String nome;
    
    private String descricao;
    
    private TipoMeta tipo;
    
    private double valorNecessario;
    
    private Date dataInicio;
    
    private Date dataConclusao;
    
    private Usuario usuario;

	public Meta(int id, String nome, String descricao, TipoMeta tipo, double valorNecessario, Date dataInicio,
			Date dataConclusao, Usuario usuario) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.tipo = tipo;
		this.valorNecessario = valorNecessario;
		this.dataInicio = dataInicio;
		this.dataConclusao = dataConclusao;
		this.usuario = usuario;
	}

	public Meta() {
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

	public TipoMeta getTipo() {
		return tipo;
	}

	public void setTipo(TipoMeta tipo) {
		this.tipo = tipo;
	}

	public double getValorNecessario() {
		return valorNecessario;
	}

	public void setValorNecessario(double valorNecessario) {
		this.valorNecessario = valorNecessario;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataConclusao() {
		return dataConclusao;
	}

	public void setDataConclusao(Date dataConclusao) {
		this.dataConclusao = dataConclusao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
    
    
}
