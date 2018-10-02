package model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(of= {"nome","cidade","cartao","boleto","deposito"})
public class Cliente {
	@Builder.Default
	private StringProperty nome = new SimpleStringProperty("");
	@Builder.Default
	private StringProperty cidade = new SimpleStringProperty("");
	@Builder.Default
	private BooleanProperty cartao = new SimpleBooleanProperty(false);
	@Builder.Default
	private BooleanProperty boleto = new SimpleBooleanProperty(false);
	@Builder.Default
	private BooleanProperty deposito = new SimpleBooleanProperty(false);
	
	public final StringProperty nomeProperty() {
		return this.nome;
	}
	
	public final String getNome() {
		return this.nomeProperty().get();
	}
	
	public final void setNome(final String nome) {
		this.nomeProperty().set(nome);
	}
	
	public final StringProperty cidadeProperty() {
		return this.cidade;
	}
	
	public final String getCidade() {
		return this.cidadeProperty().get();
	}
	
	public final void setCidade(final String cidade) {
		this.cidadeProperty().set(cidade);
	}
	
	public final BooleanProperty cartaoProperty() {
		return this.cartao;
	}
	
	public final boolean isCartao() {
		return this.cartaoProperty().get();
	}
	
	public final void setCartao(final boolean cartao) {
		this.cartaoProperty().set(cartao);
	}
	
	public final BooleanProperty boletoProperty() {
		return this.boleto;
	}
	
	public final boolean isBoleto() {
		return this.boletoProperty().get();
	}
	
	public final void setBoleto(final boolean boleto) {
		this.boletoProperty().set(boleto);
	}
	
	public final BooleanProperty depositoProperty() {
		return this.deposito;
	}
	
	public final boolean isDeposito() {
		return this.depositoProperty().get();
	}
	
	public final void setDeposito(final boolean deposito) {
		this.depositoProperty().set(deposito);
	}
	
	
}
