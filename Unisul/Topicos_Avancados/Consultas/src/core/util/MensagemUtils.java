package core.util;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class MensagemUtils {

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
}
