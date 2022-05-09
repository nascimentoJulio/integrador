package services;

import models.Investimento;
import models.Receita;

import java.util.List;

public interface ReceitaService {

  void criarReceita(Receita receita);

  void atualizarReceita(Receita receita);

  List<Receita> listarReceita(String email);

  Receita obterReceitaById(int id, String email);

  void deletarInvestimento(int id);
}
