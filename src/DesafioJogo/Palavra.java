package DesafioJogo;

import javax.swing.JOptionPane;

public class Palavra {

	private ElementoD primeiro, ultimo, atual;
	int tamanho;

	public Palavra() {
		primeiro = ultimo = atual = null;
	}

	@Override
	public String toString() {

		int n = comprimento();
		String secreta = "";
		for (int i = 0; i < n; i++) {
			moveParaPosicao(i);
			secreta += atual.letra;
		}
		return secreta;
	}

	public boolean comparacao(String t) {
		int n = comprimento();
		boolean correto = false;
		String secreta = "";
		for (int i = 0; i < n; i++) {
			moveParaPosicao(i);
			secreta += atual.letra;
		}
		if (secreta.equals(t))
			correto = true;
		System.out.println(correto);
		return correto;
	}

	public int comprimento() {
		return tamanho;
	}

	public boolean estaVazio() {
		return primeiro == null;
	}

	public void moveParaPosicao(int pos) {
		atual = primeiro;
		for (int i = 0; i < pos; i++) {
			atual = atual.proximo;

		}
	}

	public void insere(char l) {

		ElementoD novo = new ElementoD(l);
		if (estaVazio()) {
			primeiro = novo;
			atual = novo;
			tamanho++;
		} else {
			atual.proximo = novo;
			atual = novo;
			tamanho++;
		}
	}

	public boolean verificar(ElementoD posicao, Palavra p2) {
		atual = primeiro;
		p2.atual = p2.primeiro;
		boolean contem = false;
		while (atual.proximo != null) {
			if (posicao.letra == p2.atual.letra) {
				atual.letra = p2.atual.letra;
				contem = true;
			}
			atual = atual.proximo;
			p2.atual = p2.atual.proximo;
		}
		if (posicao.letra == p2.atual.letra) {
			atual.letra = p2.atual.letra;
			contem = true;
		}
		return contem;
	}

	public void posicao(char l) {
		ElementoD novo = new ElementoD(l);
		ultimo.proximo = novo;
		ultimo = novo;

	}

	public int elementoNaPosicao(int pos) {
		moveParaPosicao(pos);
		return atual.letra;
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

	public void removeLetra() {
		atual = primeiro;
		do {
			if (atual.letra == '*')
				atual = atual.proximo;
			else {
				char l = atual.letra;
				atual = primeiro;
				while (atual.proximo != null) {
					if (atual.letra == l)
						atual.letra = '*';
					atual = atual.proximo;
				}
				if (atual.letra == l) {
					atual.letra = '*';
				}
				JOptionPane.showMessageDialog(null,
						"Foi removida a letra - " + l + "\n  sua palavra : " + this.toString());
				return;
			}
		} while (atual.proximo != null);

	}

	public void insereLetra(Palavra r) {
		atual = primeiro;
		do {
			if (atual.letra == '*') {
				atual.letra = r.atual.letra;
				JOptionPane.showMessageDialog(null,
						"Foi adicionado a letra - " + r.atual.letra + "\n sua palavra: " + this.toString());
				return;
			}
		} while (atual.letra != '*');

		char l = atual.letra;
		atual = primeiro;
		while (atual.proximo != null) {
			if (l == r.atual.letra)
				atual.letra = r.atual.letra;
			r.atual = r.atual.proximo;
		}
	}

	public void ganhaJogada(Jogador j) {
		j.setJogada(true);
	}
}