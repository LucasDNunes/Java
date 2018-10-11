package model.aluno;

import java.util.List;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public interface AlunoService {
	
	public void inicializaTableView(TableColumn<Aluno, String> colNome, TableColumn<Aluno, Number> colSemestre, TableColumn<Aluno, String> colCurso);
	
	public void lerArquivo(List<Aluno> alunos, TableView<Aluno> tableView);
	
	public void gravar(TextField txtNome, TextField txtSemestre, TextField txtCurso);
}
