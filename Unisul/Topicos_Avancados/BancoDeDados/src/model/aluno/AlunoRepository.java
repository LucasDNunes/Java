package model.aluno;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import util.Conexao;

public class AlunoRepository {
	
	protected Aluno salvar(Aluno aluno) {
		try (Connection conn = Conexao.getConexao()){
			
			String sql = "insert into aluno(nome,idade,cidade) values(?,?,?)";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, aluno.getNome().getValue());
			preparedStatement.setInt(2, aluno.getIdade().getValue());
			preparedStatement.setInt(3, aluno.getCidade().getId().intValue());
			preparedStatement.setInt(4, aluno.getCurso().getId().intValue());
			preparedStatement.executeUpdate();
			
			String sqlSelect = "SELECT * FROM aluno ORDER BY cod DESC LIMIT 1";
			preparedStatement = conn.prepareStatement(sqlSelect);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Long cod = resultSet.getLong("cod");
				String nome = resultSet.getString("nome");
				Integer idade = resultSet.getInt("idade");
				Long cidade = resultSet.getLong("cidade");
				Long curso = resultSet.getLong("curso");
				
				return Aluno.builder()
						.id(cod)
						.nome(new SimpleStringProperty(nome))
						.idade(new SimpleIntegerProperty(idade))
//						.cidade(cidade)
//						.curso(curso)
						.build();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	protected Aluno findById(Long id) {
		try(Connection conn = Conexao.getConexao()) {
			String sqlSelect = "SELECT * FROM aluno WHERE cod="+id;
			PreparedStatement preparedStatement = conn.prepareStatement(sqlSelect);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Long cod = resultSet.getLong("cod");
				String nome = resultSet.getString("nome");
				Integer idade = resultSet.getInt("idade");
				Long cidade = resultSet.getLong("cidade");
				Long curso = resultSet.getLong("curso");
				
				return Aluno.builder()
						.id(cod)
						.nome(new SimpleStringProperty(nome))
						.idade(new SimpleIntegerProperty(idade))
//						.cidade(cidade)
//						.curso(curso)
						.build();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
}
