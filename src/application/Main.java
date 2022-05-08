package application;

import models.Despesa;
import repository.Cache;
import repository.dao.*;
import repository.db.DB;
import services.DespesaService;
import services.DespesaServiceImpl;
import services.UsuarioService;
import services.UsuarioServiceImpl;
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

    private static MetaDao metaDao;

    private static ReceitaDao receitaDao;

    private static void injetarDependencias() {
        Connection conn = DB.getConnection();
        usuarioDao = new UsuarioDaoJDBC(conn);
        usuarioService = new UsuarioServiceImpl(usuarioDao);

        despesaDao = new DespesaDaoJDBC(conn);
        despesaService = new DespesaServiceImpl(despesaDao);

        investimentoDao = new InvestimentoDaoJDBC(conn);

        metaDao = new MetaDaoJDBC(conn);

        receitaDao = new ReceitaDaoJDBC(conn);
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
                despesaService.CriarDespesa(criarDespesa());
                acaoDespesa();
            }
            case 2 -> acaoCadastro();
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
            default -> acaoDespesa();
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

    private static Despesa criarDespesa(){
        System.out.print("nome: ");
        String nome = input.next();

        System.out.print("Descricao: ");
        String descricao = input.next();

        System.out.print("Data criacao: ");
        LocalDate dataCriacao = Conversores.converterFormatoDataEng(input.next());

        System.out.print("Valor despesa:");
        double valorDespesa = input.nextDouble();


        return new Despesa(nome,descricao, java.sql.Date.valueOf(dataCriacao), valorDespesa, TipoDespesa.FIXA, Cache.obterToken());
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

