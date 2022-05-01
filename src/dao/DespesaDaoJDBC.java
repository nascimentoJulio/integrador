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
					"INSERT INTO despesa (id, nome, descricao, data_despesa, valor_despesa, tipo_despesa) " 
					+ "VALUES (?, ?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			
			st.setInt(1, obj.getId());
			
			st.setString(2, obj.getNome());
			
			st.setString(3, obj.getDescricao());
			
			st.setDate(4, obj.getDataDespesa());
			
			st.setDouble(5, obj.getValorDespesa());
			
			st.setTipoDespesa(6, obj.getTipoDespesa());
			
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
	public void update(Despesa obj) {
		
	}
	
	@Override
	public void deleteById(Integer id) {
		
	}
	
	@Override
	public Despesa findById(Integer id) {
		
	}
	
	@Override
	public List<Despesa> findAll(){
		
	}
}
