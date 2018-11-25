package core.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public class MensagemUtils {

	public static final String ERRO ="Erro em chamar tela de edição :'(";

	private MensagemUtils() {
		throw new IllegalStateException("Utility class");
	}

	public static void mostraMensagem(String msg, AlertType tipoMensagem) {
		Alert alerta = new Alert(tipoMensagem);
		alerta.setHeaderText(null);
		alerta.setContentText(msg);
		alerta.show();
	}
	
	public static boolean mostraMensagemPergunta(String msg) {
		Alert alerta = new Alert(AlertType.CONFIRMATION);
		alerta.setHeaderText(null);
		alerta.setContentText(msg);
		Optional<ButtonType> showAndWait = alerta.showAndWait();
		
		if(showAndWait.isPresent()) {
			return showAndWait.get().equals(ButtonType.OK);
		}
		return false;
	}
	
	public static void mostraErro(String msgErro, Exception erro) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setHeaderText(msgErro);
		alert.setContentText(erro.getMessage());

		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		erro.printStackTrace(pw);
		String exceptionText = sw.toString();

		Label label = new Label("The exception stacktrace was:");

		TextArea textArea = new TextArea(exceptionText);
		textArea.setEditable(false);
		textArea.setWrapText(true);

		textArea.setMaxWidth(Double.MAX_VALUE);
		textArea.setMaxHeight(Double.MAX_VALUE);
		GridPane.setVgrow(textArea, Priority.ALWAYS);
		GridPane.setHgrow(textArea, Priority.ALWAYS);

		GridPane expContent = new GridPane();
		expContent.setMaxWidth(Double.MAX_VALUE);
		expContent.add(label, 0, 0);
		expContent.add(textArea, 0, 1);

		// Set expandable Exception into the dialog pane.
		alert.getDialogPane().setExpandableContent(expContent);

		alert.showAndWait();
	}
}
