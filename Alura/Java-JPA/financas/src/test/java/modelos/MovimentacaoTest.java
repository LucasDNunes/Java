package modelos;

import Util.JPAUtil;
import org.junit.Test;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;

public class MovimentacaoTest {

    @Test
    public void testMovimentacao(){
        Conta conta = Conta.builder()
                .agencia("0102")
                .banco("ITAU")
                .numero("1234")
                .titular("Jorge")
                .build();

        Movimentacao movimentacao = Movimentacao.builder()
                .data(Calendar.getInstance())
                .descricao("churras")
                .tipo(TipoMovimentacao.SAIDA)
                .valor(new BigDecimal(200.0))
                .conta(conta)
                .build();

        EntityManager manager = new JPAUtil().getEntityManager();
        manager.getTransaction().begin();

        manager.persist(conta);
        manager.persist(movimentacao);

        manager.getTransaction().commit();
        manager.close();
    }

    @Test
    public void testMovimentacaoComCategoria(){

        Conta conta = Conta.builder()
                .id(15L)
                .build();

        Categoria categoria1 = Categoria.builder()
                .nome("Viagem")
                .build();

        Categoria categoria2 = Categoria.builder()
                .nome("Negocios")
                .build();

        Movimentacao movimentacao1 = Movimentacao.builder()
                .conta(conta)
                .data(Calendar.getInstance())
                .descricao("Viagem SP")
                .tipo(TipoMovimentacao.SAIDA)
                .valor(new BigDecimal(100.0))
                .categoria(Arrays.asList(categoria1,categoria2))
                .build();

        Movimentacao movimentacao2 = Movimentacao.builder()
                .conta(conta)
                .data(Calendar.getInstance())
                .descricao("Viagem RJ")
                .tipo(TipoMovimentacao.SAIDA)
                .valor(new BigDecimal(300.0))
                .categoria(Arrays.asList(categoria1,categoria2))
                .build();

        EntityManager manager = new JPAUtil().getEntityManager();
        manager.getTransaction().begin();

        manager.persist(categoria1);
        manager.persist(categoria2);

        manager.persist(movimentacao1);
        manager.persist(movimentacao2);


        manager.getTransaction().commit();
        manager.close();
    }
}
