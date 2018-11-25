package view.sala.edicao;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.sala.Sala;
import model.sala.edicao.EdicaoServiceImpl;

public class EdicaoController {

    private EdicaoServiceImpl edicaoService = new EdicaoServiceImpl();

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtNumero;

    @FXML
    private Button btnAtualizar;

    private Sala salaPai;

    @FXML
    private void initialize(){
        edicaoService.initialize(btnAtualizar, txtNome);
    }


    @FXML
    public void atualiar() {
        edicaoService.atualizar(txtNome, txtNumero, salaPai);
    }

    @FXML
    void cancelar() {
        edicaoService.voltarTelaPai(txtNome);
    }

    public void atualizarEstagiario(Sala sala) {
        this.salaPai = sala;
        txtNome.setText(sala.getNome());
        txtNumero.setText(sala.getNumero().toString());
    }
}
