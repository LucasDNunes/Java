package view;

import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.aluno.Aluno;
import model.aluno.AlunoServiceImpl;

public class CadastroAlunoController {
	@FXML TextField txtNome;
	@FXML TextField txtSemestre;
	@FXML TextField txtCurso;
	
	@FXML TableView<Aluno> tableView;
	@FXML TableColumn<Aluno, String> colNome;
	@FXML TableColumn<Aluno, Number> colSemestre;
	@FXML TableColumn<Aluno, String> colCurso;
	
	AlunoServiceImpl alunoServiceImpl =  new AlunoServiceImpl();
	
	private List<Aluno> alunos = new ArrayList<>();
	
	@FXML
	public void initialize() {
		alunoServiceImpl.inicializaTableView(colNome,colSemestre,colCurso);
		alunoServiceImpl.lerArquivo(alunos, tableView);
	}
	
	@FXML
	public void cadastrar() {
		alunoServiceImpl.gravar(txtNome,txtSemestre,txtCurso);
		alunoServiceImpl.lerArquivo(alunos, tableView);
	}
	
}
