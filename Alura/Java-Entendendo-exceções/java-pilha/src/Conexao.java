
public class Conexao implements AutoCloseable {
	public Conexao() {
		System.out.println("Abrindo conex�o");
		//throw new IllegalStateException();
	}
	public void leDaDos() {
		System.out.println("Recebendo dados");
		throw new IllegalStateException();
	}
	
	@Override
	public void close(){
		System.out.println("Fechando conex�o");
		
	}

}
