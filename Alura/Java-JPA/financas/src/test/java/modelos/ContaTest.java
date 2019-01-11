package modelos;

import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ContaTest {

    @Test
    public void testCreate(){

        Conta conta = Conta.builder()
                .titular("Leonardo")
                .agencia("123")
                .banco("Caixa Economica")
                .numero("456")
                .build();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("financas");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        em.persist(conta);

        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
