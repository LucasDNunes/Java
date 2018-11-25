package model.estagiario.edicao;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.estagiario.Estagiario;

public interface EdicaoService {
	
	Estagiario objectMap(TextField txtNome, TextField txtSemestre);
	
	void atualizar(TextField txtNome, TextField txtSemestre, Estagiario estagiarioPai);

	void voltarTelaPai(TextField txtNome);

	void initialize(Button btnAtualizar, TextField txtNome);
}
