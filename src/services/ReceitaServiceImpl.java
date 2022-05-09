package services;

import models.Investimento;
import models.Receita;
import repository.dao.ReceitaDao;

import java.util.List;

public class ReceitaServiceImpl implements ReceitaService {

  private ReceitaDao receitaDao;

  public ReceitaServiceImpl(ReceitaDao receitaDao) {
    this.receitaDao = receitaDao;
  }

  @Override
  public void criarReceita(Receita receita) {
    try {
      if (receita.getNome().isEmpty()
              || receita.getDescricao().isEmpty()
              || receita.getDataRecebimento() == null
              || receita.getValorReceita() == 0) {
        System.out.println("Dados obrigatorios não informados");
      } else {
        this.receitaDao.insert(receita);
      }
    } catch (Exception e) {
      System.out.println("Não foi possivel atualizar a receita");
    }
  }

  @Override
  public void atualizarReceita(Receita receita) {
    try {
      if (receita.getNome().isEmpty()
              || receita.getDescricao().isEmpty()
              || receita.getDataRecebimento() == null
              || receita.getValorReceita() == 0) {
        System.out.println("Dados obrigatorios não informados");
      } else if (this.receitaDao.findById(receita.getId(), receita.getEmailUsuario()) == null) {
        System.out.println("investimento não encontrada");
      } else {
        this.receitaDao.update(receita);
      }
    } catch (Exception e) {
      System.out.println("Não foi possivel atualizar o investimento");
    }
  }

  @Override
  public List<Receita> listarReceita(String email) {
    try {
      return this.receitaDao.findAll(email);
    } catch (Exception e) {
      System.out.println("Não foi possivel listar as receitas");
      return null;
    }
  }

  @Override
  public Receita obterReceitaById(int id, String email) {
    try {
      Receita receita = this.receitaDao.findById(id, email);
      if (receita == null) {
        System.out.println("receita não encontrada");
        return null;
      }
      return receita;
    } catch (Exception e) {
      System.out.println("Não foi possivel listar a receita");
      return null;
    }
  }

  @Override
  public void deletarInvestimento(int id) {
    try {
      this.receitaDao.deleteById(id);
      System.out.println("Deletado com sucesso");
    } catch (Exception e) {
      System.out.println("Não foi possivel deletar a receita");
    }
  }
}
