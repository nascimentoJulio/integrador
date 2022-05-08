package repository.dao;

import java.util.List;

import models.Usuario;

public interface UsuarioDao {
	
	public void insert(Usuario obj);
	
	public void update(Usuario obj);
	
	public void deleteByEmail(String email);
	
	public Usuario findByEmail(String email);
	
	public List<Usuario> findAll();
}