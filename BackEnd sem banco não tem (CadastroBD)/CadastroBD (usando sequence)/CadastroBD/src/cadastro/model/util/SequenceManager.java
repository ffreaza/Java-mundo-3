package cadastro.model.util;

/**
 *
 * @author Felipe
 */

// Importações necessárias
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SequenceManager {
    // Obtém o próximo valor de uma sequência no banco de dados.
    public static int getValue(String sequenceName) {
        int value = -1;
        try {
            // Consulta SQL para obter o próximo valor da sequência
            String query = "SELECT NEXT VALUE FOR " + sequenceName;
            PreparedStatement preparedStatement = ConectorBD.getPrepared(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            // Verifica se há um valor retornado pelo banco de dados
            if (resultSet.next()) {
                value = resultSet.getInt(1);
            }
            
            // Fecha o ResultSet e o PreparedStatement
            resultSet.close();
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }
}
