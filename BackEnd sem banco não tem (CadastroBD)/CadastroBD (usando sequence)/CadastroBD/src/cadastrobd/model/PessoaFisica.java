package cadastrobd.model;

/**
 *
 * @author Felipe
 */

// A classe representa uma pessoa física e herda características da classe Pessoa
public class PessoaFisica extends Pessoa {
    private String cpf; // CPF da pessoa física

    // Construtor padrão da classe PessoaFisica
    public PessoaFisica() {
        super(); // Chama o construtor padrão da classe Pessoa
    }

    // Construtor completo da classe PessoaFisica, que permite definir todos os atributos, incluindo CPF
    public PessoaFisica(int idPessoa, String nome, String logradouro, String cidade, String estado, String telefone, String email, String cpf) {
        super(idPessoa, nome, logradouro, cidade, estado, telefone, email); // Chama o construtor completo da classe Pessoa
        this.cpf = cpf;
    }

    // Métodos getters e setters para o campo CPF da pessoa física
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    // Sobrescrita do método exibir para incluir a exibição do CPF
    @Override
    public void exibir() {
        super.exibir(); // Chama o método exibir da classe Pessoa para exibir os atributos herdados
        System.out.println("CPF: " + cpf);
    }
}
