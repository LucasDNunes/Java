package model;

import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.concurrent.Task;
import javafx.scene.control.ProgressBar;

public class Funcionario extends Task<Void> {

	private StringProperty nome = new SimpleStringProperty();
	private IntegerProperty quantidadeProdutos = new SimpleIntegerProperty();
	private IntegerProperty tempo = new SimpleIntegerProperty();
	private ProgressBar barraTempo;

	@Override
	protected Void call() throws Exception {
		barraTempo.setProgress(0.0);
		double incremento = 1.0 / quantidadeProdutos.intValue();

		for (int i = 0; i < quantidadeProdutos.intValue(); i++) {
			try {
				Thread.sleep(tempo.intValue());
				Platform.runLater(() -> barraTempo.setProgress(barraTempo.getProgress() + incremento));
			} catch (Exception e) {
				System.out.println("----ERRO O SEU MERDA-----");
				e.printStackTrace();
			}
		}
		return null;
	}

	public ProgressBar getBarraTempo() {
		return barraTempo;
	}

	public void setBarraTempo(ProgressBar barraTempo) {
		this.barraTempo = barraTempo;
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

	public IntegerProperty quantidadeProdutosProperty() {
		return this.quantidadeProdutos;
	}

	public int getQuantidadeProdutos() {
		return this.quantidadeProdutosProperty().get();
	}

	public void setQuantidadeProdutos(final int quantidadeProdutos) {
		this.quantidadeProdutosProperty().set(quantidadeProdutos);
	}

	public IntegerProperty tempoProperty() {
		return this.tempo;
	}

	public int getTempo() {
		return this.tempoProperty().get();
	}

	public void setTempo(final int tempo) {
		this.tempoProperty().set(tempo);
	}

}
