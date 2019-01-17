package modelos;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titular;
    private String numero;
    private String banco;
    private String agencia;

    @OneToMany(mappedBy = "conta")
    private List<Movimentacao> movimentacoes;
}
