package br.com.jogos.regras;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ListarPontuacoes{
	private int id;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void listarPontuacoes (Connection bdconn) {
		String sql = "select * from user_pont where user_id=" + this.id;
		try {
			Statement statement = bdconn.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			rs.first();
			int count = rs.getRow();
			int num;
			if(!(count==0)){
				System.out.println ("Id. " + this.id);
				num = rs.findColumn("scoins");
				Integer valorS = (Integer) rs.getObject(num);
				
				if (valorS==null) valorS=0;
					System.out.println ("Moedas. " + valorS);
						
				num = rs.findColumn("skilled_turtles");
				valorS = (Integer) rs.getObject(num);
				
				if (valorS==null) valorS=0;
					System.out.println ("Tartarugas Mortas. " + valorS);
				
						
				num = rs.findColumn("skilled_bowsers");
				valorS = (Integer) rs.getObject(num);
				
				if (valorS==null) valorS=0;
					System.out.println ("Bowsers Mortos. " + valorS);
									
				num = rs.findColumn("sdeaths");
				valorS = (Integer) rs.getObject(num);
				
				if (valorS==null) valorS=0;
					System.out.println ("Mortes do Usuário. " + valorS);	
			}
		}catch(SQLException e) {
			System.out.println(e);
		}
	}
}