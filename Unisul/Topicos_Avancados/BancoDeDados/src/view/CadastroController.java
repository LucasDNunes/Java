package view;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javafx.beans.InvalidationListener;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.aluno.Aluno;
import model.aluno.AlunoServiceImpl;
import model.cidade.Cidade;
import model.curso.Curso;
import util.Conexao;

public class CadastroController {
	
	@FXML TextField txtNome;
	@FXML TextField txtIdade;
	@FXML TextField txtFiltro;
	
	@FXML ComboBox<Curso> cbCurso;
	@FXML ComboBox<Cidade> cbCidade;
	
	@FXML TableView<Object> tableView;
	@FXML TableColumn<Aluno, String> colNome;
	@FXML TableColumn<Aluno, Number> colIdade;
	@FXML TableColumn<Aluno, String> colCidade;
	@FXML TableColumn<Aluno, String> colCurso;
	
	private AlunoServiceImpl alunoService = new AlunoServiceImpl();
	private ArrayList<Aluno> alunos = new ArrayList<>();
	

	@FXML
	public void initialize() {
		alunoService.initialize(colNome,colIdade,colCidade,colCurso,cbCurso);
		preencheComboCidadeECurso();
		listarAlunos();
	}
	
	@FXML
	public void cadastrarAluno() {
		Aluno aluno = alunoService.objectMap(txtNome,txtIdade,cbCidade,cbCurso);
		Aluno salvo = alunoService.cadastrarAluno(aluno);
		alunos.add(salvo);
		tableView.setItems(FXCollections.observableArrayList(alunos));
	}
	
	@FXML
	public void filtrar() {
		if (txtFiltro.getText().equals("")) {
			tableView.setItems(FXCollections.observableArrayList(alunos));
		} else {
			tableView.setItems(FXCollections.observableArrayList(alunos.stream().
					filter(a -> a.getNome().getValue().startsWith(txtFiltro.getText())).collect(Collectors.toList())));
		}
		
	}
	
	
	protected void preencheComboCidadeECurso() {
		try(Connection conn = Conexao.getConexao()) {
			String sqlSelect = "SELECT * FROM cidade ORDER BY nome";
			PreparedStatement preparedStatement = conn.prepareStatement(sqlSelect);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			String sqlSelectCurso = "SELECT * FROM curso ORDER BY curso";
			PreparedStatement preparedStatementCurso = conn.prepareStatement(sqlSelectCurso);
			ResultSet resultSetCurso = preparedStatementCurso.executeQuery();
			while (resultSet.next()) {
				 Cidade cidade = Cidade.builder()
						.id(resultSet.getLong("cod"))
						.cidade(new SimpleStringProperty(resultSet.getString("nome")))
						.uf(resultSet.getString("uf"))
						.build();
				 
				 cbCidade.getItems().add(cidade);
			}
			while (resultSetCurso.next()) {
				Curso curso = Curso.builder()
						 .id(resultSetCurso.getLong("cod"))
						 .curso(new SimpleStringProperty(resultSetCurso.getString("curso")))
						 .build();
				 
				 cbCurso.getItems().add(curso);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private void listarAlunos() {
		tableView.getItems().clear();
		
		try(Connection conn = Conexao.getConexao()) {
			String sqlSelect = "SELECT a.cod as cod, a.nome as nome, a.idade as idade,a.curso as curso,"
					+ " cu.cod as codCurso, cu.curso as nomeCurso, c.cod as codCidade, c.nome as nomeCidade "
					+ "FROM aluno a JOIN cidade c ON a.cidade=c.cod JOIN curso cu ON a.curso=cu.cod "
					+ "ORDER BY a.nome";
			PreparedStatement preparedStatement = conn.prepareStatement(sqlSelect);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				alunos.add(Aluno.builder()
						.id(resultSet.getLong("cod"))
						.nome(new SimpleStringProperty(resultSet.getString("nome")))
						.idade(new SimpleIntegerProperty(resultSet.getInt("idade")))
						.cidade(Cidade.builder()
								.id(Long.parseLong(resultSet.getString("codCidade")))
								.cidade(new SimpleStringProperty(resultSet.getString("nomeCidade")))
								.build())
						.curso(Curso.builder()
								.id(resultSet.getLong("codCurso"))
								.curso(new SimpleStringProperty(resultSet.getString("nomeCurso")))
								.build())
						.build()); 
				 
			}
			
			tableView.setItems(FXCollections.observableArrayList(alunos));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
