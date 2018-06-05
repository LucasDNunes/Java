package Fila;

public class Fila {

	private Elemento inicio, fim;
	int tamanho;

	public Fila() {
		inicio = fim = null;
	}

	public boolean vazia() {
		return inicio == null;
	}

	public void insere(char v) {
		Elemento novo = new Elemento(v);
		if (vazia()) {
			inicio = novo;
			fim = novo;
			tamanho++;
		} else {
			fim.proximo = novo;
			fim = novo;
			tamanho++;
		}
	}

	public int remove() {
		int v = inicio.valor;
		inicio = inicio.proximo;
		tamanho--;
		return v;
	}

	public int frente() {
		return inicio.valor;
	}

	public int tamanho() {
		return tamanho;
	}

}
