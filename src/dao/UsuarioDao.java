package dao;

import java.util.List;

import models.Usuario;

public interface UsuarioDao {
	
	public void insert(Usuario obj);
	
	public void update(Usuario obj);
	
	public void deleteById(Integer id);
	
	public Usuario findById(Integer id);
	
	public List<Usuario> findAll();
}