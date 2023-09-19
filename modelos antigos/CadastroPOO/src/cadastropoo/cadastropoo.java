package cadastropoo;

/**
 *
 * @author felipe
 */


import java.io.IOException;
import java.util.ArrayList;
import model.PessoaFisica;
import model.PessoaFisicaRepo;
import model.PessoaJuridica;
import model.PessoaJuridicaRepo;


public class cadastropoo {
    public static void main(String[] args) {
        PessoaFisicaRepo repo1 = new PessoaFisicaRepo();
        PessoaFisica pessoaFisica1 = new PessoaFisica(327, "Rodrigo Oliveira", "63338168330", 42);
        PessoaFisica pessoaFisica2 = new PessoaFisica(582, "Marina Santos", "74089572355", 39);
        repo1.inserir(pessoaFisica1);
        repo1.inserir(pessoaFisica2);

        try {
            repo1.persistir("pessoas_fisicas.bin");
        } catch (IOException e) {
            e.printStackTrace();
        }

        PessoaFisicaRepo repo2 = new PessoaFisicaRepo();

        try {
            repo2.recuperar("pessoas_fisicas.bin");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        ArrayList<PessoaFisica> pessoasFisicasRecuperadas = repo2.obterTodos();

        System.out.println("Dados de Pessoas Físicas Armazenados.");
        System.out.println("Dados de Pessoas Físicas Recuperadas:");
        for (PessoaFisica pessoaFisica : pessoasFisicasRecuperadas) {
            pessoaFisica.exibir();
            System.out.println();
        }

        PessoaJuridicaRepo repo3 = new PessoaJuridicaRepo();
        PessoaJuridica pessoaJuridica1 = new PessoaJuridica(192, "InovaSoft Tecnologia", "67551068000130");
        PessoaJuridica pessoaJuridica2 = new PessoaJuridica(739, "Soluções NovaTech", "17601791000108");
        repo3.inserir(pessoaJuridica1);
        repo3.inserir(pessoaJuridica2);

        try {
            repo3.persistir("pessoas_juridicas.bin");
        } catch (IOException e) {
            e.printStackTrace();
        }

        PessoaJuridicaRepo repo4 = new PessoaJuridicaRepo();

        try {
            repo4.recuperar("pessoas_juridicas.bin");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        ArrayList<PessoaJuridica> pessoasJuridicasRecuperadas = repo4.obterTodos();

        System.out.println("Dados de Pessoas Jurídicas Armazenados.");
        System.out.println("Dados de Pessoas Jurídicas Recuperadas:");
        for (PessoaJuridica pessoaJuridica : pessoasJuridicasRecuperadas) {
            pessoaJuridica.exibir();
            System.out.println();
        }
    }
}