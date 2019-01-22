package dao;

import modelos.Conta;
import modelos.TipoMovimentacao;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class MovimentacaoDao {

    private EntityManager manager;

    public MovimentacaoDao(EntityManager manager) {
        this.manager = manager;
    }

    public List<Double> getMediaPorDiaETipo(TipoMovimentacao saida, Conta conta) {

        String jqlt = "SELECT AVG(m.valor) FROM Movimentacao m WHERE m.conta = :pConta " +
                "AND m.tipo = :pTipo " +
                "GROUP BY DAY(m.data), MONTH(data), YEAR(data)";

        TypedQuery<Double> query = manager.createQuery(jqlt, Double.class);
        query.setParameter("pConta", conta);
        query.setParameter("pTipo", saida);

        return query.getResultList();
    }
}
