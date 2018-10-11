package dadosFile;

import java.util.List;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public interface DadosFileService {
	
	public void inicializaTableView(TableColumn<DadosFile, String> colNome, TableColumn<DadosFile, String> colTamanho );
	
	public void abreDiretorio(TextField txtPath);
	
	public void listar(TextField txtPath, TableView<DadosFile> tableView, List<DadosFile> lista);
	
	public void apagarLinhaSelecionada(TableView<DadosFile> tableView, List<DadosFile> dadosFiles);
	
	public void apagarTodos(TableView<DadosFile> tableView, List<DadosFile> dadosFiles);
}
