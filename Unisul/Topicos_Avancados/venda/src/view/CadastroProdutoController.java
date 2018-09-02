package view;

import java.util.ArrayList;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Produto;

public class CadastroProdutoController {
	
	@FXML TextField txtProduto;
	@FXML TextField txtValor;
	@FXML TextField txtQuantidade;
	
	@FXML TableView<Produto> table;
	@FXML TableColumn<Produto, String> colNome;
	@FXML TableColumn<Produto, Number> colValor;
	@FXML TableColumn<Produto, Number> colQuantidade;
	
	private ArrayList<Produto> produtos = new ArrayList<>();
	
	@FXML
	public void initialize() {
		inicializaTbl();
	}
	
	@FXML
	public void adicionar() {
		Produto produto = new Produto();
		produto.setNome(txtProduto.getText());
		produto.setValor(Integer.parseInt(txtValor.getText()));
		produto.setQuantidade(Integer.parseInt(txtQuantidade.getText()));
		
		produtos.add(produto);
		
		table.setItems(FXCollections.observableArrayList(produtos));
	}
	
	private void inicializaTbl() {
		colNome.setCellValueFactory(cellData -> cellData.getValue().nomeProperty());
		colValor.setCellValueFactory(cellData -> cellData.getValue().valorProperty());
		colQuantidade.setCellValueFactory(cellData -> cellData.getValue().quantidadeProperty());
	}
}
