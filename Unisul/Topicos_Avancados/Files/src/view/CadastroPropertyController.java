package view;

import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import model.property.PropertyServiceImpl;

public class CadastroPropertyController {
	
	@FXML TextField txtLargura;
	@FXML TextField txtAltura;
	@FXML TextField txtPath;
	@FXML ColorPicker colorPicker;
	@FXML TextField txtRazaoSocial;
	
	private PropertyServiceImpl propertyServiceImpl  =  new PropertyServiceImpl();
	
	@FXML
	public void abreDiretorio() {
		
		propertyServiceImpl.abreDiretorio(txtPath);
		
	}
	
	@FXML
	public void gravar() {
		
		propertyServiceImpl.gravar(txtPath, colorPicker, txtLargura, txtAltura, txtRazaoSocial);
		
	}
	
}
