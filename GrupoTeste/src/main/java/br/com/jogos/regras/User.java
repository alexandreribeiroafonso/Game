/* A classe User e as demais classes desse pacote realizam as operações sobre o BD (incluir/consultar dados).
 * Aqui são são estabelecidos os métodos, propriedades e instruções SQL para operar sobre o Banco de Dados.
 */
package br.com.jogos.regras;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class User {
	public void inserir (Connection bdconn){
		String sql = "insert into user values(null)";
		try {
			Statement statement = bdconn.createStatement();
			statement.execute(sql);
			System.out.println("Usuário inserido com sucesso. Escolha '7'- Listar Usuários, para lista dos usuários registrados.");
		}catch(SQLException e) {
			System.out.println(e);
		}
	}
}
