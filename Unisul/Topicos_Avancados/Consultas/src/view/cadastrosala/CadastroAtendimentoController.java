package view.cadastrosala;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import model.atendimento.Atendimento;
import model.atendimento.AtendimentoServiceImpl;
import model.estagiario.Estagiario;
import model.sala.Sala;

public class CadastroAtendimentoController {

	private AtendimentoServiceImpl atendimentoService = new AtendimentoServiceImpl();
	
//	@FXML TextField txtEstagiario;
	@FXML TextField txtHoraInicio;
	@FXML TextField txtHoraFim;
	
	@FXML ComboBox<Sala> cbSala;
	@FXML ComboBox<Estagiario> cbEstagiario;

    @FXML
    private DatePicker dpData;

	@FXML TableView<Atendimento> tableView;
	@FXML TableColumn<Atendimento, String> colSala;
	@FXML TableColumn<Atendimento, String> colEstagiario;
	@FXML TableColumn<Atendimento, String> colDia;
	@FXML TableColumn<Atendimento, String> colHora;


	private List<Atendimento> atendimentos = new ArrayList<>();
	
	@FXML
	public void initialize() {
		atendimentoService.initialize(tableView, colSala, colEstagiario, colDia, colHora, atendimentos, cbSala, cbEstagiario);
	}

	@FXML
    public void cadastrar(){
	    atendimentoService.cadastrar(cbSala, cbEstagiario, dpData, txtHoraInicio,txtHoraFim, tableView, atendimentos);
    }
	
	private StringProperty DateToString(LocalDate data) {
		LocalDate localDate = data;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String formattedString = localDate.format(formatter);
		return new SimpleStringProperty(formattedString);
		
	}
	
	//para fazer a e passar dados para ela modal
	private void teste() {
		Stage s = new Stage();
		FXMLLoader l = new FXMLLoader();
		try {
			Parent root = l.load();
			s.setScene(new Scene(root));
			//setar quem � o pai dessa tela
//			s.initOwner();
			s.initModality(Modality.WINDOW_MODAL);
			s.setResizable(false);
			s.initStyle(StageStyle.UNDECORATED);
			
			//para
//			CadastroController c = l.getController();
			
			s.show();
			
			
//			 fechar e atualizar quem chamou
			
			FXMLLoader lo = new FXMLLoader();
			lo.load();
			CadastroAtendimentoController ca = l.getController();
			
//			Stage se = (Stage) txtEstagiario.getScene().getWindow();
//			se.close();
//
//			se.setOnCloseRequest((WindowEvent event1) -> {
//
//		    });
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
