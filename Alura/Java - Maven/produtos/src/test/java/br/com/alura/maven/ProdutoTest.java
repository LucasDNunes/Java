package br.com.alura.maven;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProdutoTest {

    @Test
    public void verificaPrecoComImposto(){

        Produto bala = new Produto("juquinha", 0.10);
        Assertions.assertEquals(0.11, bala.getPrecoComImposto(), 0.00001);
    }
}
