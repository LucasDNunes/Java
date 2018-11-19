package model.sala;

import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.util.List;

public interface SalaService {

    void initialize(TableView<Sala> tableView, TableColumn<Sala, String> colNome, TableColumn<Sala, Number> colSemestre, List<Sala> estagiarios);

    Sala objectMap(TextField txtNome, TextField txtSemestre);

    void cadastrar(TextField txtNome, TextField txtSemestre, TableView<Sala> tableView, List<Sala> estagiarios);

    void limparTela(TextField txtNome, TextField txtSemestre);

    void editarOuexcluir(Button btnExcluir, Button btnEditar);

    void editarSelecionado(Button btnExcluir, TableView<Sala> tableView, List<Sala> estagiarios);

    void excluirSelecionado(Button btnExcluir, TableView<Sala> tableView, List<Sala> estagiarios);

    void filtrar(TextField txtFiltro, TableView<Sala> tableView, List<Sala> estagiarios);
}
