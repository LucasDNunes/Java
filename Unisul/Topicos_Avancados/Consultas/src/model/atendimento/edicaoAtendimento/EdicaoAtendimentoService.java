package model.atendimento.edicaoAtendimento;

import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import model.atendimento.Atendimento;
import model.estagiario.Estagiario;
import model.sala.Sala;

public interface EdicaoAtendimentoService {

    void initialize(ComboBox<Sala> cbSala, ComboBox<Estagiario> cbEstagiario);

    Atendimento objectMap(TextField txtNome, TextField txtSemestre, ComboBox<Sala> cbSala, ComboBox<Estagiario> cbEstagiario, DatePicker dpData);

    void atualizar(TextField txtHoraInicio, TextField txtHoraFim, ComboBox<Sala> cbSala, ComboBox<Estagiario> cbEstagiario, DatePicker dpData, Atendimento atendimentoPai);

    void voltarTelaPai(TextField txtNome);
}
