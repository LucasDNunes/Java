package DesafioJogo;
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

	public boolean removeLetra() {
		atual = primeiro;
		boolean tem = false;
		if (primeiro.letra == '*') {
			while ((atual.letra == '*') && (atual.proximo != null)) {
				if (atual.letra == '*') {
					atual = atual.proximo;
				}
				if (atual.letra != '*') {
					tem = true;
					char l = atual.letra;
					atual = primeiro;
					while (atual.proximo != null) {
						if (atual.letra == l)
							atual.letra = '*';
						atual = atual.proximo;
					}
					if (atual.letra == l) {
						tem = true;
						atual.letra = '*';
					}
				}
			}
		} else {
			tem = true;
			char l = atual.letra;
			while (atual.proximo != null) {
				if (atual.letra == l)
					atual.letra = '*';
				atual = atual.proximo;
			}
			if (atual.letra == l) {
				tem = true;
				atual.letra = '*';
			}
		}
		return tem;
	}

	public void insereLetra(Palavra palavra) {
		atual = primeiro;
		palavra.atual = palavra.primeiro;
		char l;
		do {
			if (atual.letra == '*')
				l = palavra.atual.letra;
			else {
				if (atual.proximo != null)
					atual = atual.proximo;
				if (palavra.atual.proximo != null)
					palavra.atual = palavra.atual.proximo;
			}
		} while (atual.letra != '*');
		l = palavra.atual.letra;
		atual = primeiro;
		palavra.atual = palavra.primeiro;
		while (atual.proximo != null) {
			if (l == palavra.atual.letra)
				atual.letra = palavra.atual.letra;
			atual = atual.proximo;
			palavra.atual = palavra.atual.proximo;
		}
		if (l == palavra.atual.letra)
			atual.letra = palavra.atual.letra;
	}

	public void ganhaJogada(Jogador j) {
		j.setJogada(true);
	}

	public void perdeJogada(Jogador j) {
		j.setPerdeJogada(true);
	}
}