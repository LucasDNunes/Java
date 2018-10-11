package view;

import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.dadosFile.DadosFile;
import model.dadosFile.DadosFileServiceImpl;

public class ExemploController {
	
	@FXML TextField txtPath;
	
	@FXML TableView<DadosFile> tableView;
	@FXML TableColumn<DadosFile, String> colNome;
	@FXML TableColumn<DadosFile, String> colTamanho;
	
	private DadosFileServiceImpl dadosFileServiceImpl = new DadosFileServiceImpl();
	
	private List<DadosFile> dadosFiles =  new ArrayList<>();
	
	@FXML
	public void initialize() {
		dadosFileServiceImpl.inicializaTableView(colNome, colTamanho);
	}
	
	@FXML
	public void abreDiretorio() {
		dadosFileServiceImpl.abreDiretorio(txtPath);
	}
	
	@FXML
	public void listar() {
		dadosFileServiceImpl.listar(txtPath, tableView, dadosFiles);
	}
	
	@FXML
	public void apagarLinhaSelecionada() {
		dadosFileServiceImpl.apagarLinhaSelecionada(tableView, dadosFiles);
	}
	
	@FXML
	public void apagarTodos() {
		dadosFileServiceImpl.apagarTodos(tableView, dadosFiles);
	}
}
