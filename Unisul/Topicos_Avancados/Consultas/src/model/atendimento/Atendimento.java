package model.atendimento;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import model.estagiario.Estagiario;
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
	private LocalTime horaAtendimentoInicio;
	private LocalTime horaAtendimentoFim;
	private Estagiario estagiario;
	
}
