package utils.builders;

import models.Despesa;
import models.Investimento;
import models.Meta;
import models.Receita;
import repository.Cache;
import utils.conversores.Conversores;
import utils.enuns.TipoDespesa;
import utils.enuns.TipoInvestimento;
import utils.enuns.TipoMeta;
import utils.enuns.TipoReceita;

import java.time.LocalDate;
import java.util.Scanner;

public class Builder {
  private static Scanner input = new Scanner(System.in);

  public static Despesa criarDespesa() {
    System.out.print("nome: ");
    String nome = input.next();

    System.out.print("Descricao: ");
    String descricao = input.next();

    System.out.print("Data criacao: ");
    LocalDate dataCriacao = Conversores.converterFormatoDataEng(input.next());

    System.out.print("Valor despesa:");
    double valorDespesa = input.nextDouble();


    return new Despesa(nome, descricao, java.sql.Date.valueOf(dataCriacao), valorDespesa, TipoDespesa.FIXA, Cache.obterToken());
  }

  public static Meta criarMeta() {
    System.out.print("nome: ");
    String nome = input.next();

    System.out.print("Descricao: ");
    String descricao = input.next();

    System.out.print("Data inicio: ");
    LocalDate dataInicio = Conversores.converterFormatoDataEng(input.next());

    System.out.print("Data conclusao: ");
    LocalDate dataConclusao = Conversores.converterFormatoDataEng(input.next());

    System.out.print("Valor necessario:");
    double valorNecessario = input.nextDouble();


    return new Meta(0, nome, descricao, TipoMeta.OUTROS, valorNecessario, java.sql.Date.valueOf(dataInicio), java.sql.Date.valueOf(dataConclusao), Cache.obterToken());
  }

  public static Receita criarReceita() {
    System.out.print("nome: ");
    String nome = input.next();

    System.out.print("Descricao: ");
    String descricao = input.next();

    System.out.print("Data inicio: ");
    LocalDate dataInicio = Conversores.converterFormatoDataEng(input.next());

    System.out.print("Data recebimento: ");
    LocalDate dataRecebimento = Conversores.converterFormatoDataEng(input.next());

    System.out.print("Valor necessario:");
    double valorReceita = input.nextDouble();


    return new Receita(0, TipoReceita.SALARIO,valorReceita, java.sql.Date.valueOf(dataRecebimento),nome, descricao,  Cache.obterToken());
  }

  public static Investimento criarInvestimento() {
    System.out.print("nome: ");
    String nome = input.next();

    System.out.print("Descricao: ");
    String descricao = input.next();

    System.out.print("Data do investimento: ");
    LocalDate dataInvestimento = Conversores.converterFormatoDataEng(input.next());

    System.out.print("Valor investido: ");
    double valorInvestido = input.nextDouble();

    System.out.println("Taxa de rendimento: ");
    double taxaRendimento = input.nextDouble();


    return new Investimento(0, TipoInvestimento.OUTROS, valorInvestido, taxaRendimento, java.sql.Date.valueOf(dataInvestimento), nome, descricao, Cache.obterToken());
  }

}
