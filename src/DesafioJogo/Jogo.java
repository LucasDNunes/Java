package DesafioJogo;
import java.util.Random;

import javax.swing.JOptionPane;

public class Jogo {

	static Tabuleiro t = new Tabuleiro();
	static SorteAzar sa = new SorteAzar();
	static Jogador j1 = new Jogador();
	static Palavra p1 = new Palavra();
	static Palavra pa = new Palavra();
	static Jogador j2 = new Jogador();
	static Palavra p2 = new Palavra();
	static Palavra pb = new Palavra();
	static Jogador j3 = new Jogador();
	static Palavra p3 = new Palavra();
	static Palavra pc = new Palavra();

	public static void main(String[] args) {

		t.inserePrimeiro('a');
		t.insereNaPosicao('b', 0);
		sa.empilha(5);
		t.insereNaPosicao('c', 1);
		sa.empilha(2);
		t.insereNaPosicao('d', 2);
		sa.empilha(4);
		t.insereNaPosicao('e', 3);
		sa.empilha(1);
		t.insereNaPosicao('f', 4);
		sa.empilha(3);
		t.insereNaPosicao('g', 5);
		sa.empilha(2);
		t.insereNaPosicao('h', 6);
		sa.empilha(1);
		t.insereNaPosicao('i', 7);
		sa.empilha(1);
		t.insereNaPosicao('j', 8);
		sa.empilha(2);
		t.insereNaPosicao('k', 9);
		sa.empilha(4);
		t.insereNaPosicao('l', 10);
		sa.empilha(1);
		t.insereNaPosicao('m', 11);
		sa.empilha(3);
		t.insereNaPosicao('n', 12);
		sa.empilha(2);
		t.insereNaPosicao('o', 13);
		sa.empilha(2);
		t.insereNaPosicao('p', 14);
		sa.empilha(4);
		t.insereNaPosicao('w', 15);
		sa.empilha(1);
		t.insereNaPosicao('r', 16);
		sa.empilha(1);
		t.insereNaPosicao('d', 17);
		sa.empilha(3);
		t.insereNaPosicao('t', 18);
		sa.empilha(1);
		t.insereNaPosicao('u', 19);
		sa.empilha(2);
		t.insereNaPosicao('v', 20);
		sa.empilha(2);
		t.insereNaPosicao('e', 21);
		sa.empilha(3);
		t.insereNaPosicao('x', 22);
		sa.empilha(2);
		t.insereNaPosicao('y', 23);
		sa.empilha(4);
		t.insereUltimo('z');
		sa.empilha(1);

		j1.propria = p1;
		j2.propria = p2;
		j3.propria = p3;
		j1.adivinhar = pb;
		j2.adivinhar = pc;
		j3.adivinhar = pa;
		j1.posicao = t.primeiro;
		j2.posicao = t.primeiro;
		j3.posicao = t.primeiro;

		cria(p1, pa, "1");
		cria(p2, pb, "2");
		cria(p3, pc, "3");

		do {
			if (!j1.perdeJogada) {
				vez(j1, p2, pb, "1");

				while (j1.jogada) {
					j1.setJogada(false);
					vez(j1, p2, pb, "1");
				}
			} else
				j1.setPerdeJogada(false);

			if (!j2.perdeJogada) {
				vez(j2, p3, pc, "2");

				while (j2.jogada) {
					j2.setJogada(false);
					vez(j2, p3, pc, "2");
				}
			} else
				j2.setPerdeJogada(false);

			if (!j3.perdeJogada) {
				vez(j3, p1, pa, "3");

				while (j3.jogada) {
					j3.setJogada(false);
					vez(j3, p1, pa, "3");
				}
			} else
				j3.setPerdeJogada(false);

		} while (sa.estaCheia());
	}

	public static void cria(Palavra palavra, Palavra adivinha, String p) {
		String string = JOptionPane.showInputDialog(null, "Jogador " + p + " insira a palavra: ", "Palavra secreta", 1);
		for (int i = 0; i < string.length(); i++) {
			char l = string.charAt(i);
			palavra.insere(l);
		}
		for (int i = 0; i < string.length(); i++) {
			char l = '*';
			adivinha.insere(l);
		}
	}

	public static void vez(Jogador j, Palavra palavra, Palavra adivinha, String p) {
		int op = Integer.parseInt(
				JOptionPane.showInputDialog(null, "Escolha: \n1 - Avançar \n 2- Retroceder", "Vez do jogador " + p, 1));
		if (op == 1) {
			avancarJogador(j);
			paradaJogador(j, palavra, adivinha);
		}
		if (op == 2) {
			retrocedeJogador(j);
			paradaJogador(j, palavra, adivinha);
		}
	}

	public static void avancarJogador(Jogador j) {
		int dado = rodarDado();
		for (int i = 0; i < dado; i++) {
			t.atual = j.posicao;
			if (t.atual.proximo != null) {
				t.atual = t.atual.proximo;
				j.posicao = t.atual;
			}
		}
	}

	public static void retrocedeJogador(Jogador j) {
		int dado = rodarDado();
		for (int i = 0; i < dado; i++) {
			t.atual = j.posicao;
			if (t.atual.anterior != null) {
				t.atual = t.atual.anterior;
				j.posicao = t.atual;
			}
		}
	}

	public static void paradaJogador(Jogador j, Palavra palavra, Palavra adivinha) {

		JOptionPane.showMessageDialog(null, "Você parou na letra: " + j.posicao.letra, "Posição no tabuleiro", 1);
		if (adivinha.verificar(j.posicao, palavra)) {
			if (JOptionPane.showConfirmDialog(null, adivinha + "\n\nDeseja tentar adivinhar a palavra?\n",
					"Você descobriu uma letra! ", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
				String tentativa = JOptionPane.showInputDialog(adivinha + "\nDigite a palavra: ");
				if (palavra.comparacao(tentativa)) {
					JOptionPane.showMessageDialog(null, "Ganhou o Jogo!", "Acertou!", 1);
					ganhaJogo(j);
				} else {
					JOptionPane.showMessageDialog(null, "Sorte ou revés.", "Você errou !", 2);
					int aux = sa.desempilha();
					switch (aux) {
					case 1:
						if (adivinha.removeLetra()) {
							JOptionPane.showMessageDialog(null, "Você perdeu uma letra!\n" + adivinha,
									"Perca uma letra", 0);
						} else
							JOptionPane.showMessageDialog(null, "Você não possui letra na palavra!\n " + adivinha,
									"Perca uma letra", 0);
						break;
					case 2:
						adivinha.insereLetra(palavra);
						JOptionPane.showMessageDialog(null, adivinha, "Ganhe uma Letra!", 1);
						break;
					case 3:
						JOptionPane.showMessageDialog(null, "Você ganhou uma vez! \n", "Jogue novamente", 1);
						adivinha.ganhaJogada(j);
						break;
					case 4:
						JOptionPane.showMessageDialog(null, "Fique uma jogada sem jogar!", "Perdeu a vez", 0);
						adivinha.perdeJogada(j);
						break;
					case 5:
						JOptionPane.showMessageDialog(null, "Ganhou o Jogo!", "Muita sorte!", 1);
						ganhaJogo(j);
						break;
					}
				}
			} else {
				JOptionPane.showMessageDialog(null, "Sorte ou revés.", null, 2);
				int aux = sa.desempilha();
				switch (aux) {
				case 1:
					if (adivinha.removeLetra()) {
						JOptionPane.showMessageDialog(null, "Você perdeu uma letra!\n" + adivinha, "Perca uma letra",
								0);
					} else
						JOptionPane.showMessageDialog(null, "Você não possui letra na palavra!\n " + adivinha,
								"Perca uma letra", 0);
					break;
				case 2:
					adivinha.insereLetra(palavra);
					JOptionPane.showMessageDialog(null, adivinha, "Ganhe uma Letra!", 1);
					break;
				case 3:
					JOptionPane.showMessageDialog(null, "Você ganhou uma vez! \n", "Jogue novamente", 1);
					adivinha.ganhaJogada(j);
					break;
				case 4:
					JOptionPane.showMessageDialog(null, "Fique uma jogada sem jogar!", "Perdeu a vez", 0);
					adivinha.perdeJogada(j);
					break;
				case 5:
					JOptionPane.showMessageDialog(null, "Ganhou o Jogo!", "Muita sorte!", 1);
					ganhaJogo(j);
					break;
				}
			}
		} else {
			JOptionPane.showMessageDialog(null, "Sorte ou revés.", "A letra não pertence a sua palavra !", 2);
			int aux = sa.desempilha();
			switch (aux) {
			case 1:
				if (adivinha.removeLetra()) {
					JOptionPane.showMessageDialog(null, "Você perdeu uma letra!\n" + adivinha, "Perca uma letra", 0);
				} else
					JOptionPane.showMessageDialog(null, "Você não possui letra na palavra!\n " + adivinha,
							"Perca uma letra", 0);
				break;
			case 2:
				adivinha.insereLetra(palavra);
				JOptionPane.showMessageDialog(null, adivinha, "Ganhe uma Letra!", 1);
				break;
			case 3:
				JOptionPane.showMessageDialog(null, "Você ganhou uma vez! \n", "Jogue novamente", 1);
				adivinha.ganhaJogada(j);
				break;
			case 4:
				JOptionPane.showMessageDialog(null, "Fique uma jogada sem jogar!", "Perdeu a vez", 0);
				adivinha.perdeJogada(j);
				break;
			case 5:
				JOptionPane.showMessageDialog(null, "Ganhou o Jogo!", "Muita sorte!", 1);
				ganhaJogo(j);
				break;
			}
		}

	}

	public static void ganhaJogo(Jogador j) {

		System.exit(0);
	}

	public static int rodarDado() {
		Random ran = new Random();
		int dado = ran.nextInt(13) + 1;

		return dado;
	}
}
