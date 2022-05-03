package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import models.Meta;
import models.Usuario;

public class MetaDaoJDBC implements MetaDao {
	private Connection conn;
	
	public MetaDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	public Meta instanciarMeta(ResultSet rs, Usuario usuario) throws SQLException{
		Meta obj = new Meta();
		
		obj.setId(rs.getInt("id"));
		obj.setNome(rs.getString("nome"));
		obj.setDescricao(rs.getString("descricao"));
		obj.setTipo2(rs.getObject("tipo"));
		obj.setValorNecessario(rs.getDouble("valor_necessario"));
		obj.setDataInicio(rs.getDate("data_comeco"));
		obj.setDataConclusao(rs.getDate("data_conclusao"));
		obj.setUsuario(usuario);
		
		return obj;
	}
	
	public Usuario instanciarUsuario(ResultSet rs) throws SQLException{
		Usuario obj = new Usuario();
		
		obj.setEmail(rs.getString("email"));
		obj.setNome(rs.getString("nome"));
		
		return obj;
	}
	
	@Override
	public void insert(Meta obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"INSERT INTO meta "
					+ "(nome, descricao, tipo, valor_necessario, data_comeco, data_conclusao, email_usuario) "
					+ "VALUES "
					+ "(?, ?, ?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			
			st.setString(1, obj.getNome());
			st.setString(2, obj.getDescricao());
			st.setObject(3, obj.getTipo());
			st.setDouble(4, obj.getValorNecessario());
			st.setDate(5, obj.getDataInicio());
			st.setDate(6, obj.getDataConclusao());
			st.setString(7, obj.getUsuario().getEmail());
			
			int linhasAfetadas = st.executeUpdate();
			if(linhasAfetadas > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}
				DB.closeResultSet(rs);
			}
			else {
				throw new DbException("Erro inesperado! Nenhuma linha afetada!");
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
	public void update(Meta obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"UPDATE meta "
					+ "SET nome = ?, descricao = ?, tipo = ?, valor_necessario = ?, data_comeco = ?, data_conclusao = ?, email_usuario = ? "
					+ "WHERE id = ?");
			
			st.setString(1, obj.getNome());
			st.setString(2, obj.getDescricao());
			st.setObject(3, obj.getTipo());
			st.setDouble(4, obj.getValorNecessario());
			st.setDate(5, obj.getDataInicio());
			st.setDate(6, obj.getDataConclusao());
			st.setString(7, obj.getUsuario().getEmail());
			st.setInt(8, obj.getId());
			
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
			st = conn.prepareStatement("DELETE FROM meta WHERE id = ?");
			
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
	public Meta findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT meta.*, usuario.nome "
					+ "FROM meta INNER JOIN usuario "
					+"ON meta.email_usuario = usuario.email "
					+ "WHERE meta.id = ?");
			
			st.setInt(1, id);
			
			rs = st.executeQuery();
			
			if(rs.next()) {
				Usuario usuario = instanciarUsuario(rs);
				
				Meta obj = instanciarMeta(rs, usuario);
				
				return obj;
			}
			return null;
		}
		catch(SQLException e){
			throw new DbException(e.getMessage());
		}
		finally{
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}
	
	@Override
	public List<Meta> findAll(){
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT meta.*,usuario.nome "
					+ "FROM meta INNER JOIN usuario "
					+ "ON meta.email_usuario = usuario.email "
					+ "ORDER BY nome");	
			
			rs = st.executeQuery();	
			
			List<Meta> listaDeMetas = new ArrayList<>();
			
			Map<String, Usuario> map = new HashMap<>();
			
			while (rs.next()) {
				Usuario usuario = map.get(rs.getString("email_usuario"));
				if (usuario == null) {
					usuario = instanciarUsuario(rs);
					
					map.put(rs.getString("emailUsuario"), usuario);
				}
				Meta obj = instanciarMeta(rs, usuario);
				
				listaDeMetas.add(obj);
			}
			return listaDeMetas;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}
	
	@Override
	public List<Meta> findByUsuario(Usuario usuario){
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT meta.*, usuario.nome "
					+ "FROM meta INNER JOIN usuario "
					+ "ON meta.email_usuario = usuario.email "
					+ "WHERE email_usuario = ? "
					+ "ORDER BY nome");
			
			st.setString(1, usuario.getEmail());
			
			rs = st.executeQuery();
			
			List<Meta> listaDeMetas = new ArrayList<>();
			
			Map<String, Usuario> map = new HashMap<>();
			
			while(rs.next()) {
				Usuario usuario1 = map.get(rs.getString("email_usuario"));
				
				if(usuario1 == null) {
					usuario1 = instanciarUsuario(rs);
					
					map.put(rs.getString("email_usuario"), usuario1);
					
				}
				Meta obj = instanciarMeta(rs, usuario1);
				listaDeMetas.add(obj);
			}
			return listaDeMetas;
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
