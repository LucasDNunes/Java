package model.sala.edicao;

import core.util.MensagemUtils;
import core.util.TelaUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.sala.Sala;
import model.sala.SalaRepository;

import java.io.IOException;

public class EdicaoServiceImpl extends SalaRepository implements EdicaoService{

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
    public void atualizar(TextField txtNome, TextField txtNumero, Sala salaPai) {
        Sala map = objectMap(txtNome, txtNumero);
        salaPai.setNome(map.getNome());
        salaPai.setNumero(map.getNumero());
        salvar(salaPai);

    }

    @Override
    public void voltarTelaPai(TextField txtNome) {
        try {
            FXMLLoader lo = new FXMLLoader(getClass().getResource("../../../view/sala/Sala.fxml"));
            lo.load();
        } catch (IOException e) {
            MensagemUtils.mostraErro("Erro em voltar para tela de origem",e);
        }

        Stage se = (Stage) txtNome.getScene().getWindow();
        se.close();
    }

    @Override
    public void initialize(Button btnAtualizar, TextField txtNome) {

        btnAtualizar.setOnAction(e ->{
            Stage stage = (Stage) txtNome.getScene().getWindow();
            stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
        });
    }


}
