package ArvoreTeste;

import javax.swing.*;
import java.util.ArrayList;

public class UsaArvore2 {

	public static void main(String args[]) {
		int v1, op = 1;
		Arvore a = new Arvore();
		ArrayList<Node> listaFilhos = new ArrayList<Node>();
		while (op != 0) {
			op = Integer.parseInt(JOptionPane.showInputDialog(
					"1- Inserir nos\n2- Listar nos internos\n3- Listar nos externos\n4- Listar Filhos Raiz"
							+ "\n5- Soma dos Nos\n6- Lista de Ancestrais\n0- Sair"));

			switch (op) {
			case 1:
				Node noPai = new Node(-1);
				Node nofilho = new Node(Integer.parseInt(JOptionPane.showInputDialog("Valor do no filho: ")));
				if (a.raiz() == null)
					noPai = nofilho;
				a.insere(noPai, nofilho);
				Node nofilho1 = new Node(Integer.parseInt(JOptionPane.showInputDialog("Valor do no filho: ")));
				a.insere(noPai, nofilho1);
				Node nofilho2 = new Node(Integer.parseInt(JOptionPane.showInputDialog("Valor do no filho: ")));
				a.insere(noPai, nofilho2);
				Node nofilho3 = new Node(Integer.parseInt(JOptionPane.showInputDialog("Valor do no filho: ")));
				a.insere(noPai, nofilho3);
				break;
			case 2:
				listaFilhos = a.elementos();
				for (int i = 0; i < listaFilhos.size(); i++) {
					Node nofilho4 = new Node(listaFilhos.get(i));
					if (a.verificaInterno(listaFilhos.get(i)))
						JOptionPane.showMessageDialog(null, "Folha " + (i + 1) + ": " + listaFilhos.get(i).valor);
				}
				break;
			case 3:
				listaFilhos = a.elementos();
				for (int i = 0; i < listaFilhos.size(); i++) {
					Node nofilho5 = new Node(listaFilhos.get(i));
					if (a.verificaExterno(listaFilhos.get(i)))
						JOptionPane.showMessageDialog(null, "Folha " + (i + 1) + ": " + listaFilhos.get(i).valor);
				}
				break;
			case 4:
				listaFilhos = a.filhos(a.raiz());
				JOptionPane.showMessageDialog(null, "nro filhos..." + listaFilhos.size());
				for (int i = 0; i < listaFilhos.size(); i++) {
					Node nofilho6 = new Node(listaFilhos.get(i));
					JOptionPane.showMessageDialog(null, "Filho " + (i + 1) + ": " + listaFilhos.get(i).valor);
				}
				break;

			case 5:
				int soma = 0;
				listaFilhos = a.elementos();
				for (int i = 0; i < listaFilhos.size(); i++)
					soma += listaFilhos.get(i).valor;
				JOptionPane.showMessageDialog(null, "Soma= " + soma);
				break;
			case 6:
				int busca = Integer.parseInt(JOptionPane.showInputDialog("Listar os ancescrais do no:"));
				listaFilhos = a.elementos();
				for (int i = 0; i < listaFilhos.size(); i++)
					if (listaFilhos.get(i).valor == busca) {
						nofilho = listaFilhos.get(i);
						while (!nofilho.equals(a.raiz())) {
							nofilho = listaFilhos.get(i).raiz;
							JOptionPane.showMessageDialog(null, "Pai= " + nofilho.valor);
						}
					}
			}
		}
	}
}
