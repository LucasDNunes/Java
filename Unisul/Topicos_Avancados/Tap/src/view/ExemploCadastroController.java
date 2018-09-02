package view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import model.Aluno;

public class ExemploCadastroController {
	
	@FXML
	TextField txtNome;
	@FXML
	TextField txtFiltro;
	
	@FXML
	RadioButton rdMasc;
	@FXML
	RadioButton rdFem;
	
	@FXML
	ComboBox<String> uf;
	
	@FXML
	DatePicker txtDataNascimento;
	
	@FXML
	CheckBox checkMatutino;
	@FXML
	CheckBox checkVespertino;
	@FXML
	CheckBox checkNoturno;
	
	@FXML
	TableView<Aluno> tbl;
	@FXML
	TableColumn<Aluno, String> colNome;
	@FXML
	TableColumn<Aluno, Number> colIdade;
	@FXML
	TableColumn<Aluno, String> colSexo;
	@FXML
	TableColumn<Aluno, String> colMatutino;
	@FXML
	TableColumn<Aluno, String> colVespertino;
	@FXML
	TableColumn<Aluno, String> colNoturno;
	
	private ArrayList<Aluno> alunos = new ArrayList<>();
	
	
	@FXML
	public void initialize() {
		inicializaComboUf();
		inicializaTbl();
	}
	
	private void inicializaTbl() {
		colNome.setCellValueFactory(cellData -> cellData.getValue().nomeProperty());
		colIdade.setCellValueFactory(cellData -> cellData.getValue().idadeProperty());
		colSexo.setCellValueFactory(cellData -> cellData.getValue().sexoProperty());
		colMatutino.setCellValueFactory(cellData -> cellData.getValue().matProperty().get()
				? new SimpleStringProperty("X") : new SimpleStringProperty(""));
		colVespertino.setCellValueFactory(cellData -> cellData.getValue().vesProperty().get()
				? new SimpleStringProperty("X") : new SimpleStringProperty(""));
		colNoturno.setCellValueFactory(cellData -> cellData.getValue().notProperty().get()
				? new SimpleStringProperty("X") : new SimpleStringProperty(""));
	}
	
	@FXML
	public void incluir() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		Aluno aluno = new Aluno();
		
		aluno.setNome(txtNome.getText());
		
		aluno.setSexo(rdMasc.isSelected() ? "M" : "F");
		
		aluno.setDataNasc(dtf.format(txtDataNascimento.getValue()));
		
		aluno.setMat(checkMatutino.isSelected());
		aluno.setVes(checkVespertino.isSelected());
		aluno.setNot(checkNoturno.isSelected());
		
		aluno.setUF(uf.getSelectionModel().getSelectedItem());
		
		aluno.setIdade(calculaIdade(txtDataNascimento.getValue()));
		
		alunos.add(aluno);
		
		tbl.setItems(FXCollections.observableArrayList(alunos));
	}
	
	
	private void inicializaComboUf() {
		uf.getItems().add("SC");
		uf.getItems().add("AC");
		uf.getItems().add("PR");
		uf.getItems().add("RJ");
		uf.getItems().add("SP");
		uf.getItems().add("BA");
		
		uf.getSelectionModel().select(0);
	}
	
	
	
	private int calculaIdade(LocalDate dataNasc) {
		LocalDate dataHoje = LocalDate.now();
		long idade = ChronoUnit.YEARS.between(dataNasc, dataHoje);
		return (int) idade;
	}
	
	@FXML
	private void filtrar() {
		if (txtFiltro.getText().equals("")) {
			tbl.setItems(FXCollections.observableArrayList(alunos));
		} else {
			ArrayList<Aluno> aux = new ArrayList<>();
			for (Aluno aluno : alunos) {
				if (aluno.getNome().startsWith(txtFiltro.getText().toLowerCase())) {
					aux.add(aluno);
				}
			}
			tbl.setItems(FXCollections.observableArrayList(aux));
		}
	}
	
	@FXML
	private void selecionaAluno() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		Aluno aluno = tbl.getSelectionModel().getSelectedItem();
		txtNome.setText(aluno.getNome());
		if (aluno.getSexo().equals("F")) {
			rdMasc.setSelected(false);
			rdFem.setSelected(true);
		} else if (aluno.getSexo().equals("M")) {
			rdMasc.setSelected(true);
			rdFem.setSelected(false);
		}
		
		uf.getSelectionModel().select(aluno.getUF());
		txtDataNascimento.setValue(LocalDate.parse(aluno.getDataNasc(), dtf));
		checkMatutino.setSelected(aluno.isMat());
		checkVespertino.setSelected(aluno.isVep());
		checkNoturno.setSelected(aluno.isNot());
	}
	
}
