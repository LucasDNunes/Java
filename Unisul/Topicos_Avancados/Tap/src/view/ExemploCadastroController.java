package view;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Pessoa;

public class ExemploCadastroController {
	
	@FXML TextField txtNome;
	@FXML TextField txtFiltro;
	
	@FXML RadioButton rdMasc;
	@FXML RadioButton rdFem;
	
	@FXML ComboBox<String> uf;
	
	@FXML DatePicker txtDataNascimento;
	
	@FXML CheckBox checkMatutino;
	@FXML CheckBox checkVespertino;
	@FXML CheckBox checkNoturno;
	
	@FXML TableView<Pessoa> tbl;
	@FXML TableColumn<Pessoa, String> colNome;
	@FXML TableColumn<Pessoa, String> colIdade;
	@FXML TableColumn<Pessoa, String> colSexo;
	@FXML TableColumn<Pessoa, String> colTurno;
	
	@FXML
	public void initialize() {
		uf.getItems().add("SC");
		uf.getItems().add("AC");
		uf.getItems().add("PR");
		uf.getItems().add("RJ");
		uf.getItems().add("SP");
		
		uf.getSelectionModel().select(0);
	}
	
	
}
