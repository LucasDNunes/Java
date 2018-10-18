package view;

import java.io.FileInputStream;
import java.io.FileReader;
import java.util.Properties;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class PropertyController {
	
	Double largura;
	Double Altura;
	String logo;
	String cor;
	String razaoSocial;
	
	@FXML AnchorPane anchorPane;
	@FXML VBox vBox;
	@FXML Label label;
	@FXML ImageView imageView;
	
	@FXML
	public void initialize() {
		lerArquivo();
		anchorPane.setMinWidth(largura);
		anchorPane.setMinHeight(Altura);
		label.setText(razaoSocial);
		anchorPane.setStyle("-fx-background-color: "+cor);
		
		try {
			imageView.setImage(new Image(new FileInputStream(logo)));
		} catch (Exception e) {
			e.getMessage();
		}
	}
	
	private void lerArquivo() {
		Properties properties = new Properties();
		
		try (
				FileReader fileReader =  new FileReader("property.txt") 
			) {
			
			properties.load(fileReader);
			
		} catch (Exception e) {
			e.getMessage();
		}
		
		this.Altura = Double.parseDouble(properties.getProperty("Altura"));
		this.largura = Double.parseDouble(properties.getProperty("Largura"));
		this.cor = properties.getProperty("CorFundo");
		this.logo = properties.getProperty("LogoTipo");
		this.razaoSocial = properties.getProperty("RazaoSocial");
		
	}
}
