package view;

import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.aluno.Aluno;
import model.transporte.Transporte;
import model.transporte.TransporteServiceImpl;

public class CadastroTransporteController {

	@FXML
	RadioButton rdProprio;
	@FXML
	RadioButton rdPublico;

	@FXML
	TextField txtDistancia;
	@FXML
	TextField txtCustoMensal;
	
	@FXML TableView<Transporte> tableView;
	@FXML TableColumn<Transporte, String> colTipo;
	@FXML TableColumn<Transporte, Number> colDistancia;
	@FXML TableColumn<Transporte, Number> colCusto;
	
	private List<Transporte> transportes =  new ArrayList<>();
	
	private TransporteServiceImpl transporteServiceImpl = new TransporteServiceImpl(); 
	
	@FXML
	public void initialize() {
		transporteServiceImpl.inicializaTableView(colTipo, colDistancia, colCusto);
		transporteServiceImpl.lerArquivo(transportes, tableView);
	}
	
	@FXML
	public void cadastrar() {
		transporteServiceImpl.gravar(rdProprio, rdPublico, txtDistancia, txtCustoMensal);
		transporteServiceImpl.lerArquivo(transportes, tableView);
	}
	
	@FXML
	public void unirArquivos() {
		transporteServiceImpl.unirAlunoETransporte();
	}
}
