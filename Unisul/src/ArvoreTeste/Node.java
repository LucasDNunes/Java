package ArvoreTeste;

import java.util.ArrayList;

public class Node {
	int valor;
	Node raiz;
	ArrayList<Node> filhos;

	public Node(int valor) {
		this.valor = valor;
		raiz = null;
		filhos = new ArrayList<Node>();
	}

	public Node(Node node) {
		this.valor = node.valor;
		this.raiz = node.raiz;
		this.filhos = node.filhos;
	}

	public Node pai(Node n) {
		return n.raiz;
	}

	public boolean verificaRaiz(Node n) {
		return n.raiz.valor == raiz.valor;
	}

	public int atualizaElemento(Node elementoAntigo, Node elementoNovo) {
		int aux = elementoAntigo.valor;
		elementoAntigo.valor = elementoNovo.valor;
		return aux;
	}
}
