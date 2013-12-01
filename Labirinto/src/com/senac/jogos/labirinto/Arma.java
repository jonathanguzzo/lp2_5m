package com.senac.jogos.labirinto;

public class Arma extends Item {
	private int dano;
	
	public Arma(String tipo, int dano)
	{
		super(tipo);
		if (dano > 0 )
			this.dano = dano;
		else
			this.dano = 1;
	}
	
	public int getDano()
	{
		return dano;
	}
}
