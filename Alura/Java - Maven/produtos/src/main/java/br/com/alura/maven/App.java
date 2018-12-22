package br.com.alura.maven;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {

        Produto produtos = new Produto("Bala juquinha", 0.15);

        System.out.println("A bala que eu gosto é "+ produtos.getNome() + "e custa " + produtos.getPreco());

    }
}
