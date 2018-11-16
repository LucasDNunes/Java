package view.estagiario;

import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.estagiario.Estagiario;
import model.estagiario.EstagiarioServiceImpl;

public class EstagiarioController {
	
	private EstagiarioServiceImpl estagiarioService = new EstagiarioServiceImpl();
	
    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtSemestre;
    @FXML
    private TextField txtFiltro;
    
    @FXML
    private TableView<Estagiario> tableView;
    @FXML
    private TableColumn<Estagiario, String> colNome;
    @FXML
    private TableColumn<Estagiario, Number> colSemestre;
    
    @FXML
    private Button btnExcluir;
    @FXML
    private Button btnEditar;
    
    private List<Estagiario> estagiarios = new ArrayList<>();
    
    
    @FXML
    void initialize() {
    	estagiarioService.initialize(tableView, colNome, colSemestre, estagiarios);
    }
    
    
    @FXML
    void cadastrar() {
    	estagiarioService.cadastrar(txtNome, txtSemestre, tableView, estagiarios);
    }
    
    @FXML
    void selecionarEstagiorario() {
    	estagiarioService.editarOuexcluir(btnExcluir, btnEditar);
    }
    
    @FXML
    void excluir() {
    	estagiarioService.excluirSelecionado(btnExcluir, tableView, estagiarios);
    }
    
    @FXML
    void editar() {
    	estagiarioService.editarSelecionado(btnExcluir, tableView, estagiarios);
    }
    
    
    @FXML
    void filtrar() {
    	estagiarioService.filtrar(txtFiltro, tableView, estagiarios);
    }
}
