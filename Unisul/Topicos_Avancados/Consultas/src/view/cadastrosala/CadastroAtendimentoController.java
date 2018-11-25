package view.cadastrosala;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.atendimento.Atendimento;
import model.atendimento.AtendimentoServiceImpl;
import model.estagiario.Estagiario;
import model.sala.Sala;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CadastroAtendimentoController {

    private AtendimentoServiceImpl atendimentoService = new AtendimentoServiceImpl();

    @FXML
    private TextField txtHoraInicio;
    @FXML
    private TextField txtHoraFim;

    @FXML
    private ComboBox<Sala> cbSala;
    @FXML
    private ComboBox<Estagiario> cbEstagiario;

    @FXML
    private DatePicker dpData;

    @FXML
    private Button btnEditar;
    @FXML
    private Button btnExcluir;

    @FXML
    private CheckBox chDataHj;

    @FXML
    private TableView<Atendimento> tableView;
    @FXML
    private TableColumn<Atendimento, String> colSala;
    @FXML
    private TableColumn<Atendimento, String> colEstagiario;
    @FXML
    private TableColumn<Atendimento, String> colDia;
    @FXML
    private TableColumn<Atendimento, String> colHora;


    private List<Atendimento> atendimentos = new ArrayList<>();

    @FXML
    public void initialize() {
        atendimentoService.initialize(tableView, colSala, colEstagiario, colDia, colHora, atendimentos, cbSala, cbEstagiario);
    }

    @FXML
    public void cadastrar() {
        atendimentoService.cadastrar(cbSala, cbEstagiario, dpData, txtHoraInicio, txtHoraFim, tableView, atendimentos);
    }

    @FXML
    void selecionarAtendimento() {
        atendimentoService.editarOuexcluir(btnExcluir, btnEditar);
    }

    @FXML
    void excluir() {
        atendimentoService.excluirSelecionado(btnExcluir, tableView, atendimentos);
    }

    @FXML
    void editar() {
        atendimentoService.editarSelecionado(btnExcluir, tableView, atendimentos);
    }

    @FXML
    void filtrarDataDiaHoje() {
        atendimentoService.filtrarDataDiaHoje(chDataHj, tableView, atendimentos);
    }

    @FXML
    void excluirTudo(){
        atendimentoService.excluirTudo(tableView, atendimentos);
    }

//    private StringProperty dateToString(LocalDate data) {
//        LocalDate localDate = data;
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        String formattedString = localDate.format(formatter);
//        return new SimpleStringProperty(formattedString);
//
//    }

}
