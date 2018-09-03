package view;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import model.Cliente;

public class ClienteController {
	
	@FXML RadioButton ckCartao;
	@FXML RadioButton ckBoleto;
	@FXML RadioButton ckDeposito;
	
	@FXML TextField txtCliente;
	
	@FXML ComboBox<String> txtCidades;
	
	ArrayList<Cliente> clientes = new ArrayList<>();
	
	@FXML
	public void initialize() {
		inicializaCidades();
	}
	
	@FXML
	public void inicializaCidades() {
		txtCidades.getItems().add("Tubarão");
		txtCidades.getItems().add("Laguna");
		txtCidades.getItems().add("Imbituba");
		txtCidades.getItems().add("Criciúma");
		txtCidades.getSelectionModel().select(0);
		
	}
	
	@FXML
	private void registrar() {
		Cliente cliente = new Cliente();
		
		cliente.setNome(txtCliente.getText());
		cliente.setCartao(ckCartao.isSelected());
		cliente.setBoleto(ckBoleto.isSelected());
		cliente.setDeposito(ckDeposito.isSelected());
		cliente.setCidade(txtCidades.getSelectionModel().getSelectedItem());
		
		clientes.add(cliente);
		
		System.out.println(cliente.getNome() + " - " + cliente.getCidade() + " - " + cliente.isBoleto()
		+ " - " + cliente.isCartao() + " - " + cliente.isDeposito());
		
	}
}
