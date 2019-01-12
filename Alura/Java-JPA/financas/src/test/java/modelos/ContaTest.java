package modelos;

import Util.JPAUtil;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.EntityManager;

public class ContaTest {

    @Test
    public void testCreate() {
        Conta conta = Conta.builder()
                .titular("Leonardo")
                .agencia("123")
                .banco("Caixa Economica")
                .numero("456")
                .build();

        EntityManager manager = new JPAUtil().getEntityManager();
        manager.getTransaction().begin();

        manager.persist(conta);
        Assert.assertNotNull(conta.getId());

        manager.getTransaction().commit();

        manager.remove(conta);
        manager.close();
    }

    @Test
    public void populaBanco() {

        EntityManager manager = new JPAUtil().getEntityManager();
        manager.getTransaction().begin();
//        manager.createQuery("delete from Conta").executeUpdate();

        Conta conta = Conta.builder()
                .banco("001 - BANCO DO BRASIL")
                .numero("16987-8")
                .agencia("6543")
                .titular("Maria dos Santos")
                .build();

        manager.persist(conta);

        conta = Conta.builder()
                .banco("237 - BANCO BRADESCO")
                .numero("86759-1")
                .agencia("1745")
                .titular("Paulo Roberto Souza")
                .build();

        manager.persist(conta);

        conta = Conta.builder()
                .banco("341 - BANCO ITAU UNIBANCO")
                .numero("46346-3")
                .agencia("4606")
                .titular("Antonio Duraes")
                .build();
        manager.persist(conta);

        conta = Conta.builder()
                .banco("033 - BANCO SANTANDER")
                .numero("12345-6")
                .agencia("9876")
                .titular("Leandra Marques")
                .build();

        manager.persist(conta);

        conta = Conta.builder()
                .banco("104 - CAIXA ECONOMICA FEDERAL")
                .numero("98654-3")
                .agencia("1234")
                .titular("Alexandre Duarte")
                .build();

        manager.persist(conta);

        manager.getTransaction().commit();
    }

    @Test
    public void testUpdateConta(){
        EntityManager manager = new JPAUtil().getEntityManager();
        manager.getTransaction().begin();

        Conta conta = manager.find(Conta.class,15L);

        Assert.assertNotNull(conta);

        conta.setTitular("João da Silva");

        manager.getTransaction().commit();

        Conta contaNova = manager.find(Conta.class, 15L);
        Assert.assertNotNull(contaNova);
        Assert.assertEquals(conta.getTitular(), contaNova.getTitular());

    }
}
