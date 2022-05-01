package dao;

import db.DB;

public class DaoFactory {
	
	public static UsuarioDao createUsuarioDao() {
		return new UsuarioDaoJDBC(DB.getConnection());
	}
	
	public static DespesaDao createDespesaDao() {
		return new DespesaDaoJDBC(DB.getConnection());
	}
}
