
package model;

/**
 *
 * @author Felipe
 */

// Importações necessárias
import java.io.Serializable;

// A classe PessoaFisica herda da classe Pessoa e representa uma pessoa física com CPF e idade.
public class PessoaFisica extends Pessoa implements Serializable {
    private String cpf;      // O CPF da pessoa física.
    private int idade;       // A idade da pessoa física.

    // Construtor padrão vazio.
    public PessoaFisica() {
    }

    // Construtor que recebe o ID, nome, CPF e idade da pessoa física como parâmetros.
    public PessoaFisica(int id, String nome, String cpf, int idade) {
        super(id, nome);   // Chama o construtor da classe pai (Pessoa) para inicializar o ID e o nome.
        this.cpf = cpf;
        this.idade = idade;
    }

    // Sobrescreve o método exibir() da classe pai para incluir informações específicas de PessoaFisica.
    @Override
    public void exibir() {
        super.exibir();               // Chama o método exibir() da classe pai (Pessoa) para mostrar ID e nome.
        System.out.println("CPF: " + cpf);
        System.out.println("Idade: " + idade);
    }

    // Getter para obter o CPF da pessoa física.
    public String getCpf() {
        return cpf;
    }

    // Setter para definir o CPF da pessoa física.
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    // Getter para obter a idade da pessoa física.
    public int getIdade() {
        return idade;
    }

    // Setter para definir a idade da pessoa física.
    public void setIdade(int idade) {
        this.idade = idade;
    }
}
