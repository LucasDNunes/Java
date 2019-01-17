package modelos;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String profissao;

    private String Endereco;

    @JoinColumn(unique = true)
    @OneToOne
    private Conta conta;
}
