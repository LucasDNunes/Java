package ArvoreBinaria;

import javax.swing.JOptionPane;

import ArvoreBinariaBusca.NodeAB;

public class ArvoreB {

	private NodeB raiz;

	public ArvoreB() {
		raiz = null;
	}

	public void insereB(NodeB pai, NodeB filho) {
		if (raiz == null) {
			raiz = filho;
		} else {
			if (pai.filhoDir == null) {
				if (pai.filhoEsq == null)
					pai.filhoEsq = filho;
				else
					pai.filhoDir = filho;
				filho.raiz = pai;
			} else
				JOptionPane.showMessageDialog(null, "Pai já possui dois filhos");
		}
	}
}
