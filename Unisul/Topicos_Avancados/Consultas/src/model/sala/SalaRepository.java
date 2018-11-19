package model.sala;

import core.banco.Conexao;
import core.util.MensagemUtils;
import javafx.scene.control.Alert;
import model.estagiario.Estagiario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

public class SalaRepository {

    protected Optional<Sala> buscarPorId(Long id) {

        if (Objects.isNull(id)) {
            return Optional.empty();
        }

        String sqlSelect = "SELECT * FROM sala WHERE id=" + id;
        try (Connection conn = Conexao.getConexao()) {
            PreparedStatement preparedStatement = conn.prepareStatement(sqlSelect);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return Optional.of(
                        Sala.builder()
                                .id(resultSet.getLong("id"))
                                .nome((resultSet.getString("nome")))
                                .numero(resultSet.getInt("numero"))
                                .build());
            }
        } catch (Exception e) {
            MensagemUtils.mostraMensagem(e.getMessage(), Alert.AlertType.ERROR);
        }

        return Optional.empty();

    }

    protected Sala salvar(Sala sala) {

        Optional<Sala> salaExistente = buscarPorId(sala.getId());
        if (salaExistente.isPresent()) {
            update(sala);
            return sala;
        }
        return salvarERetornarSalvo(sala);
    }

    protected boolean update(Sala sala){
        String sql = "UPDATE sala SET nome=?, numero=? WHERE id=?";
        try (Connection conn = Conexao.getConexao()) {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, sala.getNome());
            preparedStatement.setInt(2, sala.getNumero());
            preparedStatement.setInt(3, sala.getId().intValue());
            preparedStatement.execute();
            return true;
        } catch (Exception e) {
            MensagemUtils.mostraMensagem(e.getMessage(), Alert.AlertType.ERROR);
        }
        return false;
    }

    protected boolean excluir(Long id) {

        String sql = "DELETE FROM sala WHERE id=" + id.intValue();
        try (Connection conn = Conexao.getConexao()) {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.execute();
            return true;
        } catch (Exception e) {
            MensagemUtils.mostraMensagem(e.getMessage(), Alert.AlertType.ERROR);
        }
        return false;
    }

    private Sala salvarERetornarSalvo(Sala sala) {

        String sql = "insert into sala(nome,numero) values(?,?)";

        try (Connection conn = Conexao.getConexao()) {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, sala.getNome());
            preparedStatement.setInt(2, sala.getNumero());
            preparedStatement.executeUpdate();

            String sqlSelect = "SELECT * FROM sala ORDER BY id DESC LIMIT 1";
            preparedStatement = conn.prepareStatement(sqlSelect);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                return Sala.builder()
                        .id(resultSet.getLong("id"))
                        .nome(resultSet.getString("nome"))
                        .numero(resultSet.getInt("numero"))
                        .build();

            }

        } catch (Exception e) {
            MensagemUtils.mostraMensagem(e.getMessage(), Alert.AlertType.ERROR);
        }

        return null;
    }

    public ArrayList<Sala> findAll() {

        ArrayList<Sala> salas = new ArrayList<>();

        String sqlSelect = "SELECT * FROM sala e ORDER BY e.nome";
        try(Connection conn = Conexao.getConexao()) {
            PreparedStatement preparedStatement = conn.prepareStatement(sqlSelect);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                salas.add(Sala.builder()
                        .id(resultSet.getLong("id"))
                        .nome(resultSet.getString("nome"))
                        .numero(resultSet.getInt("numero"))
                        .build());
            }

            return salas;

        } catch (Exception e) {
            MensagemUtils.mostraMensagem(e.getMessage(), Alert.AlertType.ERROR);
        }
        return salas;
    }
}
