/*Configuração de criação do BD e conexão, estabelecimento do ID, senha e driver de acesso ao BD */
package br.com.jogos.conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class H2JDBCUtils { 

    private static String jdbcURL = "jdbc:h2:~/jogador;DB_CLOSE_ON_EXIT=FALSE;AUTO_SERVER=TRUE";
	//private static String jdbcURL = "jdbc:h2:tcp://localhost/jogando";
    private static String jdbcUsername = "admin";
    private static String jdbcPassword = "admin";

    public static Connection getConnection() throws ClassNotFoundException {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }

    public static void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}