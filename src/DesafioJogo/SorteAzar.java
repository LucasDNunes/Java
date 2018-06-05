package DesafioJogo;

import Fila.Elemento;

public class SorteAzar {

		private Elemento topo = null;

		public SorteAzar() {
		}

		public boolean estaCheia() {
			return topo != null;
		}

		public void empilha(int v) {
			Elemento novo = new Elemento(v);
			if (topo == null)
				topo = novo;
			else {
				novo.proximo = topo;
				topo = novo;
			}
		}

		public int desempilha() {
			int retVal = topo.valor;
			topo = topo.proximo;
			return retVal;
		}
	
}
