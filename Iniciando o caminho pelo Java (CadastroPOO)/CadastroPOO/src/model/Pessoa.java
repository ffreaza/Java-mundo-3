package model;

/**
 *
 * @author Felipe
 */

// Importações necessárias
import java.io.Serializable;

// A classe Pessoa representa uma entidade básica para informações de pessoa.
public class Pessoa implements Serializable {
    private int id;       // O identificador único da pessoa.
    private String nome;  // O nome da pessoa.

    // Construtor padrão vazio.
    public Pessoa() {
    }

    // Construtor que recebe o ID e o nome da pessoa como parâmetros.
    public Pessoa(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    // Método para exibir informações da pessoa.
    public void exibir() {
        System.out.println("ID: " + id);
        System.out.println("Nome: " + nome);
    }

    // Getter para obter o ID da pessoa.
    public int getId() {
        return id;
    }

    // Setter para definir o ID da pessoa.
    public void setId(int id) {
        this.id = id;
    }

    // Getter para obter o nome da pessoa.
    public String getNome() {
        return nome;
    }

    // Setter para definir o nome da pessoa.
    public void setNome(String nome) {
        this.nome = nome;
    }
}
