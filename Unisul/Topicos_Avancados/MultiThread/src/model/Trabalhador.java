package model;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.control.ProgressBar;

public class Trabalhador extends Task<Void>{
	private Integer quantidade;
	private Integer tempo;
	private ProgressBar barra;
	
	public Trabalhador(Integer quantidade, Integer tempo, ProgressBar barra) {
		this.quantidade = quantidade;
		this.tempo = tempo;
		this.barra = barra;
		barra.setProgress(0);
	}
	
	public void iniciar() {
		barra.setProgress(0);
		double incremento = 1.0/quantidade;
		for (int i = 0; i <quantidade ; i++) {
			try {
				Thread.sleep(tempo);
				barra.setProgress(barra.getProgress() + incremento );
			} catch (Exception e) {
				System.out.println("DEU ERRO SEU MERDA");
				e.printStackTrace();
			}
		}
	}
	
	@Override
	protected Void call() throws Exception {
		double incremento = 1.0/quantidade;
		for (int i = 0; i < quantidade; i++) {
			try {
				Thread.sleep(tempo);
				Platform.runLater(() -> {
					barra.setProgress(barra.getProgress() + incremento);
				});
				
				//ou
//				Platform.runLater(new Runnable() {
//					@Override
//					public void run() {
//						barra.setProgress(barra.getProgress() + incremento);
//					}
				
			} catch (Exception e) {
				System.out.println("ERRO O SEU MERDA");
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	public Integer getTempo() {
		return tempo;
	}
	public void setTempo(Integer tempo) {
		this.tempo = tempo;
	}
	public ProgressBar getBarra() {
		return barra;
	}
	public void setBarra(ProgressBar barra) {
		this.barra = barra;
	}

	
	
	
}
