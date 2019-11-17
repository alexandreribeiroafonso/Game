package br.com.jogos.regras;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ListarTrofeus {
	private int id;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void listarTrofeus (Connection bdconn) {
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
				//System.out.println ("Moedas. " + valorS);
				if (valorS>=1)
					System.out.println ("Troféu:1 moeda");
				if (valorS>=100)
					System.out.println ("Troféu:100 moedas");
				if (valorS>=1000)
					System.out.println ("Troféu:1000 moedas");
				if (valorS>=10000)
					System.out.println ("Troféu:10000 moedas");
				if (valorS>=100000)
					System.out.println ("Troféu:100000 moedas");
				
				num = rs.findColumn("skilled_turtles");
				valorS = (Integer) rs.getObject(num);
				
				if (valorS==null) valorS=0;
				//System.out.println ("Tartarugas Mortas. " + valorS);
				
				if (valorS>=1)
					System.out.println ("Troféu:1 tartaruga");
				if (valorS>=100)
					System.out.println ("Troféu:100 tartarugas");
				if (valorS>=1000)
					System.out.println ("Troféu:1000 tartarugas");
				if (valorS>=10000)
					System.out.println ("Troféu:10000 tartarugas");
				if (valorS>=100000)
					System.out.println ("Troféu:100000 tartarugas");
				
				num = rs.findColumn("skilled_bowsers");
				valorS = (Integer) rs.getObject(num);
				
				if (valorS==null) valorS=0;
				//System.out.println ("Bowsers Mortos. " + valorS);
				
				if (valorS>=1)
					System.out.println ("Troféu:1 bowser");
				if (valorS>=100)
					System.out.println ("Troféu:100 bowsers");
				if (valorS>=1000)
					System.out.println ("Troféu:1000 bowsers");
				if (valorS>=10000)
					System.out.println ("Troféu:10000 bowsers");
				if (valorS>=100000)
					System.out.println ("Troféu:100000 bowsers");
				
							
				num = rs.findColumn("sdeaths");
				valorS = (Integer) rs.getObject(num);
				
				if (valorS==null) valorS=0;
				//System.out.println ("Mortes do Usuário. " + valorS);
				
				if (valorS>=1)
					System.out.println ("Registro:1 morte");
				if (valorS>=10)
					System.out.println ("Registro:10 mortes");
				if (valorS>=25)
					System.out.println ("Registro:25 mortes");
				if (valorS>=50)
					System.out.println ("Registro:50 mortes");
				if (valorS>=100)
					System.out.println ("Registro:100 mortes");
			}
		}catch(SQLException e) {
				System.out.println(e);
		}
	}
}