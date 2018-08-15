package view;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

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
		//txtResultado.setText(String.format("%.2f", imc));
		
		if (rdMasc.isSelected()) {
			txtResultado.setText("Senhor: "+ String.format("%.2f", imc));
		}else if(rdFem.isSelected()) {
			txtResultado.setText("Senhora: "+ String.format("%.2f", imc));
		}
	}
	
	@FXML
	public void LimpaTela() {
		txtNome.setText("");
		txtAltura.setText("");
		txtPeso.setText("");
		rdFem.setSelected(false);
		rdMasc.setSelected(false);
	}
}
