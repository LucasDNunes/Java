package model.transporte;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Transporte {
	
	private StringProperty tipoTranposte = new SimpleStringProperty("");
	private DoubleProperty distancia = new SimpleDoubleProperty(0);
	private DoubleProperty custoMensal = new SimpleDoubleProperty(0);
	
	
	public StringProperty tipoTranposteProperty() {
		return this.tipoTranposte;
	}
	
	public String getTipoTranposte() {
		return this.tipoTranposteProperty().get();
	}
	
	public void setTipoTranposte(final String tipoTranposte) {
		this.tipoTranposteProperty().set(tipoTranposte);
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
	
	public DoubleProperty custoMensalProperty() {
		return this.custoMensal;
	}
	
	public double getCustoMensal() {
		return this.custoMensalProperty().get();
	}
	
	public void setCustoMensal(final double custoMensal) {
		this.custoMensalProperty().set(custoMensal);
	}
	
}
