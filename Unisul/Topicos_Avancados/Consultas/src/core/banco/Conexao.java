package core.banco;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;

import core.util.MensagemUtils;
import javafx.scene.control.Alert.AlertType;

public class Conexao {
	
	private Conexao() {
	    throw new IllegalStateException("Conexão class");
	  }

	
	public static Connection getConexao() {
		Connection conn =  null;
		
		try {
			File file = new File("Consultas.db");
			if (file.exists()) {
				Class.forName("org.sqlite.JDBC");
				conn = DriverManager.getConnection("jdbc:sqlite:Consultas.db");
			}
		} catch (Exception e) {
			MensagemUtils.mostraMensagem(e.getMessage(), AlertType.ERROR);
		}
		
		return conn;
	}
}
