package cadastro.model.util;

/**
 *
 * @author Felipe
 */

// Importações necessárias
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConectorBD {
    // URL de conexão com o banco de dados SQL Server
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=Loja;encrypt=true;trustServerCertificate=true;";
    
    // Credenciais de acesso ao banco de dados
    private static final String USUARIO = "loja";
    private static final String SENHA = "loja";

    // Obtém uma conexão com o banco de dados
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }

    // Cria um PreparedStatement com base no SQL fornecido
    public static PreparedStatement getPrepared(String sql) throws SQLException {
        return getConnection().prepareStatement(sql);
    }

    // Executa uma consulta SELECT e retorna um ResultSet com os resultados
    public static ResultSet getSelect(String sql) throws SQLException {
        return getPrepared(sql).executeQuery();
    }

    // Fecha a conexão com o banco de dados, o PreparedStatement e o ResultSet, se estiverem abertos
    public static void close(PreparedStatement statement, ResultSet resultSet, Connection connection) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}