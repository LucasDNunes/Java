package model.estagiario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

import core.banco.Conexao;
import core.util.MensagemUtils;	
import javafx.scene.control.Alert.AlertType;

public class EstagiarioRepository {

	protected Optional<Estagiario> buscarPorId(Long id) {

		if (Objects.isNull(id)) {
			return Optional.empty();
		}

		String sqlSelect = "SELECT * FROM estagiario WHERE id=" + id;
		try (Connection conn = Conexao.getConexao()) {
			PreparedStatement preparedStatement = conn.prepareStatement(sqlSelect);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				return Optional.of(
						Estagiario.builder()
								.id(resultSet.getLong("id"))
								.nome((resultSet.getString("nome")))
								.build());
			}
		} catch (Exception e) {
			MensagemUtils.mostraMensagem(e.getMessage(), AlertType.ERROR);
		}

		return Optional.empty();

	}

	protected Estagiario salvar(Estagiario estagiario) {

		Optional<Estagiario> estagiarioExistente = buscarPorId(estagiario.getId());
		if (estagiarioExistente.isPresent()) {
			update(estagiario);
			return estagiario;
		}
		return salvarERetornarSalvo(estagiario);
	}

	protected boolean update(Estagiario estagiario){
		String sql = "UPDATE estagiario SET nome=?, semestre=? WHERE id=?";
		try (Connection conn = Conexao.getConexao()) {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, estagiario.getNome());
			preparedStatement.setInt(2, estagiario.getSemestre());
			preparedStatement.setInt(3, estagiario.getId().intValue());
			preparedStatement.execute();
			return true;
		} catch (Exception e) {
			MensagemUtils.mostraMensagem(e.getMessage(), AlertType.ERROR);
		}
		return false;
	}

	protected boolean excluir(Long id) {

		String sql = "DELETE FROM estagiario WHERE id=" + id.intValue();
		try (Connection conn = Conexao.getConexao()) {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.execute();
			return true;
		} catch (Exception e) {
			MensagemUtils.mostraMensagem(e.getMessage(), AlertType.ERROR);
		}
		return false;
	}

	private Estagiario salvarERetornarSalvo(Estagiario estagiario) {

		String sql = "insert into estagiario(nome,semestre) values(?,?)";

		try (Connection conn = Conexao.getConexao()) {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, estagiario.getNome());
			preparedStatement.setInt(2, estagiario.getSemestre());
			preparedStatement.executeUpdate();

			String sqlSelect = "SELECT * FROM estagiario ORDER BY id DESC LIMIT 1";
			preparedStatement = conn.prepareStatement(sqlSelect);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {

				return Estagiario.builder()
						.id(resultSet.getLong("id"))
						.nome(resultSet.getString("nome"))
						.semestre(resultSet.getInt("semestre"))
						.build();

			}

		} catch (Exception e) {
			MensagemUtils.mostraMensagem(e.getMessage(), AlertType.ERROR);
		}

		return null;
	}
	
	public ArrayList<Estagiario> findAll() {
		
		ArrayList<Estagiario> estagiarios = new ArrayList<>();
		
		String sqlSelect = "SELECT * FROM estagiario e ORDER BY e.nome";
		try(Connection conn = Conexao.getConexao()) {
			PreparedStatement preparedStatement = conn.prepareStatement(sqlSelect);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				estagiarios.add(Estagiario.builder()
						.id(resultSet.getLong("id"))
						.nome(resultSet.getString("nome"))
						.semestre(resultSet.getInt("semestre"))
						.build()); 
			}
			
			return estagiarios;
			
		} catch (Exception e) {
			MensagemUtils.mostraMensagem(e.getMessage(), AlertType.ERROR);
		}
		return estagiarios;
	}
}
