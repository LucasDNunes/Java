package view;

import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.produto.Produto;
import model.produto.ProdutoServiceImpl;

public class CadastroProdutoController {
	
	@FXML TextField txtCod;
	@FXML TextField txtNome;
	@FXML TextField txtValor;
	@FXML TextField txtQuantidade;
	@FXML TextField txtTotal;
	
	@FXML TableView<Produto> tableView;
	@FXML TableColumn<Produto, Number> colCod;
	@FXML TableColumn<Produto, String> colProduto;
	@FXML TableColumn<Produto, Number> colValor;
	@FXML TableColumn<Produto, Number> colQuantidade;
	
	private List<Produto> produtos = new ArrayList<>();
	
	private ProdutoServiceImpl produtoServiceImpl = new ProdutoServiceImpl();
	
	@FXML
	public void initialize() {
		produtoServiceImpl.inicializaTableView(colCod, colProduto, colValor, colQuantidade);
		produtoServiceImpl.lerArquivo(produtos, tableView, txtTotal);
	}
	
	@FXML
	public void cadastrar() {
		produtoServiceImpl.gravar(txtCod, txtNome, txtValor, txtQuantidade);
		produtoServiceImpl.lerArquivo(produtos, tableView, txtTotal);
		produtoServiceImpl.limparTela(txtCod, txtNome, txtQuantidade, txtValor);
	}
	
}
