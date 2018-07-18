
public class TexteConexao {

	public static void main(String[] args) {
		// cod mais recendo mais simplificado;
		try(Conexao conexao = new Conexao()) {
			conexao.leDaDos();
		} catch (IllegalStateException e) {
			System.out.println("deu error");
		}
		
		

		// ================
		
//		Conexao con = null;	
//		
//		try {
//			con = new Conexao();
//			con.leDaDos();
//		} catch (IllegalStateException e) {
//			System.out.println("deu erro");
//		}finally {
//			con.close();
//		}

	}

}
