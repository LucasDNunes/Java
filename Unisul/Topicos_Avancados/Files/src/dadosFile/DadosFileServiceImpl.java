package dadosFile;

import java.io.File;
import java.util.List;
import java.util.Objects;

import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;

public class DadosFileServiceImpl extends DadosFile implements DadosFileService{

	@Override
	public void inicializaTableView(TableColumn<DadosFile, String> colNome, TableColumn<DadosFile, String> colTamanho) {
		colNome.setCellValueFactory(cellData -> cellData.getValue().nomeProperty());
		colTamanho.setCellValueFactory(cellData -> cellData.getValue().tamanhoProperty());
	}

	@Override
	public void abreDiretorio(TextField txtPath) {
		DirectoryChooser directoryChooser = new DirectoryChooser();
		File file = directoryChooser.showDialog(null);
		if (Objects.nonNull(file)) {
			txtPath.setText(file.getAbsolutePath());
		}
	}

	@Override
	public void listar(TextField txtPath, TableView<DadosFile> tableView, List<DadosFile> lista) {
		if (!txtPath.getText().equals("")) {
			File diretorio = new File(txtPath.getText());
			if (diretorio.isDirectory()) {
				File[] files = diretorio.listFiles();
				for (File f : files) {
					DadosFile dados = new DadosFile();
					dados.setNome(f.getName());
					dados.setTamanho(f.length() + "");
					dados.setPath(f.getAbsolutePath());
					lista.add(dados);
				}
				
				tableView.setItems(FXCollections.observableArrayList(lista));
			}
		}
	}

	@Override
	public void apagarLinhaSelecionada(TableView<DadosFile> tableView, List<DadosFile> dadosFiles) {
		DadosFile dadosFile = tableView.getSelectionModel().getSelectedItem();
		if (Objects.nonNull(dadosFile)) {
			File file = new File(dadosFile.getPath());
			//file.delete();
			dadosFiles.remove(dadosFile);
			tableView.setItems(FXCollections.observableArrayList(dadosFiles));
		}
	}

	@Override
	public void apagarTodos(TableView<DadosFile> tableView, List<DadosFile> dadosFiles) {
		for (DadosFile d : tableView.getItems()) {
			File f = new File(d.getPath());
			//f.delete();
		}
		dadosFiles.clear();
		tableView.setItems(FXCollections.observableArrayList(dadosFiles));
	}

}
