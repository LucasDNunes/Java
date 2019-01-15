package modelos;

import Util.JPAUtil;
import org.junit.Test;

import javax.persistence.EntityManager;

public class ClienteTest {

    @Test
    public void testCreate(){
        Conta conta = Conta.builder()
                .id(15L)
                .build();

        Cliente cliente = Cliente.builder()
                .nome("Leonardo")
                .Endereco("Rua Fulano, 123")
                .profissao("Professor")
                .conta(conta)
                .build();

        EntityManager manager = new JPAUtil().getEntityManager();
        manager.getTransaction().begin();

        manager.persist(cliente);

        manager.getTransaction().commit();
        manager.close();
    }
}
