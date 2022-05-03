package dao;

import java.sql.Connection;

public class MetaDaoJDBC implements MetaDao {
	private Connection conn;
	
	public MetaDaoJDBC(Connection conn) {
		this.conn = conn;
	}
}
