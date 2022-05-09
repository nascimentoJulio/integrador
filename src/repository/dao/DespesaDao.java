package repository.dao;

import java.util.List;

import models.Despesa;


public interface DespesaDao {
	
	void insert(Despesa obj);
	
	void update(Despesa obj);
	
	void deleteById(Integer id);
	
	Despesa findById(Integer id);
	
	List<Despesa> findAll();
}
