package model.estagiario;

import java.util.List;

import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public interface EstagiarioService {
	
	void initialize(TableView<Estagiario> tableView, TableColumn<Estagiario, String> colNome, TableColumn<Estagiario, Number> colSemestre, List<Estagiario> estagiarios);
	
	Estagiario objectMap(TextField txtNome, TextField txtSemestre);
	
	void cadastrar(TextField txtNome, TextField txtSemestre, TableView<Estagiario> tableView, List<Estagiario> estagiarios);
	
	void limparTela(TextField txtNome, TextField txtSemestre);
	
	void editarOuexcluir(Button btnExcluir, Button btnEditar);
	
	void editarSelecionado(Button btnExcluir, TableView<Estagiario> tableView, List<Estagiario> estagiarios);
	
	void excluirSelecionado(Button btnExcluir, TableView<Estagiario> tableView, List<Estagiario> estagiarios);
	
	void filtrar(TextField txtFiltro, TableView<Estagiario> tableView, List<Estagiario> estagiarios);
}
