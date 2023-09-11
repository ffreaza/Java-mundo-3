package model;

/**
 *
 * @author Felipe
 */

// Importações necessárias
import java.io.*;
import java.util.ArrayList;

// A classe PessoaFisicaRepo é responsável por gerenciar um repositório de pessoas físicas.
public class PessoaFisicaRepo {
    private ArrayList<PessoaFisica> pessoasFisicas = new ArrayList<>();

    // Método para inserir uma pessoa física no repositório.
    public void inserir(PessoaFisica pessoa) {
        pessoasFisicas.add(pessoa);
    }

    // Método para alterar os dados de uma pessoa física no repositório.
    public void alterar(PessoaFisica pessoa) {
        for (int i = 0; i < pessoasFisicas.size(); i++) {
            PessoaFisica p = pessoasFisicas.get(i);
            if (p.getId() == pessoa.getId()) {
                pessoasFisicas.set(i, pessoa);
                break;
            }
        }
    }

    // Método para excluir uma pessoa física do repositório com base em seu ID.
    public void excluir(int id) {
        for (int i = 0; i < pessoasFisicas.size(); i++) {
            PessoaFisica p = pessoasFisicas.get(i);
            if (p.getId() == id) {
                pessoasFisicas.remove(i);
                break;
            }
        }
    }

    // Método para obter uma pessoa física do repositório com base em seu ID.
    public PessoaFisica obter(int id) {
        for (PessoaFisica pessoa : pessoasFisicas) {
            if (pessoa.getId() == id) {
                return pessoa;
            }
        }
        return null;
    }

    // Método para obter todas as pessoas físicas do repositório.
    public ArrayList<PessoaFisica> obterTodos() {
        return pessoasFisicas;
    }

    // Método para persistir (salvar) os dados das pessoas físicas em um arquivo.
    public void persistir(String arquivo) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(arquivo));
        out.writeObject(pessoasFisicas);
        out.close();
    }

    // Método para recuperar (carregar) os dados das pessoas físicas de um arquivo.
    public void recuperar(String arquivo) throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(arquivo));
        pessoasFisicas = (ArrayList<PessoaFisica>) in.readObject();
        in.close();
    }
}
