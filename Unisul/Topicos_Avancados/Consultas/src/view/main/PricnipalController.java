package view.main;

import java.util.Objects;

import core.util.MensagemUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.Alert.AlertType;

public class PricnipalController {
	@FXML
	TabPane tabPane;
	
	@FXML
	public void abreTelaCadastro() {
		abreTab("Sala", "../cadastrosala/CadastroSala.fxml");
	}
	
	@FXML
	public void abreTelaCadastroEstagiario() {
		abreTab("Estagiario", "../estagiario/Estagiario.fxml");
	}
	
	private void abreTab(String titulo, String path) {
		try {
			Tab tab = tabAberta(titulo);
			if (Objects.isNull(tab)) {
				tab = new Tab(titulo);
				tab.setClosable(true);
				tabPane.getTabs().add(tab);
				tab.setContent((Node) FXMLLoader.load(getClass().getResource(path)));
			}
			selecionaTab(tab);
		} catch (Exception e) {
			MensagemUtils.mostraMensagem(e.getMessage(),AlertType.INFORMATION); 
		}
	}
	
	private void selecionaTab(Tab tab) {
		tabPane.getSelectionModel().select(tab);
	}
	
	private Tab tabAberta(String titulo) {
		for (Tab tab : tabPane.getTabs()) {
			if (Objects.nonNull(tab.getText()) && tab.getText().equals(titulo)) {
				return tab;
			}
		}
		return null;
	}
}
