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

    private static void imprimirTracos() {
        System.out.println("|==========================================|");
    }

    private static void imprimirLaterais() {
        System.out.println("|                                          |");
    }


}
