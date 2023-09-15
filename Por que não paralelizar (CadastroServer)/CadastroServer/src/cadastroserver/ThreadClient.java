package cadastroserver;

/**
 *
 * @author Felipe
 */

// Importações necessárias
import controller.MovimentoJpaController;
import controller.PessoaJpaController;
import controller.ProdutoJpaController;
import controller.UsuarioJpaController;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.net.ServerSocket;
import java.net.Socket;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JTextArea;

public class ThreadClient extends Thread {
    private final JTextArea entrada;

    public ThreadClient(JTextArea entrada) {
        this.entrada = entrada;
    }

    @Override
    public void run() {
        try {
            // Cria uma instância de EntityManagerFactory para acesso ao banco de dados
            EntityManagerFactory em = Persistence.createEntityManagerFactory("CadastroServerPU");

            // Cria controladores para acessar os dados do banco de dados
            ProdutoJpaController ctrl = new ProdutoJpaController(em);
            UsuarioJpaController ctrlUsu = new UsuarioJpaController(em);
            PessoaJpaController ctrlPessoa = new PessoaJpaController(em);
            MovimentoJpaController ctrlMov = new MovimentoJpaController(em);

            // Cria um servidor Socket na porta 4321
            ServerSocket serverSocket = new ServerSocket(4321);

            // Aguarda conexões de clientes
            while (true) {
                Socket s1 = serverSocket.accept();

                // Cria uma nova thread para lidar com cada cliente
                CadastroThreadV2 cadastroThread = new CadastroThreadV2(ctrl, ctrlUsu, ctrlPessoa, ctrlMov, entrada, s1);
                cadastroThread.start();
            }
        } catch (Exception ex) {
            Logger.getLogger(ThreadClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
