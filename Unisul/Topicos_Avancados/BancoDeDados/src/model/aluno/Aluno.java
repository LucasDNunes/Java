package model.aluno;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import model.cidade.Cidade;
import model.curso.Curso;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Aluno {
	
	private Long id;
	private StringProperty nome;
	private IntegerProperty idade;
	
	private Cidade cidade;
	
	private Curso curso;
	
}
