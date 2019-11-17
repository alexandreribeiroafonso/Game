package br.com.jogos.regras;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Killed_Monster {
	private int userID;
	private int monsterID;
	
	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getMonsterID() {
		return monsterID;
	}

	public void setMonsterID(int monsterID) {
		this.monsterID = monsterID;
	}

	public void inserir (Connection bdconn){
		if (this.monsterID>2) {
			System.out.println ("ID do monstro deve ser '1' para Turtle e '2' para Bowser.");
		}else {
			try {
				Statement statement = bdconn.createStatement();
				String sql = "insert into killed_monster values(null," + this.userID + "," +  this.monsterID + ")";
				bdconn.setAutoCommit(false);
				statement.execute(sql);
				sql = "select * from user_pont where user_ID=" + this.userID;
				ResultSet rs = statement.executeQuery(sql);
				rs.last();
				int count = rs.getRow();
				if(!(count==0)){
					if (this.monsterID==1) {
						int num = rs.findColumn("skilled_turtles");
						Integer valorS = (Integer) rs.getObject(num);
						if (valorS==null) valorS = 0;
						sql = "update user_pont set  skilled_turtles =" + (1 + valorS.intValue()) + " where user_id=" + this.userID; 
						statement.execute(sql);
					}else {
						int num = rs.findColumn("skilled_bowsers");
						Integer valorS = (Integer) rs.getObject(num);
						if (valorS==null) valorS = 0;
						sql = "update user_pont set  skilled_bowsers =" + (1 + valorS.intValue()) + " where user_id=" + this.userID; 
						statement.execute(sql);					
					}
				}else{
					if (this.monsterID==1) {
						 sql="insert into user_pont values(" + this.userID + "," + "0" + ",0,1,0)"; 
						 statement.execute(sql);	
					}else {
						 sql="insert into user_pont values(" + this.userID + "," + "0" + ",0,0,1)"; 
						 statement.execute(sql);	
					}

				}
				bdconn.commit(); 
				System.out.println("Morte de monstro no jogo registrada com sucesso.");
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
}
