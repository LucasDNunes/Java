package model.produto;

import java.util.List;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public interface ProdutoService {
	
	public void inicializaTableView(TableColumn<Produto, Number> colCod, TableColumn<Produto, String> colNome, TableColumn<Produto, Number> colValor,
			TableColumn<Produto, Number> colQuantidade);
	
	public void lerArquivo(List<Produto> produtos, TableView<Produto> tableView, TextField txtTotal);
	
	public void gravar(TextField txtCod, TextField txtNome, TextField txtValor, TextField txtQuantidade);
	
	void limparTela(TextField txtCod, TextField txtNome, TextField txtQuantidade, TextField txtValor);
}
