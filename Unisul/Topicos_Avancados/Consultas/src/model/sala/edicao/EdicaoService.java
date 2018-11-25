package model.sala.edicao;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.sala.Sala;

public interface EdicaoService {

    Sala objectMap(TextField txtNome, TextField txtNumero);

    void atualizar(TextField txtNome, TextField txtNumero, Sala SalaPai);

    void voltarTelaPai(TextField txtNome);

    void initialize(Button btnAtualizar, TextField txtNome);
}
