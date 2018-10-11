package view;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import aluno.Aluno;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class CadastroAlunoController {
	@FXML TextField txtNome;
	@FXML TextField txtSemestre;
	@FXML TextField txtCurso;
	
	@FXML TableView<Aluno> tableView;
	@FXML TableColumn<Aluno, String> colNome;
	@FXML TableColumn<Aluno, Number> colSemestre;
	@FXML TableColumn<Aluno, String> colCurso;
	
	private List<Aluno> alunos = new ArrayList<>();
	
	@FXML
	public void initialize() {
		inicializaTableView();
		lerArquivo();
	}
	
	@FXML
	public void cadastrar() {
		gravar();
		lerArquivo();
	}
	
	private void gravar() {
		try {
			Aluno aluno = new Aluno();
			aluno.setNome(txtNome.getText());
			aluno.setSemestre(Integer.parseInt(txtSemestre.getText()));
			aluno.setCurso(txtCurso.getText());
			
			FileWriter fileWriter = new FileWriter("alunos.txt", true);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			
			bufferedWriter.append(aluno.getNome() +","+aluno.getSemestre()+","+aluno.getCurso()+"\n");
			bufferedWriter.close();
			fileWriter.close();
		} catch (Exception e) {
			e.getMessage();
		}
	}
	
	private void lerArquivo() {
		alunos.clear();
		try {
			FileReader fileReader = new FileReader("alunos.txt");
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			String linha="";
			
			while ((linha = bufferedReader.readLine()) != null) {
				String[] linhas = linha.split(",");
				Aluno aluno = new Aluno();
				aluno.setNome(linhas[0]);
				aluno.setSemestre(Integer.parseInt(linhas[1]));
				aluno.setCurso(linhas[2]);
				alunos.add(aluno);
			}
			
			bufferedReader.close();
			fileReader.close();
			tableView.setItems(FXCollections.observableArrayList(alunos));
			
		} catch (Exception e) {
			e.getMessage();
		}
	}

	private void inicializaTableView() {
		colNome.setCellValueFactory(cellData -> cellData.getValue().nomeProperty());
		colSemestre.setCellValueFactory(cellData -> cellData.getValue().semestreProperty());
		colCurso.setCellValueFactory(cellData -> cellData.getValue().cursoProperty());
	}
	
}
