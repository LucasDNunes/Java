package model.transporte;

import java.util.List;

import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public interface TransporteService {
	
	public void inicializaTableView(TableColumn<Transporte, String> colTipo,
			TableColumn<Transporte, Number> colDistancia, TableColumn<Transporte, Number> colCusto);
	
	public void lerArquivo(List<Transporte> transportes, TableView<Transporte> tableView);
	
	public void gravar(RadioButton rdProprio, RadioButton rdPublico, TextField txtDistancia, TextField txtCustoMensal);
	
	public void unirAlunoETransporte();
}
