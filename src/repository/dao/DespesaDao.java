package repository.dao;

import java.util.List;

import models.Despesa;


public interface DespesaDao {
	
	public void insert(Despesa obj);
	
	public void update(Despesa obj);
	
	public void deleteById(Integer id);
	
	public Despesa findById(Integer id);
	
	public List<Despesa> findAll();
}
