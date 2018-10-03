package view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import model.Competicao;

public class SimuladoQuestao2Controller {
	
	@FXML TextField txtNome;
	@FXML TextField txtDistancia;
	@FXML TextField txtColocacao;
	@FXML TextField txtMelhorColocacao;
	
	@FXML DatePicker datePickerData;
	
	@FXML TableView<Competicao> tableView;
	@FXML TableColumn<Competicao, String> colNome;
	@FXML TableColumn<Competicao, String> colData;
	@FXML TableColumn<Competicao, String> colDistancia;
	@FXML TableColumn<Competicao, Number> colColocacao;
	
	private List<Competicao> competicoes = new ArrayList<>();
	
	@FXML
	public void initialize() {
		inicializaTbl();
		removeCompeticao();
	}
	
	private void removeCompeticao() {
		tableView.setOnMouseClicked(e -> {
			 if(e.getClickCount()>1)
		        {
		            selecionarCompeticao();
		            
		            competicoes.remove(tableView.getSelectionModel().getSelectedItem());
		    		tableView.setItems(FXCollections.observableArrayList(competicoes));
		    		txtMelhorColocacao.setText(calculaColocacao());
		        }
		});
		
	}

	private void inicializaTbl() {
		colNome.setCellValueFactory(cellData -> cellData.getValue().nomeProperty());
		colData.setCellValueFactory(cellData -> cellData.getValue().dataProperty());
		colDistancia.setCellValueFactory(cellData -> cellData.getValue().distanciaProperty().asString().concat(" Km"));
		colColocacao.setCellValueFactory(cellData -> cellData.getValue().colocacaoProperty());
	}
	
	@FXML
	public void incluir() {
		
		try {
			inserirCompreticao();
		}catch (NumberFormatException e) {
			mostraMensagem("Campo 'Distancia' e 'Colocação' deve ser numeros interiros", AlertType.INFORMATION);
		}catch (NullPointerException e) {
			mostraMensagem("Preencha todos os campos", AlertType.INFORMATION);
		} catch (Exception e) {
			mostraMensagem("erro desconhecido", AlertType.INFORMATION);
		}
		
		limparTextField();
	}
	
	private void limparTextField() {
		txtNome.setText("");
		txtColocacao.setText("");
		txtDistancia.setText("");
		datePickerData.setValue(null);
	}
	
	private void inserirCompreticao() {
		DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		Competicao competicao =  new Competicao();
		
		competicao.setNome(txtNome.getText());
		competicao.setDistancia(Double.parseDouble(txtDistancia.getText()));
		competicao.setData(formatoData.format(datePickerData.getValue()));
		competicao.setColocacao(Integer.parseInt(txtColocacao.getText()));
		
		competicoes.add(competicao);
		
		tableView.setItems(FXCollections.observableArrayList(competicoes));
		
		txtMelhorColocacao.setText(calculaColocacao());
	}

	private String calculaColocacao() {
		Integer melhotColocacao = 9999;
		
		for (Competicao c : competicoes) {
			if (c.getColocacao() < melhotColocacao ) {
				melhotColocacao = c.getColocacao();
			}
		}
		
		return melhotColocacao.equals(9999) ? null : String.format("%d", melhotColocacao );
	}
	
	@FXML
	public void selecionarCompeticao() {
		DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		Competicao competicao = tableView.getSelectionModel().getSelectedItem();
		
		txtNome.setText(competicao.getNome());
		txtColocacao.setText(String.format("%d", competicao.getColocacao()));
		txtDistancia.setText(String.format("%s", competicao.getDistancia()));
		datePickerData.setValue(LocalDate.parse(competicao.getData(), formatoData));
		
	}
	
	private void mostraMensagem(String msg, AlertType tipoMensagem) {
		Alert alerta = new Alert(tipoMensagem);
		alerta.setHeaderText(null);
		alerta.setContentText(msg);
		alerta.show();
	}
}
