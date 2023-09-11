package model;

/**
 *
 * @author Felipe
 */

// Importações necessárias
import java.io.Serializable;

// A classe PessoaJuridica herda da classe Pessoa e representa uma pessoa jurídica com CNPJ.
public class PessoaJuridica extends Pessoa implements Serializable {
    private String cnpj;  // O CNPJ da pessoa jurídica.

    // Construtor padrão vazio.
    public PessoaJuridica() {
    }

    // Construtor que recebe o ID, nome e CNPJ da pessoa jurídica como parâmetros.
    public PessoaJuridica(int id, String nome, String cnpj) {
        super(id, nome);   // Chama o construtor da classe pai (Pessoa) para inicializar o ID e o nome.
        this.cnpj = cnpj;
    }

    // Sobrescreve o método exibir() da classe pai para incluir informações específicas de PessoaJuridica.
    @Override
    public void exibir() {
        super.exibir();               // Chama o método exibir() da classe pai (Pessoa) para mostrar ID e nome.
        System.out.println("CNPJ: " + cnpj);
    }

    // Getter para obter o CNPJ da pessoa jurídica.
    public String getCnpj() {
        return cnpj;
    }

    // Setter para definir o CNPJ da pessoa jurídica.
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}
