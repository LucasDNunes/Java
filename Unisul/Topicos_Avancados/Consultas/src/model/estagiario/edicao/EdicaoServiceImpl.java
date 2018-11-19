package model.estagiario.edicao;

import core.util.MensagemUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.estagiario.Estagiario;
import model.estagiario.EstagiarioRepository;

import java.io.IOException;

public class EdicaoServiceImpl extends EstagiarioRepository implements EdicaoService{

	@Override
	public Estagiario objectMap(TextField txtNome, TextField txtSemestre) {
		try {
			return Estagiario.builder()
					.nome(txtNome.getText())
					.semestre(Integer.parseInt(txtSemestre.getText()))
					.build();
		} catch (Exception e) {
			MensagemUtils.mostraMensagem(e.getMessage(), AlertType.ERROR);
		}
		return null;
	}

	@Override
	public void atualizar(TextField txtNome, TextField txtSemestre, Estagiario estagiarioPai) {
		Estagiario map = objectMap(txtNome, txtSemestre);
		estagiarioPai.setNome(map.getNome());
		estagiarioPai.setSemestre(map.getSemestre());
		salvar(estagiarioPai);
		
	}

	@Override
	public void voltarTelaPai(TextField txtNome) {
		try {
			FXMLLoader lo = new FXMLLoader(getClass().getResource("../../../view/estagiario/Estagiario.fxml"));
			lo.load();
		} catch (IOException e) {
			MensagemUtils.mostraMensagem(e.getMessage(), AlertType.ERROR);
		}

		Stage se = (Stage) txtNome.getScene().getWindow();
		se.close();
	}

}
