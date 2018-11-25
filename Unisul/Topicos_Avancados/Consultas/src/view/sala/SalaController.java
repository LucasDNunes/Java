package view.sala;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.sala.Sala;
import model.sala.SalaServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class SalaController {

    private SalaServiceImpl salaService = new SalaServiceImpl();

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtNumero;

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnExcluir;

    @FXML
    private TextField txtFiltro;

    @FXML
    private TableView<Sala> tableView;

    @FXML
    private TableColumn<Sala, String> colNome;

    @FXML
    private TableColumn<Sala, Number> colNumero;

    private List<Sala> salas = new ArrayList<>();

    @FXML
    void initialize() {
        salaService.initialize(tableView, colNome, colNumero, salas);
    }

    @FXML
    void cadastrar() {
        salaService.cadastrar(txtNome, txtNumero, tableView, salas);
    }

    @FXML
    void selecionarSala() {
        salaService.editarOuexcluir(btnExcluir, btnEditar);
    }

    @FXML
    void editar() {
        salaService.editarSelecionado(btnEditar, tableView, salas);
    }

    @FXML
    void excluir() {
        salaService.excluirSelecionado(btnExcluir, tableView, salas);
    }

    @FXML
    void filtrar() {
        salaService.filtrar(txtFiltro, tableView, salas);
    }

    @FXML
    void excluirTudo(){
        salaService.excluirTudo(tableView, salas);
    }

}
