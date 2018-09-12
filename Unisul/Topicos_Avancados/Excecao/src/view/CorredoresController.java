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
import model.Corredores;

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
	TableView<Corredores> tableView;
	@FXML
	TableColumn<Corredores, String> colNome;
	@FXML
	TableColumn<Corredores, Number> colNumeroPeito;
	@FXML
	TableColumn<Corredores, Number> colIdade;
	@FXML
	TableColumn<Corredores, Number> colDistancia;
	
	private ArrayList<Corredores> corredores = new ArrayList<>();
	
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
		Corredores corredor = new Corredores();
		corredor.setNome(txtNome.getText());
		corredor.setNumeroPeito(Integer.parseInt(txtNumeroPeito.getText()));
		corredor.setIdade(calculaIdade(dataNascimento.getValue()));
		corredor.setDistancia(Integer.parseInt(txtDistancia.getText()));
				
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
		Corredores corredor = tableView.getSelectionModel().getSelectedItem();
		txtNome.setText(corredor.getNome());
		txtNumeroPeito.setText(String.format("%s", corredor.getNumeroPeito()));
		txtDistancia.setText(String.format("%s", corredor.getDistancia()));
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
