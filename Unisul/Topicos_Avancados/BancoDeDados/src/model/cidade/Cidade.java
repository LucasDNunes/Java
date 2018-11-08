package model.cidade;

import javafx.beans.property.StringProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cidade {
	
	private Long id;
	private StringProperty cidade;
	private String uf;
	
	@Override
	public String toString() {
		return cidade.getValue();
	}
}
