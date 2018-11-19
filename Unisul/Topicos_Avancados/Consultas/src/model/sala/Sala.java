package model.sala;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Sala {
	
	private Long id;
	private String nome;
	private Integer numero;

	@Override
	public String toString() {
		return this.nome;
	}
}
