package services;

import models.Despesa;
import java.util.List;

public interface DespesaService {
    void criarDespesa(Despesa despesa);

    void atualizarDespesa(int id ,Despesa despesa);

    List<Despesa> listarDespesas();

    Despesa obterDespesaById(int id);

    void deletarDespesa(int id);
}
