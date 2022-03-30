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

    public Receita(int id, TipoReceita tipoReceita, double valorReceita, LocalDate dataRecebimento, String nome, String descricao) {
        this.id = id;
        this.tipoReceita = tipoReceita;
        this.valorReceita = valorReceita;
        this.dataRecebimento = dataRecebimento;
        this.nome = nome;
        this.descricao = descricao;
    }
}
