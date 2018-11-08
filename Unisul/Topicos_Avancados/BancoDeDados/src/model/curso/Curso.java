package model.curso;

import javafx.beans.property.StringProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Curso {
	
	private Long id;
	private StringProperty curso;
	
	@Override
	public String toString() {
		return curso.getValue();
	}
}
