package model.estagiario.edicao;

import core.util.MensagemUtils;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import model.estagiario.Estagiario;
import model.estagiario.EstagiarioRepository;

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
	public void cadastrar(TextField txtNome, TextField txtSemestre) {
		Estagiario map = objectMap(txtNome, txtSemestre);
		salvar(map);
		
	}
	
}
