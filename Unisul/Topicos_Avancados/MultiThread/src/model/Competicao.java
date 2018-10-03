package model;


import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Competicao {
	
	
	private StringProperty nome = new SimpleStringProperty("");
	private DoubleProperty distancia = new SimpleDoubleProperty(0.0);
	private IntegerProperty colocacao = new SimpleIntegerProperty(0);
	private StringProperty data = new SimpleStringProperty("");
	
	
	
	public StringProperty nomeProperty() {
		return this.nome;
	}
	

	public String getNome() {
		return this.nomeProperty().get();
	}
	
	public void setNome(final String nome) {
		this.nomeProperty().set(nome);
	}
	
	public DoubleProperty distanciaProperty() {
		return this.distancia;
	}
	
	public double getDistancia() {
		return this.distanciaProperty().get();
	}
	
	public void setDistancia(final double distancia) {
		this.distanciaProperty().set(distancia);
	}
	
	public IntegerProperty colocacaoProperty() {
		return this.colocacao;
	}
	
	public int getColocacao() {
		return this.colocacaoProperty().get();
	}
	
	public void setColocacao(final int colocacao) {
		this.colocacaoProperty().set(colocacao);
	}


	public StringProperty dataProperty() {
		return this.data;
	}
	


	public String getData() {
		return this.dataProperty().get();
	}
	


	public void setData(final String data) {
		this.dataProperty().set(data);
	}
	
}
