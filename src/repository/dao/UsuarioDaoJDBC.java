package repository.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import repository.db.DB;
import repository.db.DbException;
import models.Usuario;

public class UsuarioDaoJDBC implements UsuarioDao {
	private Connection conn;
	
	public UsuarioDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(Usuario obj) {
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement(
					"INSERT INTO usuario (email, senha) VALUES (?, ?)");
			
			st.setString(1, obj.getEmail());
			
			st.setString(2, obj.getSenha());
			
			st.executeUpdate();
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}
	
	@Override
	public void update(Usuario obj) {
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement(
					"UPDATE usuario SET senha = ? WHERE email = ?");
			
			st.setString(1, obj.getSenha());
			
			st.setString(2, obj.getEmail());
			
			st.executeUpdate();
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}
	
	@Override
	public void deleteByEmail(String email) {
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement("DELETE FROM usuario WHERE email = ?");
			
			st.setString(1, email);
			
			st.executeUpdate();
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}
	
	@Override
	public Usuario findByEmail(String email) {
		PreparedStatement st = null;
		
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement("SELECT * FROM usuario WHERE email = ?");
			
			st.setString(1, email);
			
			rs = st.executeQuery();
			
			if(rs.next()) {
				Usuario obj = new Usuario();
				
				obj.setEmail(rs.getString("email"));
				
				obj.setSenha(rs.getString("senha"));
				
				return obj;
			}
			return null;
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}
	
	@Override
	public List<Usuario> findAll(){
		PreparedStatement st = null;
		
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement("SELECT * FROM usuario ORDER BY nome");
			
			rs = st.executeQuery();
			
			List<Usuario> listaDeUsuarios = new ArrayList<>();
			
			while(rs.next()) {
				Usuario obj = new Usuario();
				
				obj.setEmail(rs.getString("email"));
				
				obj.setSenha(rs.getString("senha"));
				
				listaDeUsuarios.add(obj);
			}
			return listaDeUsuarios;
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}
	
}
