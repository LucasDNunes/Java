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
		//txtResultado.setText(imc+"");
		txtResultado.setText("sadsad");
		
//		if (rdMasc.isSelected()) {
//			
//		}
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
