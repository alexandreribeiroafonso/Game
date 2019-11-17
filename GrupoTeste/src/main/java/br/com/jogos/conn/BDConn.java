/*
 * Classe de criação do banco de dados, tabelas, relacionamentos, chaves, configuração e conexao com o banco criado.
 */
package br.com.jogos.conn;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class BDConn {

    private static final String createTableUser = "create table if not exists user (id bigint auto_increment primary key)";
    private static final String createTableMonster = "create table if not exists monster(id bigint primary key,name varchar(30) not null)";

    private static final String createTableCollectedCoin = "create table if not exists collected_coin(id bigint auto_increment primary key, value bigint not null, user_id bigint not null, foreign key(user_id) references user(id))";
    private static final String createTableKilledMonster = "create table if not exists killed_monster(id bigint auto_increment primary key, user_id bigint not null, monster_id bigint not null, foreign key(user_id) references user(id), foreign key(monster_id) references monster(id))";
    private static final String createTableDeaths = "create table if not exists deaths(id bigint auto_increment primary key, user_id bigint not null, time timestamp not null, foreign key(user_id) references user(id))";
   
    private static final String createTableUserPont = "create table if not exists user_pont(user_id bigint primary key, scoins int, sdeaths int, skilled_turtles int, skilled_bowsers int, foreign key(user_id) references user(id))";

    public static Connection createConn() throws SQLException {
        try {
        	
        	Connection connection = H2JDBCUtils.getConnection();
			
			  System.out.println (connection.toString());
			  Statement statement = connection.createStatement(); 
			  statement.execute(createTableUser);
			  statement.execute(createTableMonster);
			  statement.execute(createTableCollectedCoin); 
			  statement.execute(createTableKilledMonster);
			  statement.execute(createTableDeaths);
			  statement.execute(createTableUserPont);
	          System.out.println ("Conexão estabelecida com BD. Tabelas criadas com sucesso.");
            
	          return(connection);

        } catch (Exception e) {
        	System.out.println (e);
            return (null);
        }
    }
}

