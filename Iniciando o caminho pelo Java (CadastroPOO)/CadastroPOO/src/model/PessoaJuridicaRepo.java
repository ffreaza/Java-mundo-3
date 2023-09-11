package model;

/**
 *
 * @author Felipe
 */

// Importações necessárias
import java.io.*;
import java.util.ArrayList;

// A classe PessoaJuridicaRepo é responsável por gerenciar um repositório de pessoas jurídicas.
public class PessoaJuridicaRepo {
    private ArrayList<PessoaJuridica> pessoasJuridicas = new ArrayList<>();

    // Método para inserir uma pessoa jurídica no repositório.
    public void inserir(PessoaJuridica pessoa) {
        pessoasJuridicas.add(pessoa);
    }

    // Método para alterar os dados de uma pessoa jurídica no repositório.
    public void alterar(PessoaJuridica pessoa) {
        for (int i = 0; i < pessoasJuridicas.size(); i++) {
            PessoaJuridica p = pessoasJuridicas.get(i);
            if (p.getId() == pessoa.getId()) {
                pessoasJuridicas.set(i, pessoa);
                break;
            }
        }
    }

    // Método para excluir uma pessoa jurídica do repositório com base em seu ID.
    public void excluir(int id) {
        for (int i = 0; i < pessoasJuridicas.size(); i++) {
            PessoaJuridica p = pessoasJuridicas.get(i);
            if (p.getId() == id) {
                pessoasJuridicas.remove(i);
                break;
            }
        }
    }

    // Método para obter uma pessoa jurídica do repositório com base em seu ID.
    public PessoaJuridica obter(int id) {
        for (PessoaJuridica pessoa : pessoasJuridicas) {
            if (pessoa.getId() == id) {
                return pessoa;
            }
        }
        return null;
    }

    // Método para obter todas as pessoas jurídicas do repositório.
    public ArrayList<PessoaJuridica> obterTodos() {
        return pessoasJuridicas;
    }

    // Método para persistir (salvar) os dados das pessoas jurídicas em um arquivo.
    public void persistir(String arquivo) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(arquivo));
        out.writeObject(pessoasJuridicas);
        out.close();
    }

    // Método para recuperar (carregar) os dados das pessoas jurídicas de um arquivo.
    public void recuperar(String arquivo) throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(arquivo));
        pessoasJuridicas = (ArrayList<PessoaJuridica>) in.readObject();
        in.close();
    }
}
