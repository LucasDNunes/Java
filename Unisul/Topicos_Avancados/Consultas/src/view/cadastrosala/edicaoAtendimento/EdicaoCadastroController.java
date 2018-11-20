package view.cadastrosala.edicaoAtendimento;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import model.atendimento.Atendimento;
import model.atendimento.edicaoAtendimento.EdicaoAtendimentoServiceImpl;
import model.estagiario.Estagiario;
import model.sala.Sala;

public class EdicaoCadastroController {

    @FXML private TextField txtHoraInicio;
    @FXML private TextField txtHoraFim;

    @FXML private ComboBox<Sala> cbSala;
    @FXML private ComboBox<Estagiario> cbEstagiario;

    @FXML private DatePicker dpData;

    private Atendimento atendimentoPai;
    private EdicaoAtendimentoServiceImpl edicaoAtendimentoService = new EdicaoAtendimentoServiceImpl();

    @FXML
    void initialize() {
        edicaoAtendimentoService.initialize(cbSala, cbEstagiario);
    }

    @FXML
    private void cancelar() {
        edicaoAtendimentoService.voltarTelaPai(txtHoraFim);
    }

    @FXML
    private void atualiar() {
        edicaoAtendimentoService.atualizar(txtHoraInicio, txtHoraFim, cbSala, cbEstagiario, dpData, this.atendimentoPai);
    }

    public void atualizarEstagiario(Atendimento atendimento) {
        this.atendimentoPai = atendimento;
        cbEstagiario.getSelectionModel().select(atendimento.getEstagiario());
        cbSala.getSelectionModel().select(atendimento.getSala());
        dpData.setValue(atendimento.getData());
        txtHoraInicio.setText(atendimento.getHoraAtendimentoInicio().getHour()+"");
        txtHoraFim.setText(atendimento.getHoraAtendimentoFim().getHour()+"");
    }
}
