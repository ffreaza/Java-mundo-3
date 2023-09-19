package model;

/**
 *
 * @author felipe
 */

import java.io.*;
import java.util.ArrayList;

public class PessoaFisicaRepo {
    private ArrayList<PessoaFisica> pessoasFisicas = new ArrayList<>();

    public void inserir(PessoaFisica Pessoa) {
        pessoasFisicas.add(Pessoa);
    }

    public void alterar(PessoaFisica Pessoa) {
    }

    public void excluir(int id) {
    }

    public PessoaFisica obter(int id) {
        return null;
    }

    public ArrayList<PessoaFisica> obterTodos() {
        return pessoasFisicas;
    }

    public void persistir(String arquivo) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(arquivo));
        out.writeObject(pessoasFisicas);
        out.close();
    }

    public void recuperar(String arquivo) throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(arquivo));
        pessoasFisicas = (ArrayList<PessoaFisica>) in.readObject();
        in.close();
    }
}

