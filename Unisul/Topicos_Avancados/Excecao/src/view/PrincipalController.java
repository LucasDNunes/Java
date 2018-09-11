package view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;

public class PrincipalController {
	@FXML TextField txtTamanho;
	@FXML TextField txtNumeroIncluir;
	@FXML TextField txtPosicao;
	
	private int[] vetorNumero;
	
	@FXML
	public boolean criarVetorSemTry() {
		if (!txtTamanho.getText().isEmpty()) {
			int tamanho = Integer.parseInt(txtTamanho.getText());
			vetorNumero = new int[tamanho];			
			return true;
		}
		return false;
	}
	
	@FXML
	public void criarVetor() throws NegativeArraySizeException, NumberFormatException {
		try {
			int tamanho = Integer.parseInt(txtTamanho.getText());
			if (tamanho > 10) {
				throw new NumberFormatException("limite 10");
			}
			vetorNumero = new int[tamanho];
		} catch (NumberFormatException e) {
			mostraMensagem("erro de conversao \n"+ e.toString(), AlertType.ERROR);
			txtTamanho.requestFocus();
			txtTamanho.selectAll();
		} catch (NegativeArraySizeException e) {
			mostraMensagem("Não pode ser negativo \n"+ e.toString() , AlertType.ERROR);
			txtTamanho.requestFocus();
			txtTamanho.selectAll();
		} catch (Exception e) {
			mostraMensagem("ERROR \n"+ e.toString() , AlertType.ERROR);
			txtTamanho.requestFocus();
			txtTamanho.selectAll();
		} 
	}
	
	@FXML
	public void inserirSemTry() {
		if (criarVetorSemTry() && retornaInteiro(txtPosicao.getText()) > 0) {
			int numero = Integer.parseInt(txtNumeroIncluir.getText());
			int posicao = Integer.parseInt(txtPosicao.getText());
			insereNoVetor(posicao, numero);
			mostraMensagem("número incluido com sucesso", AlertType.INFORMATION);			
		} else {
			mostraMensagem("preencha o campo de tamanho", AlertType.ERROR);
		}
	}
	
	@FXML
	public void inserir() {
		try {
			
			int numero = Integer.parseInt(txtNumeroIncluir.getText());
			int posicao = Integer.parseInt(txtPosicao.getText());
			insereNoVetor(posicao, numero);
			mostraMensagem("número incluido com sucesso", AlertType.INFORMATION);			
		}catch (NumberFormatException e) {
			mostraMensagem("errro na conversao número", AlertType.INFORMATION);
		}catch (NegativeArraySizeException e) {
			mostraMensagem("numero negativo no campo posição", AlertType.INFORMATION);
		}catch (ArrayIndexOutOfBoundsException e) {
			mostraMensagem("tamanho invalido", AlertType.INFORMATION);
		}catch (NullPointerException e) {
			mostraMensagem("vetor nao instanciado", AlertType.INFORMATION);
		} catch (Exception e) {
			mostraMensagem("erro desconhecido", AlertType.INFORMATION);
		} finally {
			mostraMensagem("novas", AlertType.WARNING);
			
		}
	}
	
	private void insereNoVetor(int posicao, int numero) throws ArrayIndexOutOfBoundsException{
		try {
			vetorNumero[posicao -1 ] = numero;
		} catch (ArrayIndexOutOfBoundsException e) {
			mostraMensagem("posição nao existente", AlertType.INFORMATION);
			throw e;
		}
	}
	
	private int retornaInteiro(String text) {
		return Integer.parseInt(text);
	}

	private void mostraMensagem(String msg, AlertType tipoMensagem) {
		Alert alerta = new Alert(tipoMensagem);
		alerta.setHeaderText(null);
		alerta.setContentText(msg);
		alerta.show();
	}
}
