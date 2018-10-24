package model.property;

import java.io.File;
import java.io.FileWriter;
import java.util.Objects;
import java.util.Properties;

import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

public class PropertyServiceImpl implements PropertyService{

	@Override
	public void abreDiretorio(TextField txtPath) {
		FileChooser fileChooser = new FileChooser();
		File file = fileChooser.showOpenDialog(null);
		try {
			if (Objects.nonNull(file)) {
				txtPath.setText(file.getAbsolutePath());
			}
		} catch (Exception e) {
			e.getMessage();
		}
	}

	@Override
	public void gravar(TextField txtPath, ColorPicker colorPicker, TextField txtLargura, TextField txtAltura,
			TextField txtRazaoSocial) {
		
		File file = new File(txtPath.getText());
		if (file.isFile()) {
			
			Color color = colorPicker.getValue();
			String webFormat = corFundo(color);
			
			Properties properties = new Properties();
			properties.setProperty("Largura", txtLargura.getText());
			properties.setProperty("Altura", txtAltura.getText());
			properties.setProperty("LogoTipo", txtPath.getText());
			properties.setProperty("CorFundo", webFormat);
			properties.setProperty("RazaoSocial", txtRazaoSocial.getText());
		
			try (
					FileWriter fileWriter = new FileWriter("property.txt");
				){
				
				properties.store(fileWriter, "Arquivo de preferencia");
				
			} catch (Exception e) {
				e.getMessage();
			}
		}
		
	}
	
	private String corFundo(Color color) {
		
		return String.format("#%02x%02x%02x", 
				(int) (255 * color.getRed()),
				(int) (255 * color.getGreen()),
				(int) (255 * color.getBlue()));
	}

}
