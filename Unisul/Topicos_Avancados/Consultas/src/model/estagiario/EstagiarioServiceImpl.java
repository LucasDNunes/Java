package model.estagiario;


import java.util.List;
import java.util.stream.Collectors;

import core.util.MensagemUtils;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import view.estagiario.edicao.EdicaoController;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class EstagiarioServiceImpl extends EstagiarioRepository implements EstagiarioService {

	@Override
	public void initialize(TableView<Estagiario> tableView, TableColumn<Estagiario, String> colNome, TableColumn<Estagiario, Number> colSemestre, List<Estagiario> estagiarios) {
		inicarTableView(colNome, colSemestre);
		listarEstagiario(tableView, estagiarios);
	}

	@Override
	public Estagiario objectMap(TextField txtNome, TextField txtSemestre) {
		try {
			return Estagiario.builder()
					.nome(txtNome.getText())
					.semestre(Integer.parseInt(txtSemestre.getText()))
					.build();
		} catch (Exception e) {
			MensagemUtils.mostraMensagem(e.getMessage(), AlertType.ERROR);
		}
		return null;
	}
	
	@Override
	public void cadastrar(TextField txtNome, TextField txtSemestre, TableView<Estagiario> tableView, List<Estagiario> estagiarios) {
		Estagiario map = objectMap(txtNome, txtSemestre);
		Estagiario salvo = salvar(map);
		estagiarios.add(salvo);
		tableView.setItems(FXCollections.observableArrayList(estagiarios));
		limparTela(txtNome, txtSemestre);
		MensagemUtils.mostraMensagem("Estagiario Cadastrado!", AlertType.INFORMATION);
	}	
	
	@Override
	public void limparTela(TextField txtNome, TextField txtSemestre) {
		txtNome.setText("");
		txtSemestre.setText("");
	}

	private void inicarTableView(TableColumn<Estagiario, String> colNome, TableColumn<Estagiario, Number> colSemestre) {
		colNome.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getNome()));
		colSemestre.setCellValueFactory(c -> new SimpleIntegerProperty(c.getValue().getSemestre()));
	}
	
	@Override
	public void excluirSelecionado(Button btnExcluir, TableView<Estagiario> tableView, List<Estagiario> estagiarios) {
		Estagiario estagiario = tableView.getSelectionModel().getSelectedItem();
		boolean pergunta = MensagemUtils.mostraMensagemPergunta("Excluir Estagiario, "+ estagiario.getNome() + "?");
		
		if (pergunta) {
			excluir(estagiario.getId());
			estagiarios.remove(estagiario);
			tableView.setItems(FXCollections.observableArrayList(estagiarios));
		}
		
		btnExcluir.setDisable(true);
	}
	
	@Override
	public void editarSelecionado(Button btnEditar, TableView<Estagiario> tableView, List<Estagiario> estagiarios) {
		Estagiario estagiario = tableView.getSelectionModel().getSelectedItem();
		Stage stage = new Stage();
		try {
			
			FXMLLoader loader =  new FXMLLoader(getClass().getResource("../../view/estagiario/edicao/Edicao.fxml"));
			Parent root = loader.load();
			stage.setScene(new Scene(root));
			stage.initOwner(btnEditar.getScene().getWindow());
			stage.initModality(Modality.WINDOW_MODAL);
			stage.setResizable(false);
			stage.initStyle(StageStyle.UNDECORATED);
			
			EdicaoController edicaoController = loader.getController();
			edicaoController.atualizarEstagiario(estagiario);
			stage.show();
		} catch (Exception e) {
			MensagemUtils.mostraMensagem(e.getMessage(), AlertType.ERROR);
		}
		btnEditar.setDisable(true);
	}
	
	@Override
	public void editarOuexcluir(Button btnExcluir, Button btnEditar) {
		btnExcluir.setDisable(false);
		btnEditar.setDisable(false);
	}
	
	@Override
	public void filtrar(TextField txtFiltro, TableView<Estagiario> tableView, List<Estagiario> estagiarios) {
		if (txtFiltro.getText().equals("")) {
			tableView.setItems(FXCollections.observableArrayList(estagiarios));
		} else {
			
			tableView.setItems(FXCollections.observableArrayList(estagiarios.stream().filter(e -> e.getNome()
					.startsWith(txtFiltro.getText().toLowerCase()))
					.collect(Collectors.toList())));
		}
	}
	
	private void listarEstagiario(TableView<Estagiario> tableView, List<Estagiario> estagiarios) {
		
		estagiarios.clear();
		estagiarios.addAll(findAll());
		
		tableView.setItems(FXCollections.observableArrayList(estagiarios));
	}

}
