package br.com.jogos.regras;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class ListarUsuarios {
	public void listarUsuarios (Connection bdconn) {
		String sql = "select * from user";
		try {
			Statement statement = bdconn.createStatement();
			
			ResultSet rs = null;
			rs = statement.executeQuery(sql);
			ResultSetMetaData metaData = rs.getMetaData();
	
			while( rs.next() ){
				for( int i = 1; i <= metaData.getColumnCount(); i++ ){
					System.out.print( rs.getObject( i ) + " " );
				}
			}
			
			System.out.println();
		}catch(SQLException e) {
			System.out.println(e);
		}
	}
}
