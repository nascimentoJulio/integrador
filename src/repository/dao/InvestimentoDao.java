package repository.dao;

import java.util.List;

import models.Investimento;

public interface InvestimentoDao {

  void insert(Investimento obj);

  void update(Investimento obj);

  void deleteById(Integer id);

  Investimento findById(Integer id, String email);

  List<Investimento> findAll(String email);

}
