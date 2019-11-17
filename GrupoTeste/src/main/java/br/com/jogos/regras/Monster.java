package br.com.jogos.regras;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Monster {
	private String name;
	private int id;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void inserir (Connection bdconn) {
		if(id>2)
			System.out.println("ID do monstro deve ser '1' para Turtle e '2' para Bowser.");
		else {
			String sql = "insert into monster values(" + this.id + ",'" + this.name + "')";
			try {
				Statement statement = bdconn.createStatement();
				statement.execute(sql);
				System.out.println("Nome de monstro no jogo registrado com sucesso.");
			}catch (SQLException e) {
				System.out.println(e);
			}
		}
	}
}
