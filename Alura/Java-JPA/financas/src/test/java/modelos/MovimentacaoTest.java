package modelos;

import Util.JPAUtil;
import dao.MovimentacaoDao;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

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

    @Test
    public void testBuscaJPQL() {
        EntityManager manager = new JPAUtil().getEntityManager();
        manager.getTransaction().begin();

        Conta conta = Conta.builder()
                .id(15L)
                .build();

        String jqlt = "SELECT m FROM Movimentacao m WHERE m.conta = :pConta " +
                "AND m.tipo = :pTipo " +
                "ORDER BY m.valor DESC";
        Query query = manager.createQuery(jqlt);
        query.setParameter("pConta", conta);
        query.setParameter("pTipo", TipoMovimentacao.SAIDA);

        List<Movimentacao> resultList = query.getResultList();


        resultList.forEach(r -> {
            System.out.println("Descrição ".concat(r.getDescricao()));
            System.out.println("Conta.id ".concat(r.getConta().getId().toString()));
            Assert.assertEquals(conta.getId(), r.getConta().getId());
        });


        manager.getTransaction().commit();
        manager.close();
    }

    @Test
    public void testBuscaPorCategoriaJPQL() {
        EntityManager manager = new JPAUtil().getEntityManager();
        manager.getTransaction().begin();

        Categoria categoria = Categoria.builder()
                .id(3L)
                .build();

        String jqlt = "SELECT m FROM Movimentacao m JOIN m.categoria c " +
                "WHERE c = :pCategoria";
        Query query = manager.createQuery(jqlt);
        query.setParameter("pCategoria", categoria);

        List<Movimentacao> resultList = query.getResultList();


        resultList.forEach(r -> {
            System.out.println("Descrição ".concat(r.getDescricao()));
            System.out.println("Conta.id ".concat(r.getConta().getId().toString()));
        });


        manager.getTransaction().commit();
        manager.close();
    }

    @Test
    public void testBuscaPorConta() {
        EntityManager manager = new JPAUtil().getEntityManager();
        manager.getTransaction().begin();

        Movimentacao movimentacao = manager.find(Movimentacao.class, 3L);
        Assert.assertNotNull(movimentacao);
        Conta conta = movimentacao.getConta();

        Assert.assertNotNull(conta);
        System.out.println(conta.getTitular());
        System.out.println(conta.getMovimentacoes().size());


        manager.getTransaction().commit();
        manager.close();
    }

    @Test
    public void testBuscaPorTodasContas() {
        EntityManager manager = new JPAUtil().getEntityManager();
        manager.getTransaction().begin();

        String jpql = "SELECT DISTINCT c FROM Conta c LEFT JOIN FETCH c.movimentacoes";

        Query query = manager.createQuery(jpql);

        List<Conta> contas = query.getResultList();
        Assert.assertNotNull(contas);

        contas.forEach(c -> {
            System.out.println("Titular: ".concat(c.getTitular()));
            System.out.println("Movimentacao: ".concat(c.getMovimentacoes().toString()));
        });

        manager.getTransaction().commit();
        manager.close();
    }

    @Test
    public void testSomaMovimetacoes() {
        EntityManager manager = new JPAUtil().getEntityManager();
        manager.getTransaction().begin();

        Conta conta = Conta.builder()
                .id(15L)
                .build();

        String jqlt = "SELECT SUM(m.valor) FROM Movimentacao m WHERE m.conta = :pConta " +
                "AND m.tipo = :pTipo " +
                "ORDER BY m.valor DESC";
        Query query = manager.createQuery(jqlt);
        query.setParameter("pConta", conta);
        query.setParameter("pTipo", TipoMovimentacao.SAIDA);

        BigDecimal result = (BigDecimal) query.getSingleResult();
        Assert.assertNotNull(result);

        System.out.println("Soma: ".concat(result.toString()));

        manager.getTransaction().commit();
        manager.close();
    }

    @Test
    public void testMediaMovimetacoes() {
        EntityManager manager = new JPAUtil().getEntityManager();
        manager.getTransaction().begin();

        Conta conta = Conta.builder()
                .id(15L)
                .build();

        String jqlt = "SELECT AVG(m.valor) FROM Movimentacao m WHERE m.conta = :pConta " +
                "AND m.tipo = :pTipo " +
                "ORDER BY m.valor DESC";
        Query query = manager.createQuery(jqlt);
        query.setParameter("pConta", conta);
        query.setParameter("pTipo", TipoMovimentacao.SAIDA);

        Double result = (Double) query.getSingleResult();
        Assert.assertNotNull(result);

        System.out.println("Média: ".concat(result.toString()));

        manager.getTransaction().commit();
        manager.close();
    }

    @Test
    public void testMediaPorDiaMovimetacoes() {
        EntityManager manager = new JPAUtil().getEntityManager();
        manager.getTransaction().begin();

        Conta conta = Conta.builder()
                .id(15L)
                .build();

        MovimentacaoDao dao = new MovimentacaoDao(manager);

        List<Double> result = dao.getMediaPorDiaETipo(TipoMovimentacao.SAIDA, conta);

        Assert.assertNotNull(result);

        System.out.println("Média dia 26: " + result.get(0));
        System.out.println("Média dia 27: " + result.get(1));

        manager.getTransaction().commit();
        manager.close();
    }
}
