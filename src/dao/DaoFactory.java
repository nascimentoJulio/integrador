package dao;

import db.DB;

public class DaoFactory {
	
	public static UsuarioDao createUsuarioDao() {
		return new UsuarioDaoJDBC(DB.getConnection());
	}
	
	public static DespesaDao createDespesaDao() {
		return new DespesaDaoJDBC(DB.getConnection());
	}
	
	public static ReceitaDao createReceitaDao() {
		return new ReceitaDaoJDBC(DB.getConnection());
	}
	
	public static InvestimentoDao createInvestimentoDao() {
		return new InvestimentoDaoJDBC(DB.getConnection());
	}
	
	public static MetaDao createMetaDao() {
		return new MetaDaoJDBC(DB.getConnection());
	}
	
}
