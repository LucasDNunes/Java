package view.cadastrosala;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import model.atendimento.Atendimento;
import model.sala.Sala;

public class CadastroSalaController {
	
	@FXML TextField txtEstagiario;
	@FXML TextField txtHoraInicio;
	@FXML TextField txtHoraFim;
	
	@FXML ComboBox<Sala> cbSala;
	
	@FXML TableView<Atendimento> tableView;
	@FXML TableColumn<Atendimento, String> colSala;
	@FXML TableColumn<Atendimento, String> colEstagiario;
	@FXML TableColumn<Atendimento, String> colDia;
	
	
	@FXML
	public void initialize() {
		colSala.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getSala().getSala()));
		colEstagiario.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getEstagiario().getNome()));
		colDia.setCellValueFactory(c -> DateToString(c.getValue().getData()));
		
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
			//setar quem é o pai dessa tela
//			s.initOwner();
			s.initModality(Modality.WINDOW_MODAL);
			s.setResizable(false);
			s.initStyle(StageStyle.UNDECORATED);
			
			//para
//			CadastroController c = l.getController();
			
			s.show();
			
			
			// fechar e atualizar quem chamou 
			
			FXMLLoader lo = new FXMLLoader();
			lo.load();
			CadastroSalaController ca = l.getController();
			
			Stage se = (Stage) txtEstagiario.getScene().getWindow();
			se.close();
			
			se.setOnCloseRequest((WindowEvent event1) -> {
		        
		    });
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
