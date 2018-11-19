package model.atendimento;

import javafx.scene.control.*;
import model.estagiario.Estagiario;
import model.sala.Sala;

import java.util.List;

public interface AtendimentoService {

    void initialize(TableView<Atendimento> tableView, TableColumn<Atendimento, String> colSala,
                    TableColumn<Atendimento, String> colEstagiario, TableColumn<Atendimento, String> colDia,
                    TableColumn<Atendimento, String> colHora, List<Atendimento> atendimentos, ComboBox<Sala> cbSala, ComboBox<Estagiario> cbEstagiario);

    Atendimento objectMap(ComboBox<Sala> cbSala, ComboBox<Estagiario> cbEstagiario, DatePicker dpData, TextField txtHoraInicio, TextField txtHoraFim);

    void cadastrar(ComboBox<Sala> cbSala, ComboBox<Estagiario> cbEstagiario, DatePicker dpData, TextField txtHoraInicio, TextField txtHoraFim, TableView<Atendimento> tableView, List<Atendimento> atendimentos);

    void limparTela(ComboBox<Sala> cbSala, ComboBox<Estagiario> cbEstagiario, DatePicker dpData, TextField txtHoraInicio, TextField txtHoraFim);
//
//    void editarOuexcluir(Button btnExcluir, Button btnEditar);
//
//    void editarSelecionado(Button btnExcluir, TableView<Atendimento> tableView, List<Atendimento> atendimentos);
//
//    void excluirSelecionado(Button btnExcluir, TableView<Atendimento> tableView, List<Atendimento> atendimentos);
//
//    void filtrar(TextField txtFiltro, TableView<Atendimento> tableView, List<Atendimento> atendimentos);
}
