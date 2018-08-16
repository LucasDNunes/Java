package view;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ImcController {
	
	@FXML TextField txtNome;
	@FXML RadioButton rdMasc;
	@FXML RadioButton rdFem;
	@FXML TextField txtPeso;
	@FXML TextField txtAltura;
	@FXML TextArea txtResultado;
	
	@FXML
	public void CalculaImc() {
		double peso = Double.parseDouble(txtPeso.getText());
		double altura = Double.parseDouble(txtAltura.getText());
		double imc = peso/(altura*altura);
		//txtResultado.setText(Math.round(imc) + "");
		
		if (rdMasc.isSelected()) {
			txtResultado.setText("Senhor: "+ txtNome.getText()+"= "+ String.format("%.2f", imc));
		}else if(rdFem.isSelected()) {
			txtResultado.setText("Senhora: "+ txtNome.getText() +"= " +String.format("%.2f", imc));
		}else {
			txtResultado.setText("Preencha todos os campos!");
		}
	}
	@FXML
	public void trocaTela() {
		TelaCadastro tl = new TelaCadastro();
		//tl.start(Stage primaryStage);
	}
	
	@FXML
	public void LimpaTela() {
		txtNome.setText("");
		txtAltura.setText("");
		txtPeso.setText("");
		rdFem.setSelected(false);
		rdMasc.setSelected(false);
		txtResultado.setText("");
	}
}
