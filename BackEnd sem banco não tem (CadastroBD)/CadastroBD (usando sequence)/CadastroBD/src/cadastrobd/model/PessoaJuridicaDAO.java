package cadastrobd.model;

/**
 *
 * @author Felipe
 */

// Importações necessárias
import cadastro.model.util.ConectorBD;
import cadastro.model.util.SequenceManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PessoaJuridicaDAO {
    private ConectorBD conectorBD;

    // Construtor que recebe um objeto ConectorBD como parâmetro
    public PessoaJuridicaDAO(ConectorBD conectorBD) {
        this.conectorBD = conectorBD;
    }

    // Obtém uma pessoa jurídica pelo seu ID
    public PessoaJuridica getPessoa(int idPessoa) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConectorBD.getConnection();
            String sql = "SELECT p.nome, p.logradouro, p.cidade, p.estado, p.telefone, p.email, pj.cnpj " +
                         "FROM dbo.Pessoa p " +
                         "INNER JOIN dbo.PessoaJuridica pj ON p.idPessoa = pj.idPessoa " +
                         "WHERE p.idPessoa = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, idPessoa);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String nome = resultSet.getString("nome");
                String logradouro = resultSet.getString("logradouro");
                String cidade = resultSet.getString("cidade");
                String estado = resultSet.getString("estado");
                String telefone = resultSet.getString("telefone");
                String email = resultSet.getString("email");
                String cnpj = resultSet.getString("cnpj");
                return new PessoaJuridica(idPessoa, nome, logradouro, cidade, estado, telefone, email, cnpj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConectorBD.close(statement, resultSet, connection);
        }
        return null;
    }

    // Obtém uma lista de todas as pessoas jurídicas no banco de dados
    public List<PessoaJuridica> getPessoas() {
        List<PessoaJuridica> pessoas = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConectorBD.getConnection();
            String sql = "SELECT p.idPessoa, p.nome, p.logradouro, p.cidade, p.estado, p.telefone, p.email, pj.cnpj FROM dbo.Pessoa p INNER JOIN dbo.PessoaJuridica pj ON p.idPessoa = pj.idPessoa";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int idj = resultSet.getInt("idPessoa");
                String nome = resultSet.getString("nome");
                String logradouro = resultSet.getString("logradouro");
                String cidade = resultSet.getString("cidade");
                String estado = resultSet.getString("estado");
                String telefone = resultSet.getString("telefone");
                String email = resultSet.getString("email");
                String cnpj = resultSet.getString("cnpj");
                PessoaJuridica pessoa = new PessoaJuridica(idj, nome, logradouro, cidade, estado, telefone, email, cnpj);
                pessoas.add(pessoa);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConectorBD.close(statement, resultSet, connection);
        }
        return pessoas;
    }

    // Inclui uma nova pessoa jurídica no banco de dados
    public void incluir(PessoaJuridica pessoa) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConectorBD.getConnection();
            // Ativa o Sequence para a tabela "Pessoa" para permitir a inserção do valor explícito na coluna de identidade.
            String sqlPessoa = "INSERT INTO dbo.Pessoa (idPessoa, nome, logradouro, cidade, estado, telefone, email) VALUES (?, ?, ?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(sqlPessoa);
            int id = SequenceManager.getValue("PessoaSeq");
            statement.setInt(1, id);
            statement.setString(2, pessoa.getNome());
            statement.setString(3, pessoa.getLogradouro());
            statement.setString(4, pessoa.getCidade());
            statement.setString(5, pessoa.getEstado());
            statement.setString(6, pessoa.getTelefone());
            statement.setString(7, pessoa.getEmail());
            statement.executeUpdate();

            String sqlPessoaJuridica = "INSERT INTO dbo.PessoaJuridica (idPessoa, cnpj) VALUES (?, ?)";
            statement = connection.prepareStatement(sqlPessoaJuridica);
            statement.setInt(1, id);
            statement.setString(2, pessoa.getCnpj());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConectorBD.close(statement, null, connection);
        }
    }

    // Altera os dados de uma pessoa jurídica no banco de dados
    public void alterar(PessoaJuridica pessoa) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConectorBD.getConnection();
            String sqlPessoa = "UPDATE dbo.Pessoa SET nome = ?, logradouro = ?, cidade = ?, estado = ?, telefone = ?, email = ? WHERE idPessoa = ?";
            statement = connection.prepareStatement(sqlPessoa);
            statement.setString(1, pessoa.getNome());
            statement.setString(2, pessoa.getLogradouro());
            statement.setString(3, pessoa.getCidade());
            statement.setString(4, pessoa.getEstado());
            statement.setString(5, pessoa.getTelefone());
            statement.setString(6, pessoa.getEmail());
            statement.setInt(7, pessoa.getIdPessoa());
            statement.executeUpdate();

            String sqlPessoaJuridica = "UPDATE dbo.PessoaJuridica SET cnpj = ? WHERE idPessoa = ?";
            statement = connection.prepareStatement(sqlPessoaJuridica);
            statement.setString(1, pessoa.getCnpj());
            statement.setInt(2, pessoa.getIdPessoa());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConectorBD.close(statement, null, connection);
        }
    }

    // Exclui uma pessoa jurídica do banco de dados
    public void excluir(PessoaJuridica pessoa) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConectorBD.getConnection();
            String sqlPessoaJuridica = "DELETE FROM dbo.PessoaJuridica WHERE idPessoa = ?";
            statement = connection.prepareStatement(sqlPessoaJuridica);
            statement.setInt(1, pessoa.getIdPessoa());
            statement.executeUpdate();

            String sqlPessoa = "DELETE FROM dbo.Pessoa WHERE idPessoa = ?";
            statement = connection.prepareStatement(sqlPessoa);
            statement.setInt(1, pessoa.getIdPessoa());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConectorBD.close(statement, null, connection);
        }
    }
}
