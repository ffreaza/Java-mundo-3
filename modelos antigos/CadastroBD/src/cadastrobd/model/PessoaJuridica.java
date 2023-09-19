package cadastrobd.model;

/**
 *
 * @author Felipe
 */

public class PessoaJuridica extends Pessoa {
    private String cnpj;

    // Construtor padrão
    public PessoaJuridica() {
        super();
    }

    // Construtor completo
    public PessoaJuridica(int idPessoa, String nome, String logradouro, String cidade, String estado, String telefone, String email, String cnpj) {
        super(idPessoa, nome, logradouro, cidade, estado, telefone, email); // Chama o construtor completo da classe Pessoa
        this.cnpj = cnpj;
    }

    // Métodos getters e setters para o campo cnpj
    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    // Sobrescrita do método exibir para incluir o CNPJ
    @Override
    public void exibir() {
        super.exibir(); // Chama o método exibir da classe Pessoa
        System.out.println("CNPJ: " + cnpj);
    }
}
