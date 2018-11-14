package view.cadastro;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import model.atendimento.Atendimento;
import model.sala.Sala;

public class CadastroController {
	
	@FXML TextField txtEstagiario;
	//@FXML TextField date;
	
	@FXML ComboBox<Sala> cbSala;
	
	@FXML TableView<Atendimento> tableView;
	@FXML TableColumn<Atendimento, String> colSala;
	@FXML TableColumn<Atendimento, String> colEstagiario;
	@FXML TableColumn<Atendimento, String> colDia;
	
	@FXML
	public void initialize() {
		long endTime = 23;
		Label timeLabel = new Label();
		DateFormat timeFormat = new SimpleDateFormat( "HH:mm:ss" );
		final Timeline timeline = new Timeline(
		    new KeyFrame(
		        Duration.millis( 500 ),
		        event -> {
		            final long diff = endTime - System.currentTimeMillis();
		            if ( diff < 0 ) {
		            //  timeLabel.setText( "00:00:00" );
		                timeLabel.setText( timeFormat.format( 0 ) );
		            } else {
		                timeLabel.setText( timeFormat.format( diff ) );
		            }
		        }
		    )
		);
		timeline.setCycleCount( Animation.INDEFINITE );
		timeline.play();
	}
}
