package model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Produto {
	
	@Builder.Default
	private StringProperty nome = new SimpleStringProperty("");
	@Builder.Default
	private DoubleProperty valor = new SimpleDoubleProperty(0.0);
	@Builder.Default
	private IntegerProperty quantidade = new SimpleIntegerProperty(0);
	@Builder.Default
	private DoubleProperty subTotal = new SimpleDoubleProperty(0);
	
	public final StringProperty nomeProperty() {
		return this.nome;
	}
	
	public final String getNome() {
		return this.nomeProperty().get();
	}
	
	public final void setNome(final String nome) {
		this.nomeProperty().set(nome);
	}

	public final IntegerProperty quantidadeProperty() {
		return this.quantidade;
	}
	

	public final int getQuantidade() {
		return this.quantidadeProperty().get();
	}
	

	public final void setQuantidade(final int quantidade) {
		this.quantidadeProperty().set(quantidade);
	}

	public final DoubleProperty valorProperty() {
		return this.valor;
	}
	

	public final double getValor() {
		return this.valorProperty().get();
	}
	

	public final void setValor(final double valor) {
		this.valorProperty().set(valor);
	}

	public final DoubleProperty subTotalProperty() {
		return this.subTotal;
	}
	

	public final double getSubTotal() {
		return this.subTotalProperty().get();
	}
	

	public final void setSubTotal(final double subTotal) {
		this.subTotalProperty().set(subTotal);
	}
	

	
	
	
}
