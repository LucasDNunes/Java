package view;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import model.Funcionario;

public class PrincipalController {

	@FXML
	ComboBox<String> cbFuncionario;

	@FXML
	TextField txtQuantidadeProdutos;
	@FXML
	TextField txtTempoPorUnidade;

	@FXML
	TableView<Funcionario> tableView;
	@FXML
	TableColumn<Funcionario, String> colNome;
	@FXML
	TableColumn<Funcionario, Number> colQuantidade;
	@FXML
	TableColumn<Funcionario, Number> colTempo;
	
	@FXML ProgressBar barraProgressoJoao;
	@FXML ProgressBar barraProgressoJose;
	@FXML ProgressBar barraProgressoPaulo;

	private List<Funcionario> funcionarios = new ArrayList<>();

	@FXML
	public void initialize() {
		iniciaComboBoxFuncionarios();
		iniciaTabela();
	}

	private void iniciaTabela() {
		colNome.setCellValueFactory(cellData -> cellData.getValue().nomeProperty());
		colQuantidade.setCellValueFactory(cellData -> cellData.getValue().quantidadeProdutosProperty());
		colTempo.setCellValueFactory(cellData -> cellData.getValue().tempoProperty());
	}

	private void iniciaComboBoxFuncionarios() {
		cbFuncionario.getItems().add("João");
		cbFuncionario.getItems().add("José");
		cbFuncionario.getItems().add("Paulo");

		cbFuncionario.getSelectionModel().select(0);
	}

	@FXML
	public void incluir() {
		try {
			isExisteFuncionario(cbFuncionario.getSelectionModel().getSelectedItem());
			inserirFuncionario();
		} catch (NumberFormatException e) {
			mostrarMensagem("Campo 'Quantidade' e 'Tempo' deve ser numeros interiros", AlertType.INFORMATION);
		} catch (NullPointerException e) {
			mostrarMensagem("Preencha todos os campos", AlertType.INFORMATION);
		} catch (IllegalArgumentException e) {
			mostrarMensagem("Funcioario já cadastrado!", AlertType.INFORMATION);
		} catch (Exception e) {
			mostrarMensagem("erro desconhecido", AlertType.INFORMATION);
		}

		limparTextField();
	}
	
	@FXML
	public void iniciarProducao() {
		for (Funcionario f : funcionarios) {
 			Funcionario funcionario = f;
			new Thread(funcionario).start();
		}
	}

	private void isExisteFuncionario(String nome) {
		for (Funcionario f : funcionarios) {
			if (f.getNome().equals(nome)) {
				throw new IllegalArgumentException();
			}
		}
	}

	private void inserirFuncionario() {
		Funcionario funcionario = new Funcionario();

		funcionario.setNome(cbFuncionario.getValue());
		funcionario.setQuantidadeProdutos(Integer.parseInt(txtQuantidadeProdutos.getText()));
		funcionario.setTempo(Integer.parseInt(txtTempoPorUnidade.getText()));
		
		if (cbFuncionario.getValue().equals(cbFuncionario.getItems().get(0))) {
			funcionario.setBarraTempo(barraProgressoJoao);
		} else if (cbFuncionario.getValue().equals(cbFuncionario.getItems().get(1))) {
			funcionario.setBarraTempo(barraProgressoJose);
		} else if (cbFuncionario.getValue().equals(cbFuncionario.getItems().get(2))) {
			funcionario.setBarraTempo(barraProgressoPaulo);
		}

		funcionarios.add(funcionario);

		tableView.setItems(FXCollections.observableArrayList(funcionarios));
	}

	private void limparTextField() {
		cbFuncionario.getSelectionModel().select(0);
		txtQuantidadeProdutos.setText(null);
		txtTempoPorUnidade.setText(null);

	}

	private void mostrarMensagem(String msg, AlertType tipoMensagem) {
		Alert alerta = new Alert(tipoMensagem);
		alerta.setHeaderText(null);
		alerta.setContentText(msg);
		alerta.show();
	}
}
