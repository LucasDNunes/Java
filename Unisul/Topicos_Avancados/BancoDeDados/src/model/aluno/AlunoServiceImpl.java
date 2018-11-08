package model.aluno;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import model.cidade.Cidade;
import model.curso.Curso;

public class AlunoServiceImpl implements AlunoService{
	
	 AlunoRepository alunoRepository =  new AlunoRepository();
	
	@Override
	public Aluno cadastrarAluno(Aluno aluno) {
		return alunoRepository.salvar(aluno);
	}
	
	@Override
	public Aluno objectMap(TextField txtNome, TextField txtIdade, ComboBox<Cidade> cbCidade, ComboBox<Curso> cbCurso) {
		try {
			return Aluno.builder()
					.nome(new SimpleStringProperty(txtNome.getText()))
					.idade(new SimpleIntegerProperty(Integer.parseInt(txtIdade.getText())))
					.cidade(cbCidade.getSelectionModel().getSelectedItem())
					.curso(cbCurso.getSelectionModel().getSelectedItem())
					.build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void initialize(TableColumn<Aluno, String> colNome, TableColumn<Aluno, Number> colIdade, TableColumn<Aluno, String> colCidade, TableColumn<Aluno, String> colCurso, ComboBox<Curso> cbCurso) {
		inicializaTable(colNome, colIdade,colCidade,colCurso);
		//inicializaComboUf(chCidade);
	}
	
	private void inicializaTable(TableColumn<Aluno, String> colNome, TableColumn<Aluno, Number> colIdade, TableColumn<Aluno, String> colCidade, TableColumn<Aluno, String> colCurso) {
		colNome.setCellValueFactory(cellData -> cellData.getValue().getNome());
		colIdade.setCellValueFactory(cellData -> cellData.getValue().getIdade());
		colCidade.setCellValueFactory(cellData -> cellData.getValue().getCidade().getCidade());
		colCurso.setCellValueFactory(cellData -> cellData.getValue().getCurso().getCurso());
	}
	
	private void inicializaComboUf(ComboBox<String> chUf) {
		chUf.getItems().add("SC");
		chUf.getItems().add("AC");
		chUf.getItems().add("PR");
		chUf.getItems().add("RJ");
		chUf.getItems().add("SP");
		chUf.getItems().add("BA");
		
		chUf.getSelectionModel().select(0);
	}
}
