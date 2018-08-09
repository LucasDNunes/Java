package ArvoreTeste;

import java.util.ArrayList;

public class Arvore {
	private Node raiz;

	public Arvore() {
		raiz = null;
	}

	public Node raiz() {

		return this.raiz;
	}

	public boolean verificaInterno(Node n) {
		return n.filhos.size() > 0;

	}

	public boolean verificaExterno(Node n) {
		return !verificaInterno(n);
	}

	public ArrayList<Node> filhos(Node n) {
		return n.filhos;

	}

	public void insere(Node pai, Node filho) {
		if (raiz == null) {
			raiz = filho;
		} else {
			filho.raiz = pai;
			pai.filhos.add(filho);
		}
	}

	public ArrayList<Node> elementos() {
		ArrayList<Node> lista = new ArrayList<Node>();
		lista.add(raiz);
		int i = 0;
		while (i < lista.size()) {
			Node atual = lista.get(i);
			for (Node n : atual.filhos) {
				lista.add(n);
			}
			i++;
		}
		return lista;
	}

	public int tamanho() {
		return elementos().size();
	}
}