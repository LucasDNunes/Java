package model.aluno;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class AlunoServiceImpl extends Aluno implements AlunoService{

	@Override
	public void inicializaTableView(TableColumn<Aluno, String> colNome, TableColumn<Aluno, Number> colSemestre,
			TableColumn<Aluno, String> colCurso) {
		
		colNome.setCellValueFactory(cellData -> cellData.getValue().nomeProperty());
		colSemestre.setCellValueFactory(cellData -> cellData.getValue().semestreProperty());
		colCurso.setCellValueFactory(cellData -> cellData.getValue().cursoProperty());
	}

	@Override
	public void lerArquivo(List<Aluno> alunos, TableView<Aluno> tableView) {
		
		alunos.clear();
	
		try(
				FileReader fileReader = new FileReader("alunos.txt");
				BufferedReader bufferedReader = new BufferedReader(fileReader)
						) {
			
			
			String linha="";
			
			while ((linha = bufferedReader.readLine()) != null) {
				String[] linhas = linha.split(",");
				Aluno aluno = new Aluno();
				aluno.setNome(linhas[0]);
				aluno.setSemestre(Integer.parseInt(linhas[1]));
				aluno.setCurso(linhas[2]);
				alunos.add(aluno);
			}
			
			tableView.setItems(FXCollections.observableArrayList(alunos));
			
		} catch (Exception e) {
			e.getMessage();
		}
	}

	@Override
	public void gravar(TextField txtNome, TextField txtSemestre, TextField txtCurso) {
		try (
				FileWriter fileWriter = new FileWriter("alunos.txt", true);
				BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)
						) {
			
			Aluno aluno = new Aluno();
			aluno.setNome(txtNome.getText());
			aluno.setSemestre(Integer.parseInt(txtSemestre.getText()));
			aluno.setCurso(txtCurso.getText());
			
			bufferedWriter.append(aluno.getNome() +","+aluno.getSemestre()+","+aluno.getCurso()+"\n");
			
		} catch (Exception e) {
			e.getMessage();
		}
	}

}
