package model.estagiario;


import core.util.MensagemUtils;
import core.util.TelaUtils;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.sala.Sala;
import view.estagiario.edicao.EdicaoController;

import java.util.List;
import java.util.stream.Collectors;

public class EstagiarioServiceImpl extends EstagiarioRepository implements EstagiarioService {


    @Override
    public void initialize(TableView<Estagiario> tableView, TableColumn<Estagiario, String> colNome, TableColumn<Estagiario, Number> colSemestre, List<Estagiario> estagiarios) {
        inicarTableView(colNome, colSemestre);
        listarEstagiario(tableView, estagiarios);
    }

    @Override
    public Estagiario objectMap(TextField txtNome, TextField txtSemestre) {
        try {
            return Estagiario.builder()
                    .nome(txtNome.getText())
                    .semestre(Integer.parseInt(txtSemestre.getText()))
                    .build();
        } catch (Exception e) {
            MensagemUtils.mostraErro("Preencha todos os campos", e);
        }
        return null;
    }

    @Override
    public void cadastrar(TextField txtNome, TextField txtSemestre, TableView<Estagiario> tableView, List<Estagiario> estagiarios) {
        Estagiario map = objectMap(txtNome, txtSemestre);
        Estagiario salvo = salvar(map);
        estagiarios.add(salvo);
        tableView.setItems(FXCollections.observableArrayList(estagiarios));
        limparTela(txtNome, txtSemestre);
        MensagemUtils.mostraMensagem("Estagiario Cadastrado!", AlertType.INFORMATION);
    }

    @Override
    public void limparTela(TextField txtNome, TextField txtSemestre) {
        txtNome.setText("");
        txtSemestre.setText("");
    }

    private void inicarTableView(TableColumn<Estagiario, String> colNome, TableColumn<Estagiario, Number> colSemestre) {
        colNome.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getNome()));
        colSemestre.setCellValueFactory(c -> new SimpleIntegerProperty(c.getValue().getSemestre()));
    }

    @Override
    public void excluirSelecionado(Button btnExcluir, TableView<Estagiario> tableView, List<Estagiario> estagiarios) {
        Estagiario estagiario = tableView.getSelectionModel().getSelectedItem();
        boolean pergunta = MensagemUtils.mostraMensagemPergunta("Excluir Estagiario, " + estagiario.getNome() + "?");

        if (pergunta) {
            excluir(estagiario.getId());
            estagiarios.remove(estagiario);
            tableView.setItems(FXCollections.observableArrayList(estagiarios));
        }

        btnExcluir.setDisable(true);
    }

    @Override
    public void editarSelecionado(Button btnEditar, TableView<Estagiario> tableView, List<Estagiario> estagiarios) {

        Estagiario estagiario = tableView.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../view/estagiario/edicao/Edicao.fxml"));
        Stage stage;
        try {
            Parent root = loader.load();
            stage = TelaUtils.setarStage(root);


            EdicaoController edicaoController = loader.getController();
            edicaoController.atualizarEstagiario(estagiario);

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
    public void filtrar(TextField txtFiltro, TableView<Estagiario> tableView, List<Estagiario> estagiarios) {
        if (txtFiltro.getText().equals("")) {
            tableView.setItems(FXCollections.observableArrayList(estagiarios));
        } else {
            List<Estagiario> filtro = estagiarios.stream()
                    .filter(e -> e.getNome().toLowerCase()
                            .startsWith(txtFiltro.getText().toLowerCase()))
                    .collect(Collectors.toList());
            tableView.setItems(FXCollections.observableArrayList(filtro));
        }
    }

    @Override
    public void excluirTudo(TableView<Estagiario> tableView, List<Estagiario> estagiarios) {
        ObservableList<Estagiario> es = tableView.getItems();
        boolean pergunta = MensagemUtils.mostraMensagemPergunta("Excluir todas as sala?");

        if (pergunta) {
            for (Estagiario estagiario: es) {
                excluir(estagiario.getId());
                estagiarios.remove(estagiario);
            }
            tableView.setItems(FXCollections.observableArrayList(estagiarios));
        }
    }

    private void listarEstagiario(TableView<Estagiario> tableView, List<Estagiario> estagiarios) {

        estagiarios.clear();
        estagiarios.addAll(findAll());

        tableView.setItems(FXCollections.observableArrayList(estagiarios));
    }

}
