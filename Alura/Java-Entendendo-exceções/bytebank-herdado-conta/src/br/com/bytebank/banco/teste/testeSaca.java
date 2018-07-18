package br.com.bytebank.banco.teste;

import br.com.bytebank.banco.modelo.Conta;
import br.com.bytebank.banco.modelo.ContaCorrente;
import br.com.bytebank.banco.modelo.SaldoInsuficienteException;

public class testeSaca {

	public static void main(String[] args) {
		Conta conta = new ContaCorrente(111, 222);
		
		conta.deposita(200.00);
		try {
			conta.saca(200.00);
		} catch (SaldoInsuficienteException ex) {
			System.out.println(" - "+ex.getMessage());
		}
	
		
		System.out.println(conta.getSaldo());
	}

}
