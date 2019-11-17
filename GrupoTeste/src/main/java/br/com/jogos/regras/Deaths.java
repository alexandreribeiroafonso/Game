package br.com.jogos.regras;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;


public class Deaths {
	private int userID;
	private Instant timeD;
	
	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public Instant getTimeD() {
		return timeD;
	}

	public void setTimeD(Instant instant) {
		this.timeD = instant;
	}


	public void inserir (Connection bdconn) {
		try {
			Statement statement = bdconn.createStatement();
			String sql = "insert into deaths (id, user_id, time) values (null," + this.userID + ",'" + this.timeD + "')";
			bdconn.setAutoCommit(false);
			statement.execute(sql);
			sql = "select * from user_pont where user_ID=" + this.userID;
			ResultSet rs = statement.executeQuery(sql);
			rs.last();
			int count = rs.getRow();
			if(!(count==0)){
				int num = rs.findColumn("sdeaths");
				Integer valorS = (Integer) rs.getObject(num);
				if (valorS==null) valorS = 0;
				sql = "update user_pont set sdeaths =" + (1 + valorS.intValue()) + " where user_id=" + this.userID; 
				statement.execute(sql);	
			}else {
				sql = "insert into user_pont values(" + this.userID + ",1,0,0)"; 
				statement.execute(sql);
			}
			bdconn.commit(); 
			System.out.println("Morte de usuário no jogo registrada com sucesso.");
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