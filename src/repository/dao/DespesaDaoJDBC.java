package repository.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import repository.db.DB;
import repository.db.DbException;
import models.Despesa;
import utils.enuns.TipoDespesa;

public class DespesaDaoJDBC implements DespesaDao {
	private Connection conn;
	
	public DespesaDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(Despesa obj) {
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement(
					"INSERT INTO despesa (nome, descricao, data_despesa, valor_despesa, tipo_despesa, email_usuario) "
					+ "VALUES (?, ?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			
			st.setString(1, obj.getNome());
			
			st.setString(2, obj.getDescricao());
			
			st.setDate(3, obj.getDataDespesa());
			
			st.setDouble(4, obj.getValorDespesa());
			
			st.setObject(5, obj.getTipoDespesa().toString());

			st.setString(6,obj.getEmailUsuario());
			
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
				throw new DbException("Erro inesperado! Nenhuma rows affected!");
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
	public void update(Despesa obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"UPDATE despesa "
					+ "SET nome = ?, descricao = ?, data_despesa = ?, valor_despesa = ?, tipo_despesa = ? "
					+ "WHERE id = ?");
			
			st.setString(1, obj.getNome());
			
			st.setString(2, obj.getDescricao());
			
			st.setDate(3, obj.getDataDespesa());
			
			st.setDouble(4, obj.getValorDespesa());
			
			st.setObject(5, obj.getTipoDespesa());

			st.setInt(6, obj.getId());
			
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
				st = conn.prepareStatement("DELETE FROM despesa WHERE id = ?");
				
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
	public Despesa findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM despesa WHERE id = ?");
			
			st.setInt(1, id);
			
			rs = st.executeQuery();
			
			if(rs.next()) {
				Despesa obj = new Despesa();
				
				obj.setId(rs.getInt("id"));
				
				obj.setNome(rs.getString("nome"));
				
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
	public List<Despesa> findAll(){
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM despesa ORDER BY nome ");
			
			rs = st.executeQuery();
			
			List<Despesa> listaDeDespesas = new ArrayList<>();
			
			while(rs.next()) {
				Despesa obj = new Despesa();
				
				obj.setId(rs.getInt("id"));
				
				obj.setNome(rs.getString("nome"));

				obj.setDescricao(rs.getString("descricao"));

				obj.setDataDespesa(rs.getDate("data_despesa"));

				obj.setValorDespesa(rs.getDouble("valor_despesa"));

				obj.setTipoDespesa(TipoDespesa.valueOf(rs.getString("tipo_despesa")));
				listaDeDespesas.add(obj);
			}
			return listaDeDespesas;
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
