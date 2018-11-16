package view.estagiario.edicao;

import java.io.IOException;

import core.util.MensagemUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.estagiario.Estagiario;
import model.estagiario.edicao.EdicaoServiceImpl;
import view.estagiario.EstagiarioController;

public class EdicaoController {
	
	@FXML private TextField txtNome;
	@FXML private TextField txtSemestre;
	
	private EdicaoServiceImpl edicaoService = new EdicaoServiceImpl();
	
	@FXML
	private void atualiar() {
		
		edicaoService.cadastrar(txtNome, txtSemestre);
		
		FXMLLoader lo = new FXMLLoader(getClass().getResource("../Estagiario.fxml"));
		try {
			lo.load();
		} catch (IOException e) {
			MensagemUtils.mostraMensagem(e.getMessage(), AlertType.ERROR);
		}

		EstagiarioController ca = lo.getController();
		
		
		Stage se = (Stage) txtNome.getScene().getWindow();
		se.close();
		
		se.setOnCloseRequest((WindowEvent event1) -> {
	        
	    });
	}
	
	@FXML
	private void cancelar() {
		try {
			FXMLLoader lo = new FXMLLoader(getClass().getResource("../Estagiario.fxml"));
			lo.load();
		} catch (IOException e) {
			MensagemUtils.mostraMensagem(e.getMessage(), AlertType.ERROR);
		}
		
		Stage se = (Stage) txtNome.getScene().getWindow();
		se.close();
	}
	
	public void atualizarEstagiario(Estagiario estagiario) {
		txtNome.setText(estagiario.getNome());
		txtSemestre.setText(estagiario.getSemestre().toString());
	}
	
}
