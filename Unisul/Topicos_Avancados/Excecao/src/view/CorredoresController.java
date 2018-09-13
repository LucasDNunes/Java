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
	DatePicker dtDataNascimento;
	
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
	@FXML
	TableColumn<Corredor, String> colFaixaEtaria;
	
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
		corredor.setIdade(calculaIdade(dtDataNascimento.getValue()));
		corredor.setDistancia(Integer.parseInt(txtDistancia.getText()));
		corredor.setDataNascimento(dtDataNascimento.getValue());
		if (corredor.getIdade() > 20) {
			corredor.setFaixaEtaria(calculaFaixaEtaria(corredor.getIdade()));
		} else {
			throw new IllegalArgumentException("idade invalida");
		}
		this.corredores.add(corredor);
		
		tableView.setItems(FXCollections.observableArrayList(corredores));
		
		limpaTela();
	}
	
	private int calculaIdade(LocalDate dataNasc) {
		LocalDate dataHoje = LocalDate.now();
		long idade = ChronoUnit.YEARS.between(dataNasc, dataHoje);
		return (int) idade;
	}
	
	private String calculaFaixaEtaria(int idade) {
		String faixaEtaria;
			if (idade >= 20 && idade <= 29) {
				faixaEtaria = "20-29";
			} else if (idade >= 30 && idade <= 39) {
				faixaEtaria = "30-39";
			} else if (idade >= 40 && idade <= 49) {
				faixaEtaria = "40-49";
			} else if (idade >= 50 && idade <= 59) {
				faixaEtaria = "50-59";
			} else {
				faixaEtaria = "acima de 60";
			}
			return faixaEtaria;
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
		dtDataNascimento.setValue(corredor.getDataNascimento());
	}
	
	@FXML
	private void limpaTela() {
		txtNome.setText("");
		txtNumeroPeito.setText("");
		txtDistancia.setText("");
		dtDataNascimento.setValue(null);
	}
	
	private void mostraMensagem(String msg, AlertType tipoMensagem) {
		Alert alerta = new Alert(tipoMensagem);
		alerta.setHeaderText(null);
		alerta.setContentText(msg);
		alerta.show();
	}
}
