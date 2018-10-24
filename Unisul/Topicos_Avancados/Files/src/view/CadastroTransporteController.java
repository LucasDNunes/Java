package view;

import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.transporte.Transporte;
import model.transporte.TransporteService;
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
	
	private TransporteService transporteService = new TransporteServiceImpl(); 
	
	@FXML
	public void initialize() {
		transporteService.inicializaTableView(colTipo, colDistancia, colCusto);
		transporteService.lerArquivo(transportes, tableView);
	}
	
	@FXML
	public void cadastrar() {
		transporteService.gravar(rdProprio, rdPublico, txtDistancia, txtCustoMensal);
		transporteService.lerArquivo(transportes, tableView);
	}
	
	@FXML
	public void unirArquivos() {
		transporteService.unirAlunoETransporte();
	}
 
}
