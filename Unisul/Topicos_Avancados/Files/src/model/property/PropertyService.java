package model.property;

import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;

public interface PropertyService {
	
	public void abreDiretorio(TextField txtPath);
	
	public void gravar(TextField txtPath, ColorPicker colorPicker, TextField txtLargura, TextField txtAltura, TextField txtRazaoSocial);
}
