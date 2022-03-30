package models;

import utils.enuns.TipoInvestimento;

import java.time.LocalDate;

public class Investimento {

    private int id;

    private TipoInvestimento tipoInvestimento;

    private double valorInvestido;

    private double taxaRendimento;

    private LocalDate dataInvestimento;

    private String nome;

    public Investimento(int id, TipoInvestimento tipoInvestimento, double valorInvestido, double taxaRendimento, LocalDate dataInvestimento, String nome) {
        this.id = id;
        this.tipoInvestimento = tipoInvestimento;
        this.valorInvestido = valorInvestido;
        this.taxaRendimento = taxaRendimento;
        this.dataInvestimento = dataInvestimento;
        this.nome = nome;
    }
}

