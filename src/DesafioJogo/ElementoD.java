package DesafioJogo;

	public class ElementoD {
		public char letra;
		public ElementoD proximo;
		public ElementoD anterior;

		public ElementoD(char l) {
			letra = l;
			proximo = null;
			anterior = null;
		}
		
	}

