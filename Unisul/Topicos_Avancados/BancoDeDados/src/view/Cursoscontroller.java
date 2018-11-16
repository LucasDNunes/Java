package view;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.aluno.Aluno;
import model.cidade.Cidade;
import model.curso.Curso;
import util.Conexao;

public class Cursoscontroller {
	
	@FXML ComboBox<Curso> cbCusos;
	
	@FXML TableView<Aluno> tableView;
	@FXML TableColumn<Aluno, String> colNome;
	@FXML TableColumn<Aluno, Number> colIdade;
	@FXML TableColumn<Aluno, String> colCidade;
	
	private List<Aluno> alunos = new ArrayList<>();
	
	public void initialize(){
		colNome.setCellValueFactory(cellData -> cellData.getValue().getNome());
		colIdade.setCellValueFactory(cellData -> cellData.getValue().getIdade());
		colCidade.setCellValueFactory(cellData -> cellData.getValue().getCidade().getCidade());
	}
	
	
	private void buscarAluno() {
		tableView.getItems().clear();
		
		Curso curso = cbCusos.getSelectionModel().getSelectedItem();
		
		try(Connection conn = Conexao.getConexao()) {
			String sqlSelect = "SELECT a.cod as cod, a.nome as nome, a.idade as idade,a.curso as curso,"
					+ " cu.cod as codCurso, cu.curso as nomeCurso, c.cod as codCidade, c.nome as nomeCidade "
					+ "FROM aluno a JOIN cidade c ON a.cidade=c.cod JOIN curso cu ON a.curso=cu.cod "
					+ "WHERE curso = ?"
					+ "ORDER BY a.nome";
			PreparedStatement preparedStatement = conn.prepareStatement(sqlSelect);
			preparedStatement.setInt(1, curso.getId().intValue());
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
						.curso(curso)
						.build()); 
				 
			}
			
			tableView.setItems(FXCollections.observableArrayList(alunos));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
