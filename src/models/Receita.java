package models;

import utils.enuns.TipoReceita;

import java.time.LocalDate;

public class Receita {
    private int id;

    private TipoReceita tipoReceita;

    private double valorReceita;

    private LocalDate dataRecebimento;

    private String nome;

    private String descricao;

    private Usuario usuario;

    public Receita( TipoReceita tipoReceita, double valorReceita, LocalDate dataRecebimento, String nome, String descricao) {
        this.tipoReceita = tipoReceita;
        this.valorReceita = valorReceita;
        this.dataRecebimento = dataRecebimento;
        this.nome = nome;
        this.descricao = descricao;
    }
}
