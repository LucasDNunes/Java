package model.atendimento.edicaoAtendimento;

import core.util.MensagemUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.atendimento.Atendimento;
import model.atendimento.AtendimentoRepository;
import model.estagiario.Estagiario;
import model.estagiario.EstagiarioRepository;
import model.sala.Sala;
import model.sala.SalaRepository;

import java.io.IOException;
import java.time.LocalTime;

public class EdicaoAtendimentoServiceImpl extends AtendimentoRepository implements EdicaoAtendimentoService{

    private SalaRepository salaRepository = new SalaRepository();
    private EstagiarioRepository estagiarioRepository = new EstagiarioRepository();

    @Override
    public void initialize(ComboBox<Sala> cbSala, ComboBox<Estagiario> cbEstagiario, Button btnAtualizar) {
        cbSala.getItems().addAll(salaRepository.findAll());
        cbEstagiario.getItems().addAll(estagiarioRepository.findAll());

        btnAtualizar.setOnAction(e ->{
            Stage stage = (Stage) cbEstagiario.getScene().getWindow();
            stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
        });
    }

    @Override
    public Atendimento objectMap(TextField txtHoraInicio, TextField txtHoraFim, ComboBox<Sala> cbSala, ComboBox<Estagiario> cbEstagiario, DatePicker dpData) {
        try {
            return Atendimento.builder()
                    .sala(cbSala.getSelectionModel().getSelectedItem())
                    .estagiario(cbEstagiario.getSelectionModel().getSelectedItem())
                    .horaAtendimentoInicio(LocalTime.of(Integer.parseInt(txtHoraInicio.getText()), 00))
                    .horaAtendimentoFim(LocalTime.of(Integer.parseInt(txtHoraFim.getText()), 00))
                    .data(dpData.getValue())
                    .build();
        } catch (Exception e) {
            MensagemUtils.mostraMensagem(e.getMessage(), Alert.AlertType.ERROR);
        }
        return null;
    }

    @Override
    public void atualizar(TextField txtHoraInicio, TextField txtHoraFim, ComboBox<Sala> cbSala, ComboBox<Estagiario> cbEstagiario, DatePicker dpData, Atendimento atendimentoPai) {
        Atendimento map = objectMap(txtHoraInicio, txtHoraFim, cbSala, cbEstagiario, dpData);
        atendimentoPai.setSala(map.getSala());
        atendimentoPai.setEstagiario(map.getEstagiario());
        atendimentoPai.setHoraAtendimentoInicio(map.getHoraAtendimentoInicio());
        atendimentoPai.setHoraAtendimentoFim(map.getHoraAtendimentoFim());
        atendimentoPai.setData(map.getData());
        salvar(atendimentoPai);
    }

    @Override
    public void voltarTelaPai(TextField txtNome) {
        try {
            FXMLLoader lo = new FXMLLoader(getClass().getResource("../../../view/cadastrosala/CadastroAtendimento.fxml"));
            lo.load();
        } catch (IOException e) {
            MensagemUtils.mostraMensagem(e.getMessage(), Alert.AlertType.ERROR);
        }

        Stage se = (Stage) txtNome.getScene().getWindow();
        se.close();
    }
}
