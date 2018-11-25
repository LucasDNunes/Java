package model.sala;

import core.util.MensagemUtils;
import core.util.TelaUtils;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.estagiario.Estagiario;
import view.sala.edicao.EdicaoController;

import java.util.List;
import java.util.stream.Collectors;

public class SalaServiceImpl extends SalaRepository implements SalaService {

    @Override
    public void initialize(TableView<Sala> tableView, TableColumn<Sala, String> colNome, TableColumn<Sala, Number> colNumero, List<Sala> salas) {
        inicarTableView(colNome, colNumero);
        listarEstagiario(tableView, salas);
    }

    @Override
    public Sala objectMap(TextField txtNome, TextField txtNumero) {
        try {
            return Sala.builder()
                    .nome(txtNome.getText())
                    .numero(Integer.parseInt(txtNumero.getText()))
                    .build();
        } catch (Exception e) {
            MensagemUtils.mostraMensagem(e.getMessage(), Alert.AlertType.ERROR);
        }
        return null;
    }

    @Override
    public void cadastrar(TextField txtNome, TextField txtNumero, TableView<Sala> tableView, List<Sala> salas) {
        Sala map = objectMap(txtNome, txtNumero);
        Sala salvo = salvar(map);
        salas.add(salvo);
        tableView.setItems(FXCollections.observableArrayList(salas));
        limparTela(txtNome, txtNumero);
        MensagemUtils.mostraMensagem("Sala Cadastrada!", Alert.AlertType.INFORMATION);
    }

    @Override
    public void limparTela(TextField txtNome, TextField txtSemestre) {
        txtNome.setText("");
        txtSemestre.setText("");
    }

    @Override
    public void excluirSelecionado(Button btnExcluir, TableView<Sala> tableView, List<Sala> salas) {
        Sala sala = tableView.getSelectionModel().getSelectedItem();
        boolean pergunta = MensagemUtils.mostraMensagemPergunta("Excluir Sala, " + sala.getNome() + "?");

        if (pergunta) {
            excluir(sala.getId());
            salas.remove(sala);
            tableView.setItems(FXCollections.observableArrayList(salas));
        }

        btnExcluir.setDisable(true);
    }

    @Override
    public void editarSelecionado(Button btnEditar, TableView<Sala> tableView, List<Sala> salas) {
        Sala sala = tableView.getSelectionModel().getSelectedItem();
        Stage stage = new Stage();
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../view/sala/edicao/Edicao.fxml"));
            Parent root = loader.load();
            stage = TelaUtils.setarStage(root);

            EdicaoController edicaoController = loader.getController();
            edicaoController.atualizarEstagiario(sala);

            stage.setOnCloseRequest(e -> {
                edicaoController.atualiar();
                tableView.setItems(FXCollections.observableArrayList(super.findAll()));
            });

            stage.show();
        } catch (Exception e) {
            MensagemUtils.mostraErro(MensagemUtils.ERRO, e);
        }
        btnEditar.setDisable(true);
    }

    @Override
    public void editarOuexcluir(Button btnExcluir, Button btnEditar) {
        btnExcluir.setDisable(false);
        btnEditar.setDisable(false);
    }

    @Override
    public void filtrar(TextField txtFiltro, TableView<Sala> tableView, List<Sala> salas) {
        if (txtFiltro.getText().equals("")) {
            tableView.setItems(FXCollections.observableArrayList(salas));
        } else {
            List<Sala> filtro = salas.stream()
                    .filter(e -> e.getNome().toLowerCase().startsWith(txtFiltro.getText().toLowerCase()))
                    .collect(Collectors.toList());
            tableView.setItems(FXCollections.observableArrayList(filtro));
        }
    }

    @Override
    public void excluirTudo(TableView<Sala> tableView, List<Sala> salas) {
        ObservableList<Sala> sa = tableView.getItems();
        boolean pergunta = MensagemUtils.mostraMensagemPergunta("Excluir todas as sala?");

        if (pergunta) {
            for (Sala sala: sa) {
                excluir(sala.getId());
                salas.remove(sala);
            }
            tableView.setItems(FXCollections.observableArrayList(salas));
        }
    }

    private void inicarTableView(TableColumn<Sala, String> colNome, TableColumn<Sala, Number> colNumero) {
        colNome.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getNome()));
        colNumero.setCellValueFactory(c -> new SimpleIntegerProperty(c.getValue().getNumero()));
    }

    private void listarEstagiario(TableView<Sala> tableView, List<Sala> salas) {

        salas.clear();
        salas.addAll(findAll());

        tableView.setItems(FXCollections.observableArrayList(salas));
    }
}
