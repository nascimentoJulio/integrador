package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
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
					"INSERT INTO usuario (email, senha) VALUES (?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			
			st.setString(1, obj.getEmail());
			
			st.setString(2, obj.getSenha());
			
			int rowsAffected = st.executeUpdate();
			
			if(rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				while(rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}
				DB.closeResultSet(rs);
			}
			else {
				throw new DbException("Unexpected error! No rows affected!");
			}
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
					"UPDATE usuario SET email = ?, senha = ? WHERE id = ?");
			
			st.setString(1, obj.getEmail());
			
			st.setString(2, obj.getSenha());
			
			st.setInt(3, obj.getId());
			
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
	public void deleteById(Integer id) {
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement("DELETE FROM usuario WHERE id = ?");
			
			st.setInt(1, id);
			
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
	public Usuario findById(Integer id) {
		PreparedStatement st = null;
		
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement("SELECT * FROM usuario WHERE id = ?");
			
			st.setInt(1, id);
			
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
