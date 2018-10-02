package view;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import model.Trabalhador;

public class ExemploController {
	@FXML TextField qtdTrabalhador1;
	@FXML TextField tempoTrabalhador1;
	@FXML ProgressBar progressoTrabalhador1;
	
	@FXML TextField qtdTrabalhador2;
	@FXML TextField tempoTrabalhador2;
	@FXML ProgressBar progressoTrabalhador2;
	
	public void iniciarComThread() {
		Trabalhador trabalhador1 = new Trabalhador(Integer.parseInt(qtdTrabalhador1.getText()),
				Integer.parseInt(tempoTrabalhador1.getText()),
				progressoTrabalhador1);
		Trabalhador trabalhador2 = new Trabalhador(Integer.parseInt(qtdTrabalhador2.getText()),
				Integer.parseInt(tempoTrabalhador2.getText()),
				progressoTrabalhador2);
		
		
		new Thread(trabalhador1).start();
		new Thread(trabalhador2).start();
		
	}
	
	public void iniciarSemThread() {
		Trabalhador trabalhador1 = new Trabalhador(Integer.parseInt(qtdTrabalhador1.getText()),
				Integer.parseInt(tempoTrabalhador1.getText()),
				progressoTrabalhador1);
		Trabalhador trabalhador2 = new Trabalhador(Integer.parseInt(qtdTrabalhador2.getText()),
				Integer.parseInt(tempoTrabalhador2.getText()),
				progressoTrabalhador2);
		
		trabalhador1.iniciar();
		trabalhador2.iniciar();
		
	}
	
	@FXML
	public void sair() {
		Platform.exit();
	}
}
