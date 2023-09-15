package cadastroclient;

/**
 *
 * @author Felipe
 */

// Importações necessárias
import java.io.*;
import java.net.*;
import java.util.List;
import model.Produto;

public class CadastroClient {

    public static void main(String[] args) {
        try {
            // Estabelece uma conexão com o servidor na porta 4321 em localhost
            Socket socket = new Socket("localhost", 4321);
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            // Autenticação do usuário
            autenticarUsuario(out, in);

            // Listagem de produtos
            listarProdutos(out, in);

            // Fecha a conexão com o servidor
            socket.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void autenticarUsuario(ObjectOutputStream out, ObjectInputStream in) throws IOException, ClassNotFoundException {
        // Leitura do usuário e senha do teclado
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Digite o Usuário: ");
        String usuario = reader.readLine();
        System.out.println("Digite a Senha: ");
        String senha = reader.readLine();

        // Envia usuário e senha para o servidor
        out.writeObject(usuario);
        out.writeObject(senha);

        // Recebe a resposta do servidor
        String result = (String) in.readObject();
        if (!"ok".equals(result)) {
            System.out.println("Erro de login");
            System.exit(1);
        }
        System.out.println("Usuário conectado com sucesso!!");
    }

    private static void listarProdutos(ObjectOutputStream out, ObjectInputStream in) throws IOException, ClassNotFoundException {
        // Envia uma solicitação para listar produtos para o servidor
        out.writeObject("L");

        // Recebe a lista de produtos do servidor
        List<Produto> produtos = (List<Produto>) in.readObject();
        
        // Exibe a lista de produtos
        System.out.println("Lista de Produtos:");
        for (Produto produto : produtos) {
            System.out.println(produto.getNome());
        }
    }
}
