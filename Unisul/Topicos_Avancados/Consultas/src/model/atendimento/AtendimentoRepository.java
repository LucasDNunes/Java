package model.atendimento;

import core.banco.Conexao;
import core.util.MensagemUtils;
import javafx.scene.control.Alert;
import model.estagiario.Estagiario;
import model.sala.Sala;

import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

public class AtendimentoRepository {

    protected Optional<Atendimento> buscarPorId(Long id) {

        if (Objects.isNull(id)) {
            return Optional.empty();
        }

        String sqlSelect = "SELECT a.id as id, a.data as data, a.'hora-inicio' as horaIni, a.'hora-fim' as horaFim," +
                " s.id as salaId, s.nome as nomeSala, s.numero as numeroSala, e.id as estagiarioId, e.nome as nomeEstagiario, " +
                " e.semestre as semestreEstagiario " +
                "FROM atendimento a JOIN sala s ON a.sala=s.id JOIN estagiario e ON a.estagiario=e.id " +
                "WHERE a.id = ?";

        try (Connection conn = Conexao.getConexao()) {
            PreparedStatement preparedStatement = conn.prepareStatement(sqlSelect);
            preparedStatement.setInt(1, id.intValue());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return Optional.of(
                        Atendimento.builder()
                                .id(resultSet.getLong("id"))
                                .sala(Sala.builder()
                                        .id(resultSet.getLong("salaId"))
                                        .nome(resultSet.getString("nomeSala"))
                                        .numero(resultSet.getInt("numeroSala"))
                                        .build())
                                .estagiario(Estagiario.builder()
                                        .id(resultSet.getLong("estagiarioId"))
                                        .nome(resultSet.getString("nomeEstagiario"))
                                        .semestre(resultSet.getInt("semestreEstagiario"))
                                        .build())
                                .data(resultSet.getDate("data").toLocalDate())
                                .horaAtendimentoInicio(resultSet.getTime("horaIni").toLocalTime())
                                .horaAtendimentoFim(resultSet.getTime("horaFim").toLocalTime())
                                .build());
            }
        } catch (Exception e) {
            MensagemUtils.mostraMensagem(e.getMessage(), Alert.AlertType.ERROR);
        }

        return Optional.empty();

    }

    protected Atendimento salvar(Atendimento atendimento) {

        Optional<Atendimento> atendimentoExistente = buscarPorId(atendimento.getId());
        if (atendimentoExistente.isPresent()) {
            excluir(atendimentoExistente.get().getId());
            return salvarERetornarSalvo(atendimento);
        }
        return salvarERetornarSalvo(atendimento);
    }

    protected boolean update(Atendimento atendimento){

        String sql = "UPDATE atendimento SET sala=?, data=?, hora-inicio=?, hora-fim=?, estagiario=?, WHERE id=?";
        try (Connection conn = Conexao.getConexao()) {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, atendimento.getSala().getId().intValue());
            preparedStatement.setDate(2, Date.valueOf(atendimento.getData()));
            preparedStatement.setTime(3, Time.valueOf(atendimento.getHoraAtendimentoInicio()));
            preparedStatement.setTime(4, Time.valueOf(atendimento.getHoraAtendimentoFim()));
            preparedStatement.setInt(5, atendimento.getEstagiario().getId().intValue());
            preparedStatement.setInt(6, atendimento.getId().intValue());
            preparedStatement.execute();
            return true;
        } catch (Exception e) {
            MensagemUtils.mostraMensagem(e.getMessage(), Alert.AlertType.ERROR);
        }
        return false;
    }

    protected boolean excluir(Long id) {


        String sql = "DELETE FROM atendimento WHERE id=" + id.intValue();
        try (Connection conn = Conexao.getConexao()) {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.execute();
            return true;
        } catch (Exception e) {
            MensagemUtils.mostraMensagem(e.getMessage(), Alert.AlertType.ERROR);
        }
        return false;
    }

    private Atendimento salvarERetornarSalvo(Atendimento atendimento) {

        String sql = "insert into atendimento(sala,data,'hora-inicio', 'hora-fim',estagiario ) values(?,?,?,?,?)";

        try (Connection conn = Conexao.getConexao()) {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, atendimento.getSala().getId().intValue());
            preparedStatement.setDate(2, Date.valueOf(atendimento.getData()));
            preparedStatement.setTime(3, Time.valueOf(atendimento.getHoraAtendimentoInicio()));
            preparedStatement.setTime(4, Time.valueOf(atendimento.getHoraAtendimentoFim()));
            preparedStatement.setInt(5, atendimento.getEstagiario().getId().intValue());
            preparedStatement.executeUpdate();

            String sqlSelect = "SELECT a.id as id, a.data as data, a.'hora-inicio' as horaIni, a.'hora-fim' as horaFim," +
                    " s.id as salaId, s.nome as nomeSala, s.numero as numeroSala, e.id as estagiarioId, e.nome as nomeEstagiario, " +
                    " e.semestre as semestreEstagiario " +
                    "FROM atendimento a JOIN sala s ON a.sala=s.id JOIN estagiario e ON a.estagiario=e.id " +
                    "ORDER BY a.id DESC LIMIT 1";
            preparedStatement = conn.prepareStatement(sqlSelect);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                return Atendimento.builder()
                        .id(resultSet.getLong("id"))
                        .sala(Sala.builder()
                                .id(resultSet.getLong("salaId"))
                                .nome(resultSet.getString("nomeSala"))
                                .numero(resultSet.getInt("numeroSala"))
                                .build())
                        .estagiario(Estagiario.builder()
                                .id(resultSet.getLong("estagiarioId"))
                                .nome(resultSet.getString("nomeEstagiario"))
                                .semestre(resultSet.getInt("semestreEstagiario"))
                                .build())
                        .data(resultSet.getDate("data").toLocalDate())
                        .horaAtendimentoInicio(resultSet.getTime("horaIni").toLocalTime())
                        .horaAtendimentoFim(resultSet.getTime("horaFim").toLocalTime())
                        .build();

            }

        } catch (Exception e) {
            MensagemUtils.mostraMensagem(e.getMessage(), Alert.AlertType.ERROR);
        }

        return null;
    }

    public ArrayList<Atendimento> findAll() {

        ArrayList<Atendimento> atendimentos = new ArrayList<>();

        String sqlSelect = "SELECT a.id as id, a.data as data, a.'hora-inicio' as horaIni, a.'hora-fim' as horaFim," +
                " s.id as salaId, s.nome as nomeSala, s.numero as numeroSala, e.id as estagiarioId, e.nome as nomeEstagiario, " +
                " e.semestre as semestreEstagiario " +
                "FROM atendimento a JOIN sala s ON a.sala=s.id JOIN estagiario e ON a.estagiario=e.id " +
                "ORDER BY a.id";
        try (Connection conn = Conexao.getConexao()) {
            PreparedStatement preparedStatement = conn.prepareStatement(sqlSelect);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                atendimentos.add( Atendimento.builder()
                        .id(resultSet.getLong("id"))
                        .sala(Sala.builder()
                                .id(resultSet.getLong("salaId"))
                                .nome(resultSet.getString("nomeSala"))
                                .numero(resultSet.getInt("numeroSala"))
                                .build())
                        .estagiario(Estagiario.builder()
                                .id(resultSet.getLong("estagiarioId"))
                                .nome(resultSet.getString("nomeEstagiario"))
                                .semestre(resultSet.getInt("semestreEstagiario"))
                                .build())
                        .data(resultSet.getDate("data").toLocalDate())
                        .horaAtendimentoInicio(resultSet.getTime("horaIni").toLocalTime())
                        .horaAtendimentoFim(resultSet.getTime("horaFim").toLocalTime())
                        .build());
            }

            return atendimentos;

        } catch (Exception e) {
            MensagemUtils.mostraMensagem(e.getMessage(), Alert.AlertType.ERROR);
        }
        return atendimentos;
    }
}
