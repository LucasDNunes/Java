package DesafioJogo;

public class Jogador {

	ElementoD posicao;
	Palavra propria;
	Palavra adivinhar;
	boolean ganhador;
	boolean jogada;

	public Jogador() {
		posicao = null;
		propria = null;
		adivinhar = null;
		ganhador = false;
		jogada = false;
	}

	
	public boolean isJogada() {
		return jogada;
	}

	public void setJogada(boolean jogada) {
		this.jogada = jogada;
	}

}