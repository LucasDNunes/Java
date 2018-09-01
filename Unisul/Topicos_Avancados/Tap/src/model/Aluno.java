package model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import view.ExemploCadastroController;
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Aluno {
	
	@Builder.Default
	private StringProperty nome = new SimpleStringProperty("");
	@Builder.Default
	private StringProperty sexo = new SimpleStringProperty("");
	@Builder.Default
	private StringProperty dataNasc = new SimpleStringProperty("");
	@Builder.Default
	private StringProperty UF = new SimpleStringProperty("");
	@Builder.Default
	private BooleanProperty mat = new SimpleBooleanProperty(false);
	@Builder.Default
	private BooleanProperty ves = new SimpleBooleanProperty(false);
	@Builder.Default
	private BooleanProperty not = new SimpleBooleanProperty(false);
	@Builder.Default
	private IntegerProperty idade = new SimpleIntegerProperty(0);
	
	
	@FXML TextField txtNome;
	@FXML TextField txtFiltro;
	
	@FXML RadioButton rdMasc;
	@FXML RadioButton rdFem;
	
	@FXML ComboBox<String> uf;
	
	@FXML DatePicker txtDataNascimento;
	
	@FXML CheckBox checkMatutino;
	@FXML CheckBox checkVespertino;
	@FXML CheckBox checkNoturno;
	
	public StringProperty nomeProperty() {
		return this.nome;
	}

	public String getNome() {
		return this.nomeProperty().get();
	}

	public void setNome(final String nome) {
		this.nomeProperty().set(nome);
	}
	


	public StringProperty sexoProperty() {
		return this.sexo;
	}
	


	public String getSexo() {
		return this.sexoProperty().get();
	}
	


	public void setSexo(final String sexo) {
		this.sexoProperty().set(sexo);
	}
	


	public StringProperty dataNascProperty() {
		return this.dataNasc;
	}
	


	public String getDataNasc() {
		return this.dataNascProperty().get();
	}
	


	public void setDataNasc(final String dataNasc) {
		this.dataNascProperty().set(dataNasc);
	}
	


	public StringProperty UFProperty() {
		return this.UF;
	}
	


	public String getUF() {
		return this.UFProperty().get();
	}
	


	public void setUF(final String UF) {
		this.UFProperty().set(UF);
	}
	


	public BooleanProperty matProperty() {
		return this.mat;
	}
	


	public boolean isMat() {
		return this.matProperty().get();
	}
	


	public void setMat(final boolean mat) {
		this.matProperty().set(mat);
	}
	


	public BooleanProperty vesProperty() {
		return this.ves;
	}
	


	public boolean isVep() {
		return this.vesProperty().get();
	}
	


	public void setVes(final boolean vep) {
		this.vesProperty().set(vep);
	}
	


	public BooleanProperty notProperty() {
		return this.not;
	}
	


	public boolean isNot() {
		return this.notProperty().get();
	}
	


	public void setNot(final boolean not) {
		this.notProperty().set(not);
	}
	


	public IntegerProperty idadeProperty() {
		return this.idade;
	}
	


	public int getIdade() {
		return this.idadeProperty().get();
	}
	


	public void setIdade(final int idade) {
		this.idadeProperty().set(idade);
	}
	


}
