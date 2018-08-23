package view;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		
		 Button b = new Button("Botão");
	        b.setOnAction((a) -> {
	            TelaCadastro t = new TelaCadastro();
	            t.start(primaryStage);
	        });
	        
		try {
			//AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("Imc.fxml"));
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("ExemploCadastro.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Cálculo IMC");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
