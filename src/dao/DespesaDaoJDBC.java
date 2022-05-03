package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import db.DB;
import db.DbException;
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
					"INSERT INTO despesa (nome, descricao, data_despesa, valor_despesa, tipo_despesa) " 
					+ "VALUES (?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			
			st.setString(1, obj.getNome());
			
			st.setString(2, obj.getDescricao());
			
			st.setDate(3, obj.getDataDespesa());
			
			st.setDouble(4, obj.getValorDespesa());
			
			st.setTipoDespesa(5, obj.getTipoDespesa());
			
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
			
			st.setTipoDespesa(5, obj.getTipoDespesa());
			
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
		
	}
	
	@Override
	public List<Despesa> findAll(){
		
	}
}