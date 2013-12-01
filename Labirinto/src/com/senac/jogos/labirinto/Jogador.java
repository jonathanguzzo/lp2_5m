package com.senac.jogos.labirinto;

public class Jogador extends Personagem {
	
	private Arma arma;
	private Armadura armadura;
	private Chave chave;
	
	public Jogador()
	{
		super(1,1,20);
		this.arma = null;
		this.armadura = null;
		this.chave = null;
	}

	public Arma getArma()
	{
		return arma;
	}

	public void setArma(Arma arma) throws Exception
	{
		if (this.arma == null)
			throw new Exception("Jogador ja possui uma arma.");
		this.arma = arma;
	}

	public Armadura getArmadura()
	{
		return armadura;
	}

	public void setArmadura(Armadura armadura) throws Exception
	{
		if (this.armadura == null)
			throw new Exception("Jogador ja possui uma armadura.");
		this.armadura = armadura;
	}

	public Chave getChave()
	{
		return chave;
	}

	public void setChave(Chave chave) throws Exception
	{
		if (this.arma == null)
			throw new Exception("Jogador ja possui uma chave.");
		this.chave = chave;
	}

	@Override
	public int getAtaque()
	{
		int bonus = 0;
		if (arma != null)
			bonus += arma.getDano();
		return ataque + bonus;
	}

	@Override
	public void setDano(int value)
	{
		int total = value - resistencia;
		if (armadura != null)
			total -= armadura.getResistencia();
		vida -= total;
	}
}
