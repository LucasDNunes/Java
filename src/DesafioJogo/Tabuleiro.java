package DesafioJogo;

public class Tabuleiro {

	public ElementoD primeiro, ultimo, atual;

	public Tabuleiro() {
		primeiro = ultimo = atual = null;
	}
	
	public void moveParaPosicao(int pos) {
		atual = primeiro;
		for (int i = 0; i < pos; i++) {
			atual = atual.proximo;
		}
	}

	public void inserePrimeiro(char l) {
		ElementoD novo = new ElementoD(l);
		primeiro = novo;
		ultimo = novo;
		atual = novo;
	}

	public void insereUltimo(char l) {
		ElementoD novo = new ElementoD(l);
		atual.proximo = novo;
		novo.anterior = atual;
		ultimo = novo;
		atual = primeiro;
	}

	public void insereNaPosicao(char l, int pos) {
		ElementoD novo = new ElementoD(l);
		moveParaPosicao(pos);
		atual.proximo = novo;
		novo.anterior = atual;
		atual = novo;
	}

	public char elementoNaPosicao(int pos) {
		moveParaPosicao(pos);
		return atual.letra;
	}

	public ElementoD primeiro() {
		return primeiro;
	}

	public int buscaElemento(char l) {
		int cont = 0;
		atual = primeiro;
		while (atual != null && atual.letra != l) {
			atual = atual.proximo;
			cont++;
		}
		if (atual != null)
			return cont;
		return -1;
	}
}