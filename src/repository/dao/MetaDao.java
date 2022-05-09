package repository.dao;

import java.util.List;

import models.Meta;
import models.Usuario;

public interface MetaDao {
	
	public void insert(Meta obj);
	
	public void update(Meta obj);
	
	public void deleteById(Integer id);
	
	public Meta findById(Integer id, String email);
	
	public List<Meta> findAll(String email);

}
