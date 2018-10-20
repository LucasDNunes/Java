package view;

import java.io.File;
import java.io.FileWriter;
import java.util.Objects;
import java.util.Properties;

import org.omg.CORBA.Object;

import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

public class CadastroPropertyController {
	
	@FXML TextField txtLargura;
	@FXML TextField txtAltura;
	@FXML TextField txtPath;
	@FXML ColorPicker colorPicker;
	@FXML TextField txtRazaoSocial;
	
	@FXML
	public File abreDiretorio() {
		
		FileChooser fileChooser = new FileChooser();
		File file = fileChooser.showOpenDialog(null);
		try {
			if (Objects.nonNull(file)) {
				return file;
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return null;
	}
	
	@FXML
	public void gravar() {
		File file = new File(txtPath.getText());
		if (file.isFile()) {
			
			Color color = colorPicker.getValue();
			String webFormat = String.format("#%02x%02x%02x", 
					(int) (255 * color.getRed()),
					(int) (255 * color.getGreen()),
					(int) (255 * color.getBlue()));
			
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
	
}
