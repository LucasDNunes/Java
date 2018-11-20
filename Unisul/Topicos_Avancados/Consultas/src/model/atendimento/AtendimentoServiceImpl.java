package model.atendimento;

import core.util.MensagemUtils;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.estagiario.Estagiario;
import model.estagiario.EstagiarioRepository;
import model.sala.Sala;
import model.sala.SalaRepository;
import view.cadastrosala.edicaoAtendimento.EdicaoCadastroController;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class AtendimentoServiceImpl extends AtendimentoRepository implements AtendimentoService{

    private static final DateTimeFormatter DATAFORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private SalaRepository salaRepository = new SalaRepository();
    private EstagiarioRepository estagiarioRepository = new EstagiarioRepository();

    @Override
    public void initialize(TableView<Atendimento> tableView, TableColumn<Atendimento, String> colSala, TableColumn<Atendimento, String> colEstagiario, TableColumn<Atendimento, String> colDia, TableColumn<Atendimento, String> colHora, List<Atendimento> atendimentos, ComboBox<Sala> cbSala, ComboBox<Estagiario> cbEstagiario) {
        inicarTableView(colSala, colEstagiario, colDia, colHora);
        preencheEstagiarioESala(cbSala, cbEstagiario);
        listarAtendimento(tableView, atendimentos);
    }

    @Override
    public Atendimento objectMap(ComboBox<Sala> cbSala, ComboBox<Estagiario> cbEstagiario, DatePicker dpData, TextField txtHoraInicio, TextField txtHoraFim) {
        cbSala.getSelectionModel().getSelectedItem();
        return Atendimento.builder()
                .sala(cbSala.getSelectionModel().getSelectedItem())
                .data(dpData.getValue())
                .horaAtendimentoInicio(LocalTime.of(Integer.parseInt(txtHoraInicio.getText()), 00))
                .horaAtendimentoFim(LocalTime.of(Integer.parseInt(txtHoraFim.getText()), 00))
                .estagiario(cbEstagiario.getSelectionModel().getSelectedItem())
                .build();
    }

    @Override
    public void cadastrar(ComboBox<Sala> cbSala, ComboBox<Estagiario> cbEstagiario, DatePicker dpData, TextField txtHoraInicio, TextField txtHoraFim, TableView<Atendimento> tableView, List<Atendimento> atendimentos) {
        Atendimento atendimento = objectMap(cbSala, cbEstagiario, dpData, txtHoraInicio, txtHoraFim);
        Atendimento salvo = salvar(atendimento);
        atendimentos.add(salvo);
        tableView.setItems(FXCollections.observableArrayList(atendimentos));
        limparTela(cbSala, cbEstagiario, dpData, txtHoraInicio, txtHoraFim);
        MensagemUtils.mostraMensagem("Atendimento Cadastrado!", Alert.AlertType.INFORMATION);
    }

    @Override
    public void limparTela(ComboBox<Sala> cbSala, ComboBox<Estagiario> cbEstagiario, DatePicker dpData, TextField txtHoraInicio, TextField txtHoraFim) {
        cbSala.getSelectionModel().clearSelection();
        cbEstagiario.getSelectionModel().clearSelection();
        dpData.setValue(null);
        txtHoraInicio.setText(null);
        txtHoraFim.setText(null);
    }

    @Override
    public void editarOuexcluir(Button btnExcluir, Button btnEditar) {
        btnEditar.setDisable(false);
        btnExcluir.setDisable(false);
    }

    @Override
    public void editarSelecionado(Button btnEditar, TableView<Atendimento> tableView, List<Atendimento> atendimentos) {
        Atendimento atendimento = tableView.getSelectionModel().getSelectedItem();
        Stage stage = new Stage();
        try {

            FXMLLoader loader =  new FXMLLoader(getClass().getResource("../../view/cadastrosala/edicaoAtendimento/EdicaoCadastro.fxml"));
            Parent root = loader.load();
            stage.setScene(new Scene(root));
            stage.initOwner(btnEditar.getScene().getWindow());
            stage.initModality(Modality.WINDOW_MODAL);
            stage.setResizable(false);
            stage.initStyle(StageStyle.UNDECORATED);

            EdicaoCadastroController edicaoController = loader.getController();
            edicaoController.atualizarEstagiario(atendimento);
            stage.show();
        } catch (Exception e) {
            MensagemUtils.mostraMensagem(e.getMessage(), Alert.AlertType.ERROR);
        }
        btnEditar.setDisable(true);
    }

    @Override
    public void excluirSelecionado(Button btnExcluir, TableView<Atendimento> tableView, List<Atendimento> atendimentos) {
        Atendimento atendimento = tableView.getSelectionModel().getSelectedItem();
        boolean pergunta = MensagemUtils.mostraMensagemPergunta("Excluir Atendimento ?");

        if (pergunta) {
            excluir(atendimento.getId());
            atendimentos.remove(atendimento);
            tableView.setItems(FXCollections.observableArrayList(atendimentos));
        }

        btnExcluir.setDisable(true);
    }

    private void preencheEstagiarioESala(ComboBox<Sala> cbSala, ComboBox<Estagiario> cbEstagiario) {
        ArrayList<Sala> salas = salaRepository.findAll();
        ArrayList<Estagiario> estagiarios = estagiarioRepository.findAll();

        cbSala.getItems().addAll(salas);
        cbEstagiario.getItems().addAll(estagiarios);
    }

    private void inicarTableView(TableColumn<Atendimento, String> colSala, TableColumn<Atendimento, String> colEstagiario, TableColumn<Atendimento, String> colDia, TableColumn<Atendimento, String> colHora) {
        colSala.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getSala().getNome()));
        colEstagiario.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getEstagiario().getNome()));
        colDia.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getData().format(DATAFORMATTER)));
        colHora.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getHoraAtendimentoInicio()
                +" até " + c.getValue().getHoraAtendimentoFim()));
    }

    private void listarAtendimento(TableView<Atendimento> tableView, List<Atendimento> atendimentos) {

        atendimentos.clear();
        atendimentos.addAll(findAll());

        tableView.setItems(FXCollections.observableArrayList(atendimentos));
    }
}
