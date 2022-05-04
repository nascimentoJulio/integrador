package dao;

import java.util.List;

import models.Investimento;
import models.Usuario;

public interface InvestimentoDao {

	public void insert(Investimento obj);
	
	public void update(Investimento obj);
	
	public void deleteById(Integer id);
	
	public Investimento findById(Integer id);
	
	public List<Investimento> findAll();
	
	public List<Investimento> findByUsuario(Usuario usuario);
}
