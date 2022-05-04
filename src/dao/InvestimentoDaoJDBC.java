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
import models.Investimento;
import models.Usuario;

public class InvestimentoDaoJDBC implements InvestimentoDao {
	private Connection conn;
	
	public InvestimentoDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	public Investimento instanciarInvestimento(ResultSet rs, Usuario usuario) throws SQLException{
		Investimento obj = new Investimento();
		
		obj.setId(rs.getInt("id"));
		obj.setNome(rs.getString("nome"));
		obj.setDescricao(rs.getString("descricao"));
		obj.setTipoInvestimento2(rs.getObject("tipo_investimento"));
		obj.setValorInvestido(rs.getDouble("valor_investido"));
		obj.setTaxaRendimento(rs.getDouble("taxa_investimento"));
		obj.setDataInvestimento(rs.getDate("data_investimento"));
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
	public void insert(Investimento obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"INSERT INTO investimento "
					+ "(nome, descricao, tipo_investimento, valor_investido, taxa_rendimento, data_investimento, email_usuario) "
					+ "VALUES "
					+ "(?, ?, ?, ?, ?, ?, ?",
					Statement.RETURN_GENERATED_KEYS);
			
			st.setString(1, obj.getNome());
			
			st.setString(2, obj.getDescricao());
			
			st.setObject(3, obj.getTipoInvestimento());
			
			st.setDouble(4, obj.getValorInvestido());
			
			st.setDouble(5, obj.getTaxaRendimento());
			
			st.setDate(6, obj.getDataInvestimento());
			
			st.setString(7, obj.getUsuario().getEmail());
			
			int linhasAfetadas = st.executeUpdate();
			
			if(linhasAfetadas > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if(rs.next()) {
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
	public void update(Investimento obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"UPDATE investimento "
					+ "SET nome = ?, descricao = ?, tipo_investimento = ?, valor_investido = ?, taxa_rendimento = ?, "
					+ "data_investimento = ?, email_usuario = ? WHERE id = ?");
			
			st.setString(1, obj.getNome());
			
			st.setString(2, obj.getDescricao());
			
			st.setObject(3, obj.getTipoInvestimento());
			
			st.setDouble(4, obj.getValorInvestido());
			
			st.setDouble(5, obj.getTaxaRendimento());
			
			st.setDate(6, obj.getDataInvestimento());
			
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
			st = conn.prepareStatement("DELETE FROM investimento WHERE id = ?");
			
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
	public Investimento findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT investimento.*, usuario.nome "
					+ "FROM investimento INNER JOIN usuario "
					+"ON investimento.email_usuario = usuario.email "
					+ "WHERE investimento.id = ?");
			
			st.setInt(1, id);
			rs = st.executeQuery();
			
			if(rs.next()) {
				Usuario usuario = instanciarUsuario(rs);
				
				Investimento investimento = instanciarInvestimento(rs, usuario);
				
				return investimento;
			}
			return null;
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
	}
	
	@Override
	public List<Investimento> findAll(){
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT investimento.*,usuario.nome "
					+ "FROM investimento INNER JOIN usuario "
					+ "ON investimento.email_usuario = usuario.email "
					+ "ORDER BY nome");
			
			rs = st.executeQuery();
			
			List<Investimento> listaDeInvestimentos = new ArrayList<>();
			
			Map<String, Usuario> map = new HashMap<>();
			
			while (rs.next()) {
				Usuario usuario = map.get(rs.getString("id"));
				if (usuario == null) {
					usuario= instanciarUsuario(rs);
					map.put(rs.getString("id"), usuario);
				}
				Investimento obj = instanciarInvestimento(rs, usuario);
				listaDeInvestimentos.add(obj);
			}
			return listaDeInvestimentos;
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
	public List<Investimento> findByUsuario(Usuario usuario){
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement(
					"SELECT investimento.*, usuario.nome "
					+ "FROM investimento INNER JOIN usuario "
					+ "ON investimento.email_usuario = usuario.email "
					+ "WHERE email = ? "
					+ "ORDER BY Name");
			
			st.setString(1, usuario.getEmail());
			
			rs = st.executeQuery();
			
			List<Investimento> listaDeInvestimentos = new ArrayList<>();
			
			Map<String, Usuario> map = new HashMap<>();
			
			while(rs.next()) {
				Usuario usuario1 = map.get(rs.getString("email"));
				
				if(usuario1 == null) {
					usuario1 = instanciarUsuario(rs);
					
					map.put(rs.getString("email"), usuario1);
				}
				Investimento obj = instanciarInvestimento(rs, usuario1);
				
				listaDeInvestimentos.add(obj);
			}
			return listaDeInvestimentos;
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
