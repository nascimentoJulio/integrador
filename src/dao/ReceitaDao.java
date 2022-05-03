package dao;

import java.util.List;

import models.Receita;
import models.Usuario;

public interface ReceitaDao {
	
	public void insert(Receita obj);
	
	public void update(Receita obj);
	
	public void deleteById(Integer id);
	
	public Receita findById(Integer id);
	
	public List<Receita> findAll();
	
	public List<Receita> findByUsuario(Usuario usuario);
}
