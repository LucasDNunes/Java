package model.aluno;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.cidade.Cidade;
import model.curso.Curso;

public interface AlunoService {
	
	public Aluno cadastrarAluno(Aluno aluno);
	
	public Aluno objectMap(TextField txtNome, TextField txtIdade, ComboBox<Cidade> cbCidade, ComboBox<Curso> cbCurso);
}
