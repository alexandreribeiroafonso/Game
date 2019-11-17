package br.com.jogos.regras;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Collected_Coin {
	private int userID;
	private int value;
	
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public void inserir (Connection bdconn) {
		try {
			Statement statement = bdconn.createStatement();
			String sql = "insert into collected_coin values(null," + this.value + "," +  this.userID + ")";
			bdconn.setAutoCommit(false);
			statement.execute(sql);
			sql = "select * from user_pont where user_ID=" + this.userID;
			ResultSet rs = statement.executeQuery(sql);
			rs.last();
			int count = rs.getRow();
			if(!(count==0)){
				int num = rs.findColumn("scoins");
				Integer valorS = (Integer) rs.getObject(num);
				if (valorS==null) valorS = 0;
				sql = "update user_pont set scoins =" + (this.value + valorS.intValue()) + " where user_id=" + this.userID; 
				statement.execute(sql);	
			}else {
				sql = "insert into user_pont values(" + this.userID + "," + this.value + "," + "0,0,0)"; 
				statement.execute(sql);
			}
				
			bdconn.commit();
			System.out.println("Pontuação de coleta de moedas no jogo registrada com sucesso.");
		}catch(SQLException ex) {
			ex.printStackTrace();
	        try {
	            System.out.println("Falha na Transação.");
	            bdconn.rollback();
	        }
	        catch (SQLException se) {
	            se.printStackTrace();
	        }
		}
	}
}
