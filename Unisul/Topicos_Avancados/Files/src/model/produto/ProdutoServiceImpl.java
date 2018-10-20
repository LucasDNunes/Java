package model.produto;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;
import java.util.Objects;

import javafx.collections.FXCollections;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class ProdutoServiceImpl implements ProdutoService{
	
	private static final String PRODUTO_TXT = "arquivos\\produto.txt";

	@Override
	public void inicializaTableView(TableColumn<Produto, Number> colCod, TableColumn<Produto, String> colNome,
			TableColumn<Produto, Number> colValor, TableColumn<Produto, Number> colQuantidade) {
		
		colCod.setCellValueFactory(cellData -> cellData.getValue().codProperty());
		colNome.setCellValueFactory(cellData -> cellData.getValue().nomeProperty());
		colValor.setCellValueFactory(cellData -> cellData.getValue().valorProperty());
		colQuantidade.setCellValueFactory(cellData -> cellData.getValue().quantidadeProperty());
		
	}

	@Override
	public void lerArquivo(List<Produto> produtos, TableView<Produto> tableView, TextField txtTotal) {
		
		produtos.clear();
		
		try(
				FileReader fileReader = new FileReader(PRODUTO_TXT);
				BufferedReader bufferedReader = new BufferedReader(fileReader)
			) {
			
			String linha="";
			
			while (Objects.nonNull(linha = bufferedReader.readLine())) {
				
				String cod = linha.substring(0, 3);
				String nome = linha.substring(3,13);
				
				String valorAntesVirgula = linha.substring(13, 16);
				String valorDepoisVirgula = linha.substring(16, 18);
				String valor = valorAntesVirgula + "." + valorDepoisVirgula;
				
				String quantidade = linha.substring(18, 21);
				
				Produto produto = new Produto();
				produto.setCod(Integer.parseInt(cod));
				produto.setNome(nome);
				produto.setValor(Double.parseDouble(valor));
				produto.setQuantidade(Integer.parseInt(quantidade));
				produtos.add(produto);
			}
			
			tableView.setItems(FXCollections.observableArrayList(produtos));
			txtTotal.setText(String.format("%.2f R$", calcularTotal(produtos)));
			
		} catch (Exception e) {
			mostraMensagem(e.getMessage(), AlertType.ERROR);
		}
	}

	@Override
	public void gravar(TextField txtCod, TextField txtNome, TextField txtValor, TextField txtQuantidade) {
		try (
				FileWriter fileWriter = new FileWriter(PRODUTO_TXT, true);
				BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)
			) {
			
			Produto produto = new Produto();
			produto.setCod(Integer.parseInt(txtCod.getText()));
			produto.setNome(txtNome.getText());
			produto.setValor(Double.parseDouble(txtValor.getText()));
			produto.setQuantidade(Integer.parseInt(txtQuantidade.getText()));
			
			String cod = String.format("%03d", produto.getCod()).substring(0,3);
			String nome = String.format("%-10.10s", produto.getNome());
			String valor = String.format("%06.2f", produto.getValor()).substring(0,6).replace(",", "");
			String quatidade = String.format("%03d", produto.getQuantidade());
			
			bufferedWriter.append(cod + nome + valor + quatidade +"\n");
			
		} catch (Exception e) {
			mostraMensagem(e.getMessage(), AlertType.ERROR);
		}
	}
	
	
	private void mostraMensagem(String msg, AlertType tipoMensagem) {
		Alert alerta = new Alert(tipoMensagem);
		alerta.setHeaderText(null);
		alerta.setContentText(msg);
		alerta.show();
	}
	
	private double calcularTotal(List<Produto> produtos) {
		double total = 0;
		for (Produto produto : produtos) {
			total += produto.getValor() * produto.getQuantidade();
		}
		return total;
	}

	@Override
	public void limparTela(TextField txtCod, TextField txtNome, TextField txtQuantidade, TextField txtValor) {
		
		txtCod.setText("");
		txtNome.setText("");
		txtQuantidade.setText("");
		txtValor.setText("");
	}
	
	
}
