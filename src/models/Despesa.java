package models;

import utils.enuns.TipoDespesa;

import java.time.LocalDate;

public class Despesa {

    private int id;

    private String nome;

    private String descricao;

    private LocalDate dataDespesa;

    private double valorDespesa;

    private TipoDespesa tipoDespesa;

    public Despesa(int id, String nome, String descricao, LocalDate dataDespesa, double valorDespesa, TipoDespesa tipoDespesa) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.dataDespesa = dataDespesa;
        this.valorDespesa = valorDespesa;
        this.tipoDespesa = tipoDespesa;
    }
}
