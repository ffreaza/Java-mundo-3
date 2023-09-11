package cadastrobd.model;

/**
 *
 * @author Felipe
 */

// A classe representa uma pessoa com informações básicas
public class Pessoa {
    private int idPessoa; // Identificador único da pessoa
    private String nome; // Nome da pessoa
    private String logradouro; // Endereço da pessoa
    private String cidade; // Cidade onde a pessoa reside
    private String estado; // Estado onde a pessoa reside
    private String telefone; // Número de telefone da pessoa
    private String email; // Endereço de e-mail da pessoa

    // Construtor padrão da classe Pessoa
    public Pessoa() {
    }

    // Construtor completo da classe Pessoa, que permite definir todos os atributos
    public Pessoa(int idPessoa, String nome, String logradouro, String cidade, String estado, String telefone, String email) {
        this.idPessoa = idPessoa;
        this.nome = nome;
        this.logradouro = logradouro;
        this.cidade = cidade;
        this.estado = estado;
        this.telefone = telefone;
        this.email = email;
    }

    // Métodos getters e setters para todos os campos da classe Pessoa
    public int getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(int idPessoa) {
        this.idPessoa = idPessoa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Método para exibir os dados de uma pessoa no console
    public void exibir() {
        System.out.println("Id: " + idPessoa);
        System.out.println("Nome: " + nome);
        System.out.println("Logradouro: " + logradouro);
        System.out.println("Cidade: " + cidade);
        System.out.println("Estado: " + estado);
        System.out.println("Telefone: " + telefone);
        System.out.println("E-mail: " + email);
    }
}
