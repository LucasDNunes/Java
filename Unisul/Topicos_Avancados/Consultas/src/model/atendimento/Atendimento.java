package model.atendimento;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import model.sala.Sala;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Atendimento {
	
	private Long id;
	private Sala sala;
	private LocalDate data;
	//private Estagiario estagiario;
	
}
