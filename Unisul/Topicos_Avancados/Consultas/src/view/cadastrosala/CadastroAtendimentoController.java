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
	
	@FXML private TextField txtHoraInicio;
	@FXML private TextField txtHoraFim;
	@FXML private TextField txtFiltro;

	@FXML private ComboBox<Sala> cbSala;
	@FXML private ComboBox<Estagiario> cbEstagiario;

    @FXML
    private DatePicker dpData;

    @FXML private Button btnEditar;
    @FXML private Button btnExcluir;

	@FXML private TableView<Atendimento> tableView;
	@FXML private TableColumn<Atendimento, String> colSala;
	@FXML private TableColumn<Atendimento, String> colEstagiario;
	@FXML private TableColumn<Atendimento, String> colDia;
	@FXML private TableColumn<Atendimento, String> colHora;


	private List<Atendimento> atendimentos = new ArrayList<>();
	
	@FXML
	public void initialize() {
		atendimentoService.initialize(tableView, colSala, colEstagiario, colDia, colHora, atendimentos, cbSala, cbEstagiario);
	}

	@FXML
    public void cadastrar(){
	    atendimentoService.cadastrar(cbSala, cbEstagiario, dpData, txtHoraInicio,txtHoraFim, tableView, atendimentos);
    }

	@FXML
	void selecionarAtendimento() {
		atendimentoService.editarOuexcluir(btnExcluir, btnEditar);
	}

    @FXML
    void excluir() {
        atendimentoService.excluirSelecionado(btnExcluir, tableView, atendimentos);
    }

    @FXML
    void editar() {
        atendimentoService.editarSelecionado(btnExcluir, tableView, atendimentos);
    }

//    @FXML
//    void filtrar() {
//        atendimentoService.filtrar(txtFiltro, tableView, estagiarios);
//    }

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
