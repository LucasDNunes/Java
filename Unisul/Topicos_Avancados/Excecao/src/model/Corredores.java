package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Corredores {
	
	private StringProperty nome = new SimpleStringProperty();
	private IntegerProperty numeroPeito = new SimpleIntegerProperty();
	private IntegerProperty idade = new SimpleIntegerProperty();
	private IntegerProperty distancia = new SimpleIntegerProperty();
	
	
	public final StringProperty nomeProperty() {
		return this.nome;
	}
	
	public final String getNome() {
		return this.nomeProperty().get();
	}
	
	public final void setNome(final String nome) {
		this.nomeProperty().set(nome);
	}
	
	public final IntegerProperty numeroPeitoProperty() {
		return this.numeroPeito;
	}
	
	public final int getNumeroPeito() {
		return this.numeroPeitoProperty().get();
	}
	
	public final void setNumeroPeito(final int numeroPeito) {
		this.numeroPeitoProperty().set(numeroPeito);
	}
	
	public final IntegerProperty idadeProperty() {
		return this.idade;
	}
	
	public final int getIdade() {
		return this.idadeProperty().get();
	}
	
	public final void setIdade(final int idade) {
		this.idadeProperty().set(idade);
	}
	
	public final IntegerProperty distanciaProperty() {
		return this.distancia;
	}
	
	public final int getDistancia() {
		return this.distanciaProperty().get();
	}
	
	public final void setDistancia(final int distancia) {
		this.distanciaProperty().set(distancia);
	}
	
	
}
