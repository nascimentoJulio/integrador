package services;
import models.Investimento;

import java.util.List;

public interface InvestimentoService {
    void criarInvestimento(Investimento investimento);

    void atualizarInvestimento(Investimento investimento);

    List<Investimento> listarInvestimento(String email);

    Investimento obterInvestimentoById(int id, String email);

    void deletarInvestimento(int id);
}
