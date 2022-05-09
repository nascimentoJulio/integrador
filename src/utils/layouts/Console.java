package utils.layouts;

public class Console {
	
    public static void imprimirMenuInicial() {
        imprimirTracos();
        System.out.println("|                 GAXTOS                   |");
        imprimirTracos();
        imprimirLaterais();
        imprimirLaterais();
        System.out.println("| [1] Fazer login                          |");
        System.out.println("| [2] Cadastrar                            |");
        System.out.println("| [3] Encerrar Programa                    |");
        imprimirLaterais();
        imprimirLaterais();
        imprimirTracos();

    }
    
    public static void imprimirOpcao1() {
    	imprimirTracos();
    	System.out.println("|                 GAXTOS                   |");
        imprimirTracos();
        imprimirLaterais();
        imprimirLaterais();
        System.out.println("|   Insira seu e-mail e sua senha abaixo   |");
        imprimirLaterais();
        imprimirLaterais();
        imprimirTracos();
    }
    
    public static void imprimirOpcao2() {
    	imprimirTracos();
    	System.out.println("|                 GAXTOS                   |");
        imprimirTracos();
        imprimirLaterais();
        imprimirLaterais();
        System.out.println("|         Preencha os dados abaixo:        |");
        imprimirLaterais();
        imprimirLaterais();
        imprimirTracos();
    }
    
    public static void imprimirOpcao3() {
    	imprimirTracos();
    	System.out.println("|                 GAXTOS                   |");
        imprimirTracos();
        imprimirLaterais();
        imprimirLaterais();
        System.out.println("|   Obrigado por usar GAXTOS, até breve!   |");
        imprimirLaterais();
        imprimirLaterais();
        imprimirTracos();
    }


    public static void imprimirMenuDespesa() {
        imprimirTracos();
        System.out.println("|                 GAXTOS                   |");
        imprimirTracos();
        imprimirLaterais();
        imprimirLaterais();
        System.out.println("| [1] Cria despesa                         |");
        System.out.println("| [2] Atualizar despesa                    |");
        System.out.println("| [3] Listar todas despesa                 |");
        System.out.println("| [4] Listar despesa por id                |");
        System.out.println("| [5] Deletar despesa                      |");
        imprimirLaterais();
        imprimirLaterais();
        imprimirTracos();

    }

    public static void imprimirMenuMeta() {
        imprimirTracos();
        System.out.println("|                 GAXTOS                   |");
        imprimirTracos();
        imprimirLaterais();
        imprimirLaterais();
        System.out.println("| [1] Cria meta                            |");
        System.out.println("| [2] Atualizar meta                       |");
        System.out.println("| [3] Listar todas metas                   |");
        System.out.println("| [4] Listar meta por id                   |");
        System.out.println("| [5] Deletar meta                      |");
        imprimirLaterais();
        imprimirLaterais();
        imprimirTracos();

    }

    public static void imprimirMenuReceita() {
        imprimirTracos();
        System.out.println("|                 GAXTOS                   |");
        imprimirTracos();
        imprimirLaterais();
        imprimirLaterais();
        System.out.println("| [1] Cria receita                         |");
        System.out.println("| [2] Atualizar receita                    |");
        System.out.println("| [3] Listar todas receita                 |");
        System.out.println("| [4] Listar receita por id                |");
        System.out.println("| [5] Deletar receita                      |");
        imprimirLaterais();
        imprimirLaterais();
        imprimirTracos();

    }

    public static void imprimirMenuInvestimento() {
        imprimirTracos();
        System.out.println("|                 GAXTOS                   |");
        imprimirTracos();
        imprimirLaterais();
        imprimirLaterais();
        System.out.println("| [1] Cria investimento                    |");
        System.out.println("| [2] Atualizar investimento               |");
        System.out.println("| [3] Listar todas investimento            |");
        System.out.println("| [4] Listar investimento por id           |");
        System.out.println("| [5] Deletar investimento                 |");
        imprimirLaterais();
        imprimirLaterais();
        imprimirTracos();

    }

    public static void imprimirMenuLogado() {
        imprimirTracos();
        System.out.println("|                 GAXTOS                   |");
        imprimirTracos();
        imprimirLaterais();
        imprimirLaterais();
        System.out.println("| [1] Menu despesas                        |");
        System.out.println("| [2] Voltar                               |");
        imprimirLaterais();
        imprimirLaterais();
        imprimirTracos();

    }

    
    
    
    private static void imprimirTracos() {
        System.out.println("|==========================================|");
    }

    private static void imprimirLaterais() {
        System.out.println("|                                          |");
    }


}
