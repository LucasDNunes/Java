package model;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.control.ProgressBar;

public class Atleta extends Task<Void>{
	
	private Double distancia;
	private Integer tempo;
	private ProgressBar barra;
	

	public Atleta(Double distancia, Integer tempo, ProgressBar barra) {
		this.distancia = distancia;
		this.tempo = tempo;
		this.barra = barra;
	}
	
	@Override
	protected Void call() throws Exception {
		double incremento = 1.0/distancia;
		
		for (int i = 0; i < distancia.intValue(); i++) {
			try {
				Thread.sleep(tempo * 1000);
				Platform.runLater(() -> barra.setProgress(barra.getProgress() + incremento));
			} catch (Exception e) {
				System.out.println("----ERRO O SEU MERDA-----");
				e.printStackTrace();
			}
		}
		return null;
	}
	
	
	public ProgressBar getBarra() {
		return barra;
	}

	public void setBarra(ProgressBar barra) {
		this.barra = barra;
	}
	public Double getDistancia() {
		return distancia;
	}
	public void setDistancia(Double distancia) {
		this.distancia = distancia;
	}
	public Integer getTempo() {
		return tempo;
	}
	public void setTempo(Integer tempo) {
		this.tempo = tempo;
	}
}
