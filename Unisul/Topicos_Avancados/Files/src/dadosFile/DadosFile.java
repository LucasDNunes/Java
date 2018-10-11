package dadosFile;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DadosFile {
	private StringProperty nome = new SimpleStringProperty("");
	private StringProperty tamanho = new SimpleStringProperty("");
	private StringProperty path = new SimpleStringProperty("");
	
	public StringProperty nomeProperty() {
		return this.nome;
	}
	
	public String getNome() {
		return this.nomeProperty().get();
	}
	
	public void setNome(final String nome) {
		this.nomeProperty().set(nome);
	}
	
	public StringProperty tamanhoProperty() {
		return this.tamanho;
	}
	
	public String getTamanho() {
		return this.tamanhoProperty().get();
	}
	
	public void setTamanho(final String tamanho) {
		this.tamanhoProperty().set(tamanho);
	}

	public StringProperty pathProperty() {
		return this.path;
	}
	

	public String getPath() {
		return this.pathProperty().get();
	}
	

	public void setPath(final String path) {
		this.pathProperty().set(path);
	}
	
	
}
