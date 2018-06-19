package ArvoreBinariaBusca;

import javax.swing.*;

public class AB {

	protected NodeAB raiz; // no raiz
	protected NodeAB pt; // no de percurso
	protected NodeAB aux;// no auxiliar de percurso (guarda o ultimo no
							// acessado)
	protected int pos; // posicao do no na sub-arvore, 1 = esquerda, 2 = direita
	protected String listaArvore; // atributo para listar a arvore

	public AB() {
		this.raiz = null;
		this.pt = null;
		this.aux = null;
		this.listaArvore = " ";

	}

	public AB(int n) {
		this.raiz = new NodeAB(n);
		this.pt = raiz;
		this.listaArvore = " ";
	}

	// Metodo que retorna a raiz da arvore
	public NodeAB raiz() {
		return this.raiz;
	}

	// Metodo que retorna o pai do no x da arvore
	public NodeAB pai(NodeAB x) {
		if (x == raiz)
			return null;
		else
			return x.pai;
	}

	// Metodo que testa se a arvore é vazia
	public boolean eVazia() {
		if (raiz == null)
			return true;
		else
			return false;
	}

	/*
	 * Metodo que exibe a arvore em pre ordem na representacao de parenteses
	 * aninhados
	 */
	public String exibirArvorePreOrdem() {
		if (raiz == null)
			return "";
		NodeAB p = this.raiz; // recebe o no raiz
		listaArvore = listaArvore + Integer.toString(p.info) + " ";
		if (p.esq != null) {
			listaArvore = listaArvore + "(" + exibirArvorePreOrdem(p.esq) + ")";
		}
		if (p.dir != null) {
			listaArvore = listaArvore + "(" + exibirArvorePreOrdem(p.dir) + ")";
		}
		String pr = listaArvore;
		listaArvore = " ";
		return pr;
	}

	/*
	 * Metodo que exibe a arvore em pre ordem (metodo privado para auxiliar para
	 * recursao) na representacao de parenteses aninhados
	 */
	private String exibirArvorePreOrdem(NodeAB p) {
		listaArvore = Integer.toString(p.info) + " ";
		if (p.esq != null) {
			listaArvore = listaArvore + "( " + exibirArvorePreOrdem(p.esq) + ")";
		}
		if (p.dir != null) {
			listaArvore = listaArvore + "(" + exibirArvorePreOrdem(p.dir) + ")";
		}
		String pr = listaArvore;
		listaArvore = " ";
		return pr;
	}

	// Metodo de busca de um elemento com chave x na arvore
	public NodeAB buscarElemento(int x) {
		// testa se a arvore e' vazia
		if (raiz == null) {
			pos = 0;
			return raiz;
		}
		// testa se o no e vazio
		if (pt == null)
			return null;
		// achou o no
		else if (pt.info == x)
			return pt;
		// no a esquerda
		else if (x < pt.info) {
			aux = pt;
			pt = pt.esq;
			aux.esq = pt;
			pos = 1; // posicao indicao a insersao na esquerda
			return buscarElemento(x);
		}
		// no a direita
		else {
			aux = pt;
			pt = pt.dir;
			aux.dir = pt;
			pos = 2;// posicao indicao a insercao na direita
			return buscarElemento(x);
		}
	}

	// Metodo de insercao de um elemento com chave x na arvore
	public boolean inserirElemento(int x) {
		NodeAB p = null;
		this.pt = this.raiz;
		p = buscarElemento(x);

		if (p != null) {
			return false;
		} // a chave ja existe na arvore

		else {
			p = new NodeAB(x);
			if (pos == 0) {// inserir na raiz
				this.raiz = p;
			}
			// insercao do elemento sem ser raiz

			else if (pos == 1) {// filho a esquerda do pai
				p.pai = aux;
				aux.esq = p;
			} else if (pos == 2) {// filho a direita do pai
				p.pai = aux;
				aux.dir = p;
			}
		}
		pos = 4;// passa a nao indicar nada
		pt = raiz; // ponteiro de pesquisa volta a ser a raiz
		// System.out.print(exibirArvorePreOrdem());
		return true;
	}

	public void removeFolha(int x) {
		NodeAB p = null;
		p = buscarElemento(x);
		if (p.pai.dir != null)
			p.pai.dir = null;
		else
			p.pai.esq = null;
	}

	// Metodo de remocao de um elemento com chave x na arvore
	public boolean removerElemento(int x) {
		NodeAB p = null;
		pt = raiz;
		p = buscarElemento(x);
		int local = 0; // localiza se o pai do no removido vai ter um novo
						// filho a direita ou a esquerda

		if (p == null) {
			return false;
		} // nao achou o elemento
		else {// achou o elemento
			if (p.esq == null && p.dir == null) {// nao tem filhos
				if (p == raiz) {
					raiz = null;
				} // o no e a raiz
				else {
					if (pos == 1)// este no e' o filho da esquerda do seu pai
						p.pai.esq = null;
					else if (pos == 2)// este no e' o filho da direita do seu
										// pai
						p.pai.dir = null;
				}
				p = null;
				System.out.println(exibirArvorePreOrdem()); // exibe a arvore
				return true;
			} else {// tem filhos
				aux = p;
				if (p.esq == null) { // pode ter apenas o filho da direita
					p = p.dir;
					local = 1; // pai de p tera novo filho a direita
				} else {
					if (p.dir == null) {// tem apenas o filho da esquerda
						p = p.esq;
						local = 2; // pai de p tera novo filho a esquerda
					} else {// tem dois filhos

						p = minDir(aux.dir); // achar o menor a direita
						p.esq = aux.esq; // reordenacao de ponteiros
						if (aux == raiz) {
							raiz = p;
						} // se o no a ser removido era a raiz, p e' a nova raiz
						else {
							p.pai = aux.pai;
							if (pos == 1)// este no e' o filho da esquerda do
											// seu pai
								aux.pai.esq = p;
							else if (pos == 2)// este no e' o filho da direita
												// do seu pai
								aux.pai.dir = p;
						}
						aux = null;
						System.out.println(exibirArvorePreOrdem()); // exibe a
																	// arvore
						return true;
					}
				}
			}
			if (aux == raiz) {
				raiz = p;
			} // se o no a ser removido era a raiz, p e' a nova raiz
			else {// nao e a raiz(tem pai)
				if (local == 1)
					aux.pai.dir = p; // pai tem filho a direita
				else if (local == 2)
					aux.pai.esq = p; // pai tem filho a esquerda
				p.pai = aux.pai;
			}
			aux = null;
			System.out.println(exibirArvorePreOrdem()); // exibe a arvore
			return true;
		}
	}

	/*
	 * Metodo auxiliar para o metodo da remocao. Acha o no de menor chave ao
	 * lado direito do no dado
	 */
	protected NodeAB minDir(NodeAB p) {
		if (p.esq == null)
			return p;
		else {
			p = p.esq;
			return minDir(p);
		}
	}

	// Metodo de execucao da Arvore Binaria
	public void inicio() {
		String parametro;
		String tipodeOperacao = " ";
		// Inserindo na árvore
		int[] valor = { 27, 70, 45, 0, 55, 89, 38, 62, 29, 18 };

		System.out.print("Inserindo na árvore: ");

		for (int i = 0; i <= 9; i++) {
			this.inserirElemento(valor[i]);
			System.out.print(valor[i] + "  ");
		}
		System.out.println("");

		JOptionPane.showMessageDialog(null, "Arvore atual: " + exibirArvorePreOrdem()); // impressao
		System.out.println("Listar: " + this.exibirArvorePreOrdem());

		// JOptionPane.showMessageDialog(null,"Arvore Binaria \n Aceita entrada
		// de valores inteiros"," Arvore Binaria");
		System.out.println("-- operações --");
		while (tipodeOperacao != null) {// inicio do loop
			tipodeOperacao = JOptionPane
					.showInputDialog("Opção" + ":\n [1] Buscar \n [2] Inserir \n [3] " + "Remover\n [4] Listar");
			if (tipodeOperacao == null || tipodeOperacao.length() == 0) {
				return;
			} // entrada vazia(cancelamento do panel)

			boolean testeOperador = false;
			for (int j = 0; j < tipodeOperacao.length(); j++) {// testa se
																// entrada nao é
																// inteiro
				if (!Character.isDigit(tipodeOperacao.charAt(j))) {
					testeOperador = true;
				}
			}
			if (testeOperador) {
				JOptionPane.showMessageDialog(null, "Entrada incorreta ");
			} else {
				// IMPRESSAO
				if (tipodeOperacao.length() == 1 && tipodeOperacao.charAt(0) == '4') {// impressao
					JOptionPane.showMessageDialog(null, "Arvore atual: " + exibirArvorePreOrdem()); // impressao
					System.out.println("Listar: " + this.exibirArvorePreOrdem());
				} // passa
				else {
					parametro = JOptionPane.showInputDialog("Entre com um valor: ");
					if (parametro == null || parametro.length() == 0) {
						return;
					} // teste de cancelamento ou entrada vazia

					boolean testeParametro = false;
					for (int j = 0; j < parametro.length(); j++) {
						if (!Character.isDigit(parametro.charAt(j))) { // testa
																		// se
																		// entrada
																		// nao é
																		// inteiro
							testeParametro = true;
						}
					}
					if (testeParametro) {
						JOptionPane.showMessageDialog(null, "A árvore aceita apenas inteiros", " Arvore B ",
								JOptionPane.ERROR_MESSAGE);
					} else {
						int x = Integer.parseInt(parametro);
						// BUSCA
						if (tipodeOperacao.length() == 1 && tipodeOperacao.charAt(0) == '1') {
							if (this.buscarElemento(x) == null) {// busca
								JOptionPane.showMessageDialog(null, "Elemento não Encontrado: " + parametro,
										"Impressao", JOptionPane.INFORMATION_MESSAGE);
								System.out.println("Busca: Elemento não Encontrado" + parametro);
							} else {
								JOptionPane.showMessageDialog(null, "Elemento Encontrado: " + parametro, "Impressao",
										JOptionPane.INFORMATION_MESSAGE);
								System.out.println("Busca: Elemento Encontrado" + parametro);
							}
						}
						// INSERCAO
						else if (tipodeOperacao.length() == 1 && tipodeOperacao.charAt(0) == '2') {// insercao
							this.inserirElemento(x);
							JOptionPane.showMessageDialog(null, "A arvore atual e': " + exibirArvorePreOrdem());
							System.out.println("Inserindo: " + parametro);
						}
						// REMOCAO
						else if (tipodeOperacao.length() == 1 && tipodeOperacao.charAt(0) == '3') {// remocao
							this.removerElemento(x);
							JOptionPane.showMessageDialog(null, "A arvore atual e': " + exibirArvorePreOrdem());
							System.out.println("Removendo: " + parametro);
						} else if (tipodeOperacao == null) {// cancelou
							System.out.println("Fim da operacao");
							return;
						} else {// Se diferente de todas as outras opcoes,
								// entrada incorreta
							JOptionPane.showMessageDialog(null, "Entrada incorreta", " Arvore Binaria ",
									JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			}
			System.out.println(exibirArvorePreOrdem());
		}
		System.out.println("Finalizando operacao do programa de execucao da Arvore Binaria");
	}

}
