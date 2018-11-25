package view.main;
	
import core.util.MensagemUtils;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			AnchorPane root = FXMLLoader.load(getClass().getResource("Pricnipal.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setMaximized(true);
			primaryStage.setMinHeight(850);
			primaryStage.setMinWidth(900);
			primaryStage.show();
		} catch(Exception e) {
			MensagemUtils.mostraErro("Erro ao abir o programa", e);
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
