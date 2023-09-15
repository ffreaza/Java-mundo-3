package cadastroserver;

/**
 *
 * @author Felipe
 */

// Importações necessárias
import controller.ProdutoJpaController;
import controller.UsuarioJpaController;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Usuario;

public class CadastroThread extends Thread {
    private final ProdutoJpaController ctrl;
    private final UsuarioJpaController ctrlUsu;
    private final Socket s1;

    public CadastroThread(ProdutoJpaController ctrl, UsuarioJpaController ctrlUsu, Socket s1) {
        this.ctrl = ctrl;
        this.ctrlUsu = ctrlUsu;
        this.s1 = s1;
    }

    @Override
    public void run() {
        System.out.println("Thread está em execução...");

        try (ObjectInputStream in = new ObjectInputStream(s1.getInputStream());
             ObjectOutputStream out = new ObjectOutputStream(s1.getOutputStream())) {

            // Lê o login e senha enviados pelo cliente
            String login = (String) in.readObject();
            String senha = (String) in.readObject();

            // Procura o usuário no banco de dados com o login e senha recebidos
            Usuario user = ctrlUsu.findUsuario(login, senha);
            if (user == null) {
                // Se o usuário não for encontrado, envia "nok" de volta ao cliente e encerra a thread
                out.writeObject("nok");
                return;
            }
            // Se o usuário for encontrado, envia "ok" de volta ao cliente
            out.writeObject("ok");

            String input;
            do {
                // Lê comandos do cliente
                input = (String) in.readObject();
                if ("l".equalsIgnoreCase(input)) {
                    // Se o comando for "l", envia a lista de produtos ao cliente
                    out.writeObject(ctrl.findProdutoEntities());
                } else if (!"x".equalsIgnoreCase(input)) {
                    // Se um comando inválido for recebido, exibe uma mensagem de erro no servidor
                    System.out.println("Comando inválido recebido: " + input);
                }

            } while (!input.equalsIgnoreCase("x"));

        } catch (ClassNotFoundException | IOException ex) {
            Logger.getLogger(CadastroThread.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            System.out.println("Thread finalizada...");
        }
    }
}
