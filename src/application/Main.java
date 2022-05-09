package application;

import models.Despesa;
import repository.Cache;
import repository.dao.*;
import repository.db.DB;
import services.*;
import utils.builders.Builder;
import utils.conversores.Conversores;
import utils.enuns.TipoDespesa;
import utils.layouts.Console;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;


public class Main {
  private static Scanner input = new Scanner(System.in);

  private static UsuarioService usuarioService;
  private static UsuarioDao usuarioDao;

  private static DespesaDao despesaDao;
  private static DespesaService despesaService;

  private static InvestimentoDao investimentoDao;
  private static InvestimentoService investimentoService;

  private static MetaDao metaDao;
  private static MetaService metaService;

  private static ReceitaService receitaService;
  private static ReceitaDao receitaDao;


  private static void injetarDependencias() {
    Connection conn = DB.getConnection();
    usuarioDao = new UsuarioDaoJDBC(conn);
    usuarioService = new UsuarioServiceImpl(usuarioDao);

    despesaDao = new DespesaDaoJDBC(conn);
    despesaService = new DespesaServiceImpl(despesaDao);

    investimentoDao = new InvestimentoDaoJDBC(conn);
    investimentoService = new InvestimentoServiceImpl(investimentoDao);

    metaDao = new MetaDaoJDBC(conn);
    metaService = new MetaServiceImpl(metaDao);

    receitaDao = new ReceitaDaoJDBC(conn);
    receitaService = new ReceitaServiceImpl(receitaDao);
  }


  private static void menuLogado(boolean isPrimeiraVez) {
    if (isPrimeiraVez) {
      Console.imprimirMenuLogado();
    }
    int escolha = input.nextInt();
    switch (escolha) {
      case 1 -> acaoDespesa();
      case 2 -> Console.imprimirMenuInicial();
      default -> menuLogado(true);
    }
  }

  private static void acaoDespesa() {
    Console.imprimirMenuDespesa();
    int escolha = input.nextInt();
    switch (escolha) {
      case 1 -> {
        despesaService.criarDespesa(Builder.criarDespesa());
        acaoDespesa();
      }
      case 2 -> {
        System.out.println("Informe o id: ");
        int id = input.nextInt();
        despesaService.atualizarDespesa(id, Builder.criarDespesa());
        acaoDespesa();
      }
      case 3 -> {
        despesaService
                .listarDespesas()
                .forEach(despesa -> System.out.println(despesa.toString()));
        acaoDespesa();
      }
      case 4 -> {
        System.out.println("Informe o id da despesa: ");
        System.out.println(despesaService.obterDespesaById(input.nextInt()).toString());
        acaoDespesa();
      }
      case 5 -> {
        System.out.println("Informe o id da despesa: ");
        despesaService.deletarDespesa(input.nextInt());
        acaoDespesa();
      }
      default -> acaoDespesa();
    }
  }

  private static void acaoInvestimento() {
    Console.imprimirMenuInvestimento();
    int escolha = input.nextInt();
    switch (escolha) {
      case 1 -> {
        investimentoService.criarInvestimento(Builder.criarInvestimento());
        acaoInvestimento();
      }
      case 2 -> {
        System.out.println("Informe o id: ");
        int id = input.nextInt();
        investimentoService.atualizarInvestimento(id, Builder.criarInvestimento());
        acaoInvestimento();
      }
      case 3 -> {
        investimentoService
                .listarInvestimento(Cache.obterToken())
                .forEach(investimento -> System.out.println(investimento.toString()));
        acaoInvestimento();
      }
      case 4 -> {
        System.out.println("Informe o id do investimento: ");
        System.out.println(investimentoService.obterInvestimentoById(input.nextInt(), Cache.obterToken()).toString());
        acaoInvestimento();
      }
      case 5 -> {
        System.out.println("Informe o id do investimento: ");
        investimentoService.deletarInvestimento(input.nextInt());
        acaoInvestimento();
      }
      default -> acaoInvestimento();
    }
  }

  private static void acaoMeta() {
    Console.imprimirMenuMeta();
    int escolha = input.nextInt();
    switch (escolha) {
      case 1 -> {
        metaService.criarMeta(Builder.criarMeta());
        acaoMeta();
      }
      case 2 -> {
        System.out.println("Informe o id: ");
        int id = input.nextInt();
        metaService.atualizarMeta(id, Builder.criarMeta());
        acaoMeta();
      }
      case 3 -> {
        metaService
                .listarMeta(Cache.obterToken())
                .forEach(meta -> System.out.println(meta.toString()));
        acaoMeta();
      }
      case 4 -> {
        System.out.println("Informe o id da meta: ");
        System.out.println(metaService.obterMetaById(input.nextInt(), Cache.obterToken()).toString());
        acaoMeta();
      }
      case 5 -> {
        System.out.println("Informe o id da meta: ");
        metaService.deletarMeta(input.nextInt());
        acaoMeta();
      }
      default -> acaoMeta();
    }
  }

  private static void acaoReceita() {
    Console.imprimirMenuMeta();
    int escolha = input.nextInt();
    switch (escolha) {
      case 1 -> {
        receitaService.criarReceita(Builder.criarReceita());
        acaoReceita();
      }
      case 2 -> {
        receitaService.atualizarReceita(Builder.criarReceita());
        acaoReceita();
      }
      case 3 -> {
        receitaService
                .listarReceita(Cache.obterToken())
                .forEach(receita -> System.out.println(receita.toString()));
        acaoReceita();
      }
      case 4 -> {
        System.out.println("Informe o id da receita: ");
        System.out.println(receitaService.obterReceitaById(input.nextInt(), Cache.obterToken()).toString());
        acaoReceita();
      }
      case 5 -> {
        System.out.println("Informe o id da receita: ");
        receitaService.deletarReceita(input.nextInt());
        acaoReceita();
      }
      default -> acaoReceita();
    }
  }

  private static void acaoCadastro() {
    Console.imprimirOpcao2();
    System.out.print("Email: ");
    String email = input.next();
    System.out.print("Senha: ");
    String senha = input.next();

    usuarioService.Cadastrar(email, senha);
    menuInicial(true);
  }


  private static void acaoLogin() {
    Console.imprimirOpcao1();
    System.out.print("Email: ");
    String email = input.next();
    System.out.print("Senha: ");
    String senha = input.next();

    usuarioService.Login(email, senha);
    menuLogado(true);
  }


  public static void main(String[] args) {
    injetarDependencias();
    menuInicial(true);
  }

  private static void menuInicial(boolean isPrimeiraVez) {
    if (isPrimeiraVez) {
      Console.imprimirMenuInicial();
    }
    int escolha = input.nextInt();
    switch (escolha) {
      case 1:
        acaoLogin();
        break;
      case 2:
        acaoCadastro();
        break;
      case 3:
        Console.imprimirOpcao3();
        break;
      default:
        menuInicial(false);
    }
  }


}

