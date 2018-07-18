
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
