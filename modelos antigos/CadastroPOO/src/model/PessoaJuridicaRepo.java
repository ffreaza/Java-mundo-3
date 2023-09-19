package model;

/**
 *
 * @author felipe
 */

import java.io.*;
import java.util.ArrayList;

public class PessoaJuridicaRepo {
    private ArrayList<PessoaJuridica> pessoasJuridicas = new ArrayList<>();

    public void inserir(PessoaJuridica pessoa) {
        pessoasJuridicas.add(pessoa);
    }

    public void alterar(PessoaJuridica pessoa) {
    }

    public void excluir(int id) {
    }

    public PessoaJuridica obter(int id) {
        return null;
    }

    public ArrayList<PessoaJuridica> obterTodos() {
        return pessoasJuridicas;
    }

    public void persistir(String arquivo) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(arquivo));
        out.writeObject(pessoasJuridicas);
        out.close();
    }

    public void recuperar(String arquivo) throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(arquivo));
        pessoasJuridicas = (ArrayList<PessoaJuridica>) in.readObject();
        in.close();
    }
}

