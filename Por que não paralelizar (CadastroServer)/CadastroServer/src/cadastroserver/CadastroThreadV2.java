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
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;
import model.Movimento;
import model.Produto;
import model.Usuario;

public class CadastroThreadV2 extends Thread {

    private final ProdutoJpaController ctrl;
    private final UsuarioJpaController ctrlUsu;
    private final PessoaJpaController ctrlPessoa;
    private final MovimentoJpaController ctrlMov;
    private final JTextArea entrada;
    private final Socket s1;

    public CadastroThreadV2(ProdutoJpaController ctrl, UsuarioJpaController ctrlUsu, PessoaJpaController ctrlPessoa, MovimentoJpaController ctrlMov, JTextArea entrada, Socket s1) {
        this.ctrl = ctrl;
        this.ctrlUsu = ctrlUsu;
        this.ctrlPessoa = ctrlPessoa;
        this.ctrlMov = ctrlMov;
        this.entrada = entrada;
        this.s1 = s1;
    }

    @Override
    public void run() {
        System.out.println("Thread está em execução...");
        entrada.append(">> Nova comunicação em " + java.time.LocalDateTime.now() + "\n");

        try (ObjectInputStream in = new ObjectInputStream(s1.getInputStream());
             ObjectOutputStream out = new ObjectOutputStream(s1.getOutputStream())) {

            // Lê o login e senha enviados pelo cliente
            String login = (String) in.readObject();
            String senha = (String) in.readObject();

            // Procura o usuário no banco de dados com o login e senha recebidos
            Usuario user = ctrlUsu.findUsuario(login, senha);
            if (user == null) {
                entrada.append("Erro de conexão do usuário\n");
                out.writeObject("nok");
                return;
            }
            // Se o usuário for encontrado, envia "ok" de volta ao cliente
            out.writeObject("ok");
            entrada.append("Usuário conectado com sucesso\n");

            String input;
            do {
                // Lê comandos do cliente
                input = (String) in.readObject();
                if ("l".equalsIgnoreCase(input)) {
                    // Se o comando for "l", envia a lista de produtos ao cliente
                    List<Produto> produtos = ctrl.findProdutoEntities();
                    for (Produto produto : produtos) {
                        entrada.append(produto.getNome() + "::" + produto.getQuantidade() + "\n");
                    }
                    out.writeObject(produtos);
                } else if ("e".equalsIgnoreCase(input) || "s".equalsIgnoreCase(input)) {

                    // Cria um objeto de movimento
                    Movimento movimento = new Movimento();
                    movimento.setIdUsuario(user);
                    movimento.setTipo(input.toUpperCase().charAt(0));

                    // Lê idPessoa, idProduto, quantidade e valor do cliente
                    int idPessoa = Integer.parseInt((String) in.readObject());
                    movimento.setIdPessoa(ctrlPessoa.findPessoa(idPessoa));

                    int idProduto = Integer.parseInt((String) in.readObject());
                    Produto produto = ctrl.findProduto(idProduto);
                    movimento.setIdProduto(produto);

                    int quantidade = Integer.parseInt((String) in.readObject());
                    movimento.setQuantidade(quantidade);

                    BigDecimal valor = new BigDecimal((String) in.readObject());
                    movimento.setValorUnitario(valor);

                    // Atualiza a quantidade de produtos com base no tipo de movimento
                    if ("e".equalsIgnoreCase(input)) {
                        produto.setQuantidade(produto.getQuantidade() + quantidade);
                    } else {
                        produto.setQuantidade(produto.getQuantidade() - quantidade);
                    }
                    // Salva as alterações no produto e cria o movimento
                    ctrl.edit(produto);
                    ctrlMov.create(movimento);
                    entrada.append("Movimento criado\n");

                } else if (!"x".equalsIgnoreCase(input)) {
                    // Se um comando inválido for recebido, exibe uma mensagem de erro no servidor
                    System.out.println("Comando inválido recebido: " + input);
                    entrada.append("Comando inválido recebido: " + input + "\n");
                }

            } while (!input.equalsIgnoreCase("x"));

        } catch (Exception ex) {
            Logger.getLogger(CadastroThreadV2.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            entrada.append("<< Fim de comunicação em " + java.time.LocalDateTime.now() + "\n");
            System.out.println("Thread finalizada...");
        }
    }
}
