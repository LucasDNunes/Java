package view;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import model.Atleta;

public class SimuladoQuestao1Controller {
	
	@FXML TextField distanciaAtleta1;
	@FXML TextField tempoAtleta1;
	@FXML ProgressBar barraAtleta1;
	
	@FXML TextField distanciaAtleta2;
	@FXML TextField tempoAtleta2;
	@FXML ProgressBar barraAtleta2;
	
	public void iniciar() {
		Atleta atleta1 = new Atleta(Double.parseDouble(distanciaAtleta1.getText()),
				Integer.parseInt(tempoAtleta1.getText()),
				barraAtleta1);
		Atleta atleta2 = new Atleta(Double.parseDouble(distanciaAtleta2.getText()),
				Integer.parseInt(tempoAtleta2.getText()),
				barraAtleta2);
		
		
		new Thread(atleta1).start();
		new Thread(atleta2).start();
	}
	
	public void sair() {
		System.exit(0);
		//Platform.exit();
	}
}
