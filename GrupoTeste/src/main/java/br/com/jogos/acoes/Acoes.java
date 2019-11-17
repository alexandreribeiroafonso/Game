/*
 * Classe de Iterface, chamada das classes do pacote br.com.jogos.conn.* paraconexão e  criação do Banco de Dados
 * e do pacote import br.com.jogos.regras.* cujas classes executam operações de jogo sobre o banco de dados criado.
 * Interface do sistema, inicia rodando o módulo main abaixo para exibição do menu principal
 */

package br.com.jogos.acoes;
import java.sql.Connection;

import java.sql.SQLException;
import java.util.TimeZone;

import br.com.jogos.conn.*;
import br.com.jogos.regras.*;

import java.util.Scanner;

public class Acoes {
	static private Connection conn;
	
	public static void conexaoBD()  {
		try {
			conn = BDConn.createConn();
		}catch (SQLException e){
			System.out.println(e);
		}
	}
	
	public static void desconexaoBD(){
		try {
			conn.close();
		}catch(SQLException e) {
			System.out.println(e);
		}
	}
	
	public static void inserirUser(){
		User user = new User();
		user.inserir(conn);
	}
	
	public static void inserirCollected_Coin(int userID, int value){
		Collected_Coin cc = new Collected_Coin();
		cc.setUserID(userID);
		cc.setValue(value);
		cc.inserir(conn);
	}
	
	public static void inserirMonster(int i, String name){
		Monster monster = new Monster();
		monster.setId(i);
		monster.setName(name);
		monster.inserir(conn);
	}
	
	public static void inserirKilled_Monster(int userID, int monsterID){
		Killed_Monster km = new Killed_Monster();
		km.setUserID(userID);
		km.setMonsterID(monsterID);
		km.inserir(conn);
	}

	public static void inserirDeaths(int userID){
		Deaths deaths = new Deaths();
		deaths.setUserID(userID);
		TimeZone.getTimeZone("Brazil/East");
		deaths.setTimeD (java.time.Instant.now());
		deaths.inserir(conn);

	}
	
	public static void listarTrofeus(int i) {
		ListarTrofeus lt = new ListarTrofeus();
		lt.setId(i);
		lt.listarTrofeus(conn);
	}
	
	public static void listarUsuarios() {
		ListarUsuarios lu = new ListarUsuarios();
		lu.listarUsuarios(conn);
	}
	
	public static void listarPontuacoes(int i) {
		ListarPontuacoes lp = new ListarPontuacoes();
		lp.setId(i);
		lp.listarPontuacoes(conn);
	}
	
	public static void main (String[] args) throws SQLException {
		conexaoBD();
		Scanner ler = new Scanner(System.in);
		int i,i2;
		String s;
		do {
			System.out.println("");
			System.out.println ("Operações:");
			System.out.println ("1-Inserir Usuário");
			System.out.println ("2-Inserir Monstro");
			System.out.println ("3-Inserir Moedas Coletadas");
			System.out.println ("4-Inserir Monstro Morto");
			System.out.println ("5-Inserir Morte do Usuário");
			System.out.println ("6-Listar Troféus");
			System.out.println ("7-Listar Usuários");
			System.out.println ("8-Listar Pontuações de Usuário");
			System.out.println ("9-Sair");
			System.out.print("Entre com o número da operação desejada:");
			i = ler.nextInt();
			
			switch (i){
				case 1:
					inserirUser();
					break;
				case 2:
					System.out.print("Entre com o Id do monstro:");
					i = ler.nextInt();
					System.out.print("Entre com o nome do monstro:");
					ler.nextLine();
					s = ler.nextLine();
					inserirMonster(i,s);
					break;
				case 3:
					System.out.print("Entre com o Id do usuário:");
					i = ler.nextInt();
					System.out.print("Entre com a quantidade de moedas:");
					i2 = ler.nextInt();
					inserirCollected_Coin(i, i2);
					break;
				case 4:
					System.out.print("Entre com o Id do usuário:");
					i = ler.nextInt();
					System.out.print("Entre com o Id do monstro morto:");
					i2 = ler.nextInt();
					inserirKilled_Monster(i,i2);
					break;
				case 5:
					System.out.print("Entre com o Id do usuário:");
					i = ler.nextInt();
					inserirDeaths(i);
					break;
				case 6:
					System.out.print("Entre com o Id do usuário:");
					i = ler.nextInt();
					listarTrofeus(i);
					break;

				case 7:
					System.out.print("Lista de usuários:");
					listarUsuarios();
					break;
				case 8:
					System.out.print("Id do usuário:");
					i = ler.nextInt();
					listarPontuacoes(i);
					break;				
				case 9:
					System.out.println("Finalizado.");
					break;	
			}
				
		}while(i!=9);
		
		System.out.println("");
	}
}
