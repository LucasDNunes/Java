package core.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class TelaUtils {

    private TelaUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static <T> T telaEdicao(String url) {

        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(TelaUtils.class.getResource(url));

        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            MensagemUtils.mostraErro("Erro em chamar tela de edição", e);
        }
        stage.setScene(new Scene(root));
//        stage.initOwner(btnEditar.getScene().getWindow());
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);

        stage.show();

        return loader.getController();


//        edicaoController.atualizarEstagiario(atendimento);

    }

    public static Stage setarStage(Parent root) {
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
//        stage.initOwner(btnEditar.getScene().getWindow());
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);

        return stage;
    }

//    public static FXMLLoader getResource(String url) {
//        return new FXMLLoader(TelaUtils.class.getResource(url));
//    }
}
