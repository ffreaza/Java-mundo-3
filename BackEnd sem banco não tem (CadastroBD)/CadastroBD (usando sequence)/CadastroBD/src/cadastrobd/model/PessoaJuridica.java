package cadastrobd.model;

/**
 *
 * @author Felipe
 */

// A classe representa uma pessoa jurídica e herda características da classe Pessoa
public class PessoaJuridica extends Pessoa {
    private String cnpj; // CNPJ da pessoa jurídica

    // Construtor padrão da classe PessoaJuridica
    public PessoaJuridica() {
        super(); // Chama o construtor padrão da classe Pessoa
    }

    // Construtor completo da classe PessoaJuridica, que permite definir todos os atributos, incluindo CNPJ
    public PessoaJuridica(int idPessoa, String nome, String logradouro, String cidade, String estado, String telefone, String email, String cnpj) {
        super(idPessoa, nome, logradouro, cidade, estado, telefone, email); // Chama o construtor completo da classe Pessoa
        this.cnpj = cnpj;
    }

    // Métodos getters e setters para o campo CNPJ da pessoa jurídica
    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    // Sobrescrita do método exibir para incluir a exibição do CNPJ
    @Override
    public void exibir() {
        super.exibir(); // Chama o método exibir da classe Pessoa para exibir os atributos herdados
        System.out.println("CNPJ: " + cnpj);
    }
}
