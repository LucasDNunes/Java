package view;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Produto;

public class CadastroProdutoController {
	
	@FXML
	TextField txtProduto;
	@FXML
	TextField txtValor;
	@FXML
	TextField txtQuantidade;
	@FXML
	TextField txtTotal;
	
	@FXML
	TableView<Produto> table;
	@FXML
	TableColumn<Produto, String> colNome;
	@FXML
	TableColumn<Produto, Number> colValor;
	@FXML
	TableColumn<Produto, Number> colQuantidade;
	@FXML
	TableColumn<Produto, Number> colSubTotal;
	
	private ArrayList<Produto> produtos = new ArrayList<>();
	
	@FXML
	public void initialize() {
		inicializaTbl();
		table.setEditable(true);
	}
	
	@FXML
	public void adicionar() {
		Produto produto = new Produto();
		produto.setNome(txtProduto.getText());
		produto.setValor(Double.parseDouble(txtValor.getText().replace(",", ".")));
		produto.setQuantidade(Integer.parseInt(txtQuantidade.getText()));
		produto.setSubTotal(calculaSubTotal(produto.getValor(), produto.getQuantidade()));
				
		produtos.add(produto);
		
		
		table.setItems(FXCollections.observableArrayList(produtos));

		txtTotal.setText(String.format("%.2f $", calculaTotal()));
		limpaTela();
	}
	
	private void inicializaTbl() {
		colNome.setCellValueFactory(cellData -> cellData.getValue().nomeProperty());
		colValor.setCellValueFactory(cellData -> cellData.getValue().valorProperty());
		colQuantidade.setCellValueFactory(cellData -> cellData.getValue().quantidadeProperty());
		colSubTotal.setCellValueFactory(cellData -> cellData.getValue().subTotalProperty());
	}
	
	@FXML
	private void selecionaProduto() {
		Produto produto = table.getSelectionModel().getSelectedItem();
		txtProduto.setText(produto.getNome());
		txtValor.setText(String.format("%.2f", produto.getValor()));
		txtQuantidade.setText(String.format("%d", produto.getQuantidade()));
	}
	@FXML
	private void limpaTela() {
		txtProduto.setText("");
		txtValor.setText("");
		txtQuantidade.setText("");
	}
	
	private double calculaSubTotal(double valor, int quantidade) {
		return  valor * quantidade;
	}
	
	private double calculaTotal() {
		double total = 0;
		for (Produto produto : produtos) {
			total += produto.getSubTotal();
		}
		return total;
	}

	@FXML public void edita(TableColumn.CellEditEvent<Produto, String> productStringCellEvent) {
		Produto produto = table.getSelectionModel().getSelectedItem();
		produto.setNome(productStringCellEvent.getNewValue());
	}

	
	

}
