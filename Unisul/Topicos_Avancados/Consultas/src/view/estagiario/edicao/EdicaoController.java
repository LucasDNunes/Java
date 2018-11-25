package view.estagiario.edicao;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.estagiario.Estagiario;
import model.estagiario.edicao.EdicaoServiceImpl;

public class EdicaoController {
	
	@FXML private TextField txtNome;
	@FXML private TextField txtSemestre;
	@FXML private Button btnAtualizar;

	private Estagiario estagiarioPai;
	
	private EdicaoServiceImpl edicaoService = new EdicaoServiceImpl();
	
	public void initialize() {
		edicaoService.initialize(btnAtualizar, txtNome);
	}

	@FXML
	public void atualiar() {
		
		edicaoService.atualizar(txtNome, txtSemestre, this.estagiarioPai);
	}
	
	@FXML
	private void cancelar() {

		edicaoService.voltarTelaPai(txtNome);

	}
	
	public void atualizarEstagiario(Estagiario estagiario) {
		this.estagiarioPai = estagiario;
		txtNome.setText(estagiario.getNome());
		txtSemestre.setText(estagiario.getSemestre().toString());
	}
	
}
