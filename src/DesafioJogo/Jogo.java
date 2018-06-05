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
		sa.empilha(1);
		t.insereNaPosicao('c', 1);
		sa.empilha(2);
		t.insereNaPosicao('d', 2);
		sa.empilha(1);
		t.insereNaPosicao('e', 3);
		sa.empilha(3);
		t.insereNaPosicao('f', 4);
		sa.empilha(4);
		t.insereNaPosicao('g', 5);
		sa.empilha(5);
		t.insereNaPosicao('h', 6);
		sa.empilha(4);
		t.insereNaPosicao('i', 7);
		sa.empilha(1);
		t.insereNaPosicao('j', 8);
		sa.empilha(1);
		t.insereNaPosicao('k', 9);
		sa.empilha(2);
		t.insereNaPosicao('l', 10);
		sa.empilha(4);
		t.insereNaPosicao('m', 11);
		sa.empilha(3);
		t.insereNaPosicao('n', 12);
		sa.empilha(2);
		t.insereNaPosicao('o', 13);
		sa.empilha(2);
		t.insereNaPosicao('p', 14);
		sa.empilha(1);
		t.insereNaPosicao('w', 15);
		sa.empilha(1);
		t.insereNaPosicao('r', 16);
		sa.empilha(2);
		t.insereNaPosicao('d', 17);
		sa.empilha(3);
		t.insereNaPosicao('t', 18);
		sa.empilha(5);
		t.insereNaPosicao('u', 19);
		sa.empilha(4);
		t.insereNaPosicao('v', 20);
		sa.empilha(2);
		t.insereNaPosicao('e', 21);
		sa.empilha(3);
		t.insereNaPosicao('x', 22);
		sa.empilha(2);
		t.insereNaPosicao('y', 23);
		sa.empilha(1);
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

		String palavra = JOptionPane.showInputDialog("Jogador 1, insira a palavra: ");
		for (int i = 0; i < palavra.length(); i++) {
			char l = palavra.charAt(i);
			p1.insere(l);
		}
		for (int i = 0; i < palavra.length(); i++) {
			char l = '*';
			pa.insere(l);
		}

		palavra = JOptionPane.showInputDialog("Jogador 2, insira a palavra: ");
		for (int i = 0; i < palavra.length(); i++) {
			char l = palavra.charAt(i);
			p2.insere(l);
		}
		for (int i = 0; i < palavra.length(); i++) {
			char l = '*';
			pb.insere(l);
		}

		palavra = JOptionPane.showInputDialog("Jogador 3, insira a palavra: ");
		for (int i = 0; i < palavra.length(); i++) {
			char l = palavra.charAt(i);
			p3.insere(l);
		}
		for (int i = 0; i < palavra.length(); i++) {
			char l = '*';
			pc.insere(l);
		}

		int op;
		do {
			op = Integer.parseInt(JOptionPane.showInputDialog("-----Jogador 1----- \n\n1 - Avançar \n 2- Retroceder"));

			if (op == 1) {
				avancarJogador(j1);
				paradaJogador(j1, p2, pb);
			}
			if (op == 2) {
				retrocedeJogador(j1);
				paradaJogador(j1, p2, pb);
			}

			op = Integer.parseInt(JOptionPane.showInputDialog("-----Jogador 2----- \n\n1 - Avançar \n 2- Retroceder"));
			if (op == 1) {
				avancarJogador(j2);
				paradaJogador(j2, p3, pc);
			}
			if (op == 2) {
				retrocedeJogador(j2);
				paradaJogador(j2, p3, pc);
			}

			op = Integer.parseInt(JOptionPane.showInputDialog("-----Jogador 3----- \n\n1 - Avançar \n 2- Retroceder"));
			if (op == 1) {
				avancarJogador(j3);
				paradaJogador(j3, p1, pa);
			}
			if (op == 2) {
				retrocedeJogador(j3);
				paradaJogador(j3, p1, pa);
			}
			System.out.println(op);
		} while ((j1.ganhador == false) & (j2.ganhador == false) & (j3.ganhador == false));
	}

	public static void avancarJogador(Jogador j) {
		int dado = rodarDado();
		System.out.println(dado);
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
		System.out.println(dado);
		for (int i = 0; i < dado; i++) {
			t.atual = j.posicao;
			if (t.atual.anterior != null) {
				t.atual = t.atual.anterior;
				j.posicao = t.atual;
			}
		}
	}

	public static void paradaJogador(Jogador j, Palavra palavra, Palavra adivinha) {

		JOptionPane.showMessageDialog(null, "Você parou na letra: " + j.posicao.letra);
		if (adivinha.verificar(j.posicao, palavra)) {
			if (JOptionPane.showConfirmDialog(null, adivinha + "\n\nDeseja tentar adivinhar a palavra?\n",
					"Você descobriu uma letra! ", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
				String tentativa = JOptionPane.showInputDialog(adivinha + "\nDigite a palavra: ");
				if (palavra.comparacao(tentativa)) {
					JOptionPane.showMessageDialog(null, "Ganhou o Jogo!");
					ganhaJogo(j);
				} else {
					JOptionPane.showMessageDialog(null, "Sorte ou azar.");
					int aux = sa.desempilha();
					switch (aux) {
					case 1:
						adivinha.removeLetra();
						break;
					case 2:
						adivinha.insereLetra(palavra);
						break;
					case 3:
						// ganhaJogada();
						break;
					case 4:
						// perdeJogada();
						break;
					case 5:
						ganhaJogo(j);
						break;
					}
				}
			} else {
				JOptionPane.showMessageDialog(null, "Sorte ou azar.");
				int aux = sa.desempilha();
				System.out.println(aux);
				switch (aux) {
				case 1:
					adivinha.removeLetra();
					break;
				case 2:
					adivinha.insereLetra(palavra);
					break;
				case 3:
					// ganhaJogada();
					break;
				case 4:
					// perdeJogada();
					break;
				case 5:
					ganhaJogo(j);
					break;
				}
			}
		} else {
			int aux = sa.desempilha();
			switch (aux) {
			case 1:
				adivinha.removeLetra();
				break;
			case 2:
				adivinha.insereLetra(palavra);
				break;
			case 3:
				// ganhaJogada();
				break;
			case 4:
				// perdeJogada();
				break;
			case 5:
				// ganhaJogo();
				break;
			}
		}

	}

	public static void ganhaJogo(Jogador j) {

		j.ganhador = true;
	}

	public static int rodarDado() {
		Random ran = new Random();
		int dado = ran.nextInt(6);

		return dado;
	}
}
