package model.produto;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Produto {
	
	private IntegerProperty cod = new SimpleIntegerProperty();
	private StringProperty nome = new SimpleStringProperty();
	private DoubleProperty valor = new SimpleDoubleProperty();
	private IntegerProperty quantidade = new SimpleIntegerProperty();
	
	
	public IntegerProperty codProperty() {
		return this.cod;
	}
	
	public int getCod() {
		return this.codProperty().get();
	}
	
	public void setCod(final int cod) {
		this.codProperty().set(cod);
	}
	
	public StringProperty nomeProperty() {
		return this.nome;
	}
	
	public String getNome() {
		return this.nomeProperty().get();
	}
	
	public void setNome(final String nome) {
		this.nomeProperty().set(nome);
	}
	
	public DoubleProperty valorProperty() {
		return this.valor;
	}
	
	public double getValor() {
		return this.valorProperty().get();
	}
	
	public void setValor(final double valor) {
		this.valorProperty().set(valor);
	}
	
	public IntegerProperty quantidadeProperty() {
		return this.quantidade;
	}
	
	public int getQuantidade() {
		return this.quantidadeProperty().get();
	}
	
	public void setQuantidade(final int quantidade) {
		this.quantidadeProperty().set(quantidade);
	}
	
}
