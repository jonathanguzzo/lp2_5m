package com.senac.jogos.labirinto;

import static java.lang.System.*;

import java.io.FileInputStream;
import java.util.Scanner;

public class Labirinto {
	
	private static final Scanner teclado = new Scanner(System.in);
	
	private static Sala[] salas;
	private static int countSalas;
	private static int salaAtual;
	
	//-------------------------------------------------
	private static Range range;
	private static Jogador player;
	private Conexao[] conexao = new Conexao[6];
	private static Sala proximaSala;
	private Direcao proximaDirecao;
		
	//--------------------------------------------------
	
	public void setSala(String sala){
		salaAtual= Integer.parseInt(sala);
	}
	
	public Sala getSala(){
		return salas[salaAtual];
	}
	
	
	public static void Mover(String acao) throws Exception{
		
		proximaSala= salas[salaAtual];
		
		switch(acao){
			case "NORTH":
			case "SOUTH":
			case "EAST":
			case "WEST":
			case "UP":
			case "DOWN":
				salaAtual= proximaSala.getConexao(acao);
				break;
			default: System.out.println("Direção inválida !! ");
				break;
		}			
	}
	
	//----------------------------------------------------------------
	
	
	
	private void run()
	{
		inicializaLabirinto();
		
		for (Sala s: salas) {
			if (s == null) break;
			out.println(s);
		}
		
		/*
		while (! isGameOver()) {
		 
			exibeStatus();
			executaComando ( teclado.next() );
		}
		*/
	}
	
	private void inicializaLabirinto()
	{
		salas = new Sala[50];
		salas[0] = new Sala();
		countSalas = 1;
		try {
			leLabirinto( new Scanner( new FileInputStream("labirinto.txt") ) );
		} catch (Exception e) {
			err.println(e.getMessage());
			exit(1);
		}
	}

	private void leLabirinto( Scanner arquivo ) throws Exception
	{
		String cmd = arquivo.next().toLowerCase();
		while (cmd.equals("room")) {
			int salaId = arquivo.nextInt();
			salas[salaId] = new Sala();
			Sala sala = salas[salaId];
			
			String direcao = arquivo.next();

			do {
				if (arquivo.hasNextInt()) {
					salaId = arquivo.nextInt();
				} else if (arquivo.next().equalsIgnoreCase("EXIT")) {
					salaId = 0;
				} else break;
			
				sala.addConexao(direcao, salaId);
			
				if (!arquivo.hasNext())
					return;
				cmd = arquivo.next().toLowerCase();	
				if (cmd.equals("trap")) {
					sala.setArmadilha(direcao);
					if (!arquivo.hasNext())
						return;
					cmd = arquivo.next();
				}
				direcao = cmd;
			} while (!cmd.equals("room"));
		}
		throw new Exception("Arquivo de descricao do labirinto invalido.");
	}
	
	

	public static void main(String[] args) throws Exception
	{
				
		(new Labirinto()).run();
		
		String acao,direcao;
		
		player= new Jogador();
		range= new Range(0, countSalas);
		salaAtual= range.getRandom();
		
				
		do{
			
			if(salaAtual==0){
				salaAtual++;
			}
			
			System.out.println("Você está na sala : "+salaAtual);
			System.out.println("Digite MOVER para mover de sala :");
			acao= teclado.next();
			System.out.println("Digite: NORTH,SOUTH,EAST,WEST,UP,DOWN para seguir a Direção desejada :");
			direcao= teclado.next();
						
			switch(acao){
			case "MOVER":
				Mover(direcao);
				break;
			}	
			
		}while(salaAtual!=0);
		
		
	}//final do main

}//final da classse Labirinto
