package services;

import models.Despesa;
import utils.enuns.TipoDespesa;

import java.sql.Date;
import java.util.List;

public interface DespesaService {
    void CriarDespesa(Despesa despesa);

    List<Despesa> listarDespesas();

    Despesa obterDespesaById(int id);

    void DeletarDespesa(int id);
}
