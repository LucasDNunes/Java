package model.estagiario.edicao;

import javafx.scene.control.TextField;
import model.estagiario.Estagiario;

public interface EdicaoService {
	
	Estagiario objectMap(TextField txtNome, TextField txtSemestre);
	
	void cadastrar(TextField txtNome, TextField txtSemestre);
}
