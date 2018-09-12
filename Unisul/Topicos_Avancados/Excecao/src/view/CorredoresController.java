package view;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Corredor;

public class CorredoresController {
	
	@FXML
	TextField txtNome;
	@FXML
	TextField txtNumeroPeito;
	@FXML
	TextField txtDistancia;
	
	@FXML
	DatePicker dataNascimento;
	
	@FXML
	TableView<Corredor> tableView;
	@FXML
	TableColumn<Corredor, String> colNome;
	@FXML
	TableColumn<Corredor, Number> colNumeroPeito;
	@FXML
	TableColumn<Corredor, Number> colIdade;
	@FXML
	TableColumn<Corredor, Number> colDistancia;
	
	private ArrayList<Corredor> corredores = new ArrayList<>();
	
	@FXML
	public void initialize() {
		inicializaTbl();
		tableView.setEditable(true);
	}

	public void adicionar() {
		try {
			inserirTabela();
		} catch (Exception e) {
			mostraMensagem(String.format("valor invalido %s", e.getMessage()), AlertType.WARNING);
		}
	}
	
	private void inserirTabela() {
		Corredor corredor = new Corredor();
		corredor.setNome(txtNome.getText());
		corredor.setNumeroPeito(Integer.parseInt(txtNumeroPeito.getText()));
		corredor.setIdade(calculaIdade(dataNascimento.getValue()));
		corredor.setDistancia(Integer.parseInt(txtDistancia.getText()));
		corredor.setDataNascimento(dataNascimento.getValue());
		
		this.corredores.add(corredor);
		
		
		tableView.setItems(FXCollections.observableArrayList(corredores));

		limpaTela();
	}
	
	private int calculaIdade(LocalDate dataNasc) {
		LocalDate dataHoje = LocalDate.now();
		long idade = ChronoUnit.YEARS.between(dataNasc, dataHoje);
		return (int) idade;
	}

	private void inicializaTbl() {
		colNome.setCellValueFactory(cellData -> cellData.getValue().nomeProperty());
		colNumeroPeito.setCellValueFactory(cellData -> cellData.getValue().distanciaProperty());
		colIdade.setCellValueFactory(cellData -> cellData.getValue().idadeProperty());
		colDistancia.setCellValueFactory(cellData -> cellData.getValue().distanciaProperty());
	}
	
	@FXML
	private void selecionaProduto() {
		Corredor corredor = tableView.getSelectionModel().getSelectedItem();
		txtNome.setText(corredor.getNome());
		txtNumeroPeito.setText(String.format("%s", corredor.getNumeroPeito()));
		txtDistancia.setText(String.format("%s", corredor.getDistancia()));
		dataNascimento.setValue(corredor.getDataNascimento());
	}
	
	@FXML
	private void limpaTela() {
		txtNome.setText("");
		txtNumeroPeito.setText("");
		txtDistancia.setText("");
		dataNascimento.setValue(null);
	}
	
	private void mostraMensagem(String msg, AlertType tipoMensagem) {
		Alert alerta = new Alert(tipoMensagem);
		alerta.setHeaderText(null);
		alerta.setContentText(msg);
		alerta.show();
	}
}
