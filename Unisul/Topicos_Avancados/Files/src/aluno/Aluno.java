package aluno;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Aluno {
	
	private StringProperty nome = new SimpleStringProperty();
	private IntegerProperty semestre = new SimpleIntegerProperty();
	private StringProperty curso = new SimpleStringProperty();
	
	
	public StringProperty nomeProperty() {
		return this.nome;
	}
	
	public String getNome() {
		return this.nomeProperty().get();
	}
	
	public void setNome(final String nome) {
		this.nomeProperty().set(nome);
	}
	
	public IntegerProperty semestreProperty() {
		return this.semestre;
	}
	
	public int getSemestre() {
		return this.semestreProperty().get();
	}
	
	public void setSemestre(final int semestre) {
		this.semestreProperty().set(semestre);
	}
	
	public StringProperty cursoProperty() {
		return this.curso;
	}
	
	public String getCurso() {
		return this.cursoProperty().get();
	}
	
	public void setCurso(final String curso) {
		this.cursoProperty().set(curso);
	}
	
}
