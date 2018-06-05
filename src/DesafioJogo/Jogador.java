package DesafioJogo;

public class Jogador {

	ElementoD posicao;
	Palavra propria;
	Palavra adivinhar;
	boolean jogada;
	boolean perdeJogada;

	public Jogador() {
		posicao = null;
		propria = null;
		adivinhar = null;
		jogada = false;
		perdeJogada = false;
	}
	
	public boolean isPerdeJogada() {
		return perdeJogada;
	}


	public void setPerdeJogada(boolean perdeJogada) {
		this.perdeJogada = perdeJogada;
	}
	
	public boolean isJogada() {
		return jogada;
	}

	public void setJogada(boolean jogada) {
		this.jogada = jogada;
	}

}