package cadastroserver;

/**
 *
 * @author Felipe
 */

// Importações necessárias
import java.io.IOException;
import javax.swing.SwingUtilities;

public class CadastroServer {

    public static void main(String[] args) throws IOException {
        // Executa a aplicação Swing na thread de despacho de eventos Swing (Event Dispatch Thread)
        SwingUtilities.invokeLater(CadastroServer::iniciarAplicacao);
    }

    private static void iniciarAplicacao() {
        // Cria uma instância da classe SaidaFrame, que é uma janela de interface gráfica Swing
        SaidaFrame frame = new SaidaFrame();

        // Cria uma instância da classe ThreadClient, que lida com a comunicação do cliente
        // e passa o componente de texto da janela SaidaFrame como argumento
        ThreadClient client = new ThreadClient(frame.getTexto());

        // Inicializa a thread do cliente
        client.start();
    }
}

