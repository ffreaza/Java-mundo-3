package cadastroee.servlets;

// Importações necessárias
import cadastroee.model.Produto;
import cadastroee.controller.ProdutoFacadeLocal;
import java.io.IOException;
import java.util.List;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Felipe
 */
@WebServlet(name = "ServletProdutoFC", urlPatterns = {"/ServletProdutoFC"})
public class ServletProdutoFC extends HttpServlet {

    @EJB
    private ProdutoFacadeLocal facade;

    // Processa solicitações GET e POST
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String acao = request.getParameter("acao");
        String destino = "ProdutoLista.jsp"; // Página padrão de destino
        
        if (acao != null) {
            if (acao.equals("listar")) {
                // Recupera todos os produtos e os define como atributos da solicitação
                List<Produto> produtos = facade.findAll();
                request.setAttribute("produtos", produtos);
            } else if (acao.equals("formAlterar")) {
                // Exibe o formulário de alteração para um produto específico
                String idProdutoStr = request.getParameter("idProduto");
                if (idProdutoStr != null) {
                    Integer idProduto = Integer.parseInt(idProdutoStr);
                    Produto produto = facade.find(idProduto);
                    request.setAttribute("produto", produto);
                    destino = "ProdutoDados.jsp";
                }
            } else if (acao.equals("formIncluir")) {
                // Exibe o formulário de inclusão de produto
                destino = "ProdutoDados.jsp";
            } else if (acao.equals("excluir")) {
                // Exclui um produto e recarrega a lista após a exclusão
                String idProdutoStr = request.getParameter("idProduto");
                if (idProdutoStr != null) {
                    Integer idProduto = Integer.parseInt(idProdutoStr);
                    Produto produto = facade.find(idProduto);
                    if (produto != null) {
                        facade.remove(produto);
                    }
                    List<Produto> produtos = facade.findAll();
                    request.setAttribute("produtos", produtos);
                }
            } else if (acao.equals("alterar") || acao.equals("incluir")) {
                // Obtém parâmetros do formulário para criação ou atualização de produtos
                String idProdutoStr = request.getParameter("idProduto");
                String nome = request.getParameter("nome");
                String quantidadeStr = request.getParameter("quantidade");
                String precoVendaStr = request.getParameter("precoVenda");
                
                // Valores padrão
                Integer idProduto = null;
                Integer quantidade = null;
                Float precoVenda = null;
                
                // Converte valores se disponíveis
                if (idProdutoStr != null && !idProdutoStr.isEmpty()) {
                    idProduto = Integer.parseInt(idProdutoStr);
                }
                if (quantidadeStr != null && !quantidadeStr.isEmpty()) {
                    quantidade = Integer.parseInt(quantidadeStr);
                }
                if (precoVendaStr != null && !precoVendaStr.isEmpty()) {
                    precoVenda = Float.parseFloat(precoVendaStr);
                }
                
                // Cria ou atualiza o produto com base na ação
                Produto produto;
                if (acao.equals("alterar")) {
                    produto = facade.find(idProduto);
                    produto.setNome(nome);
                    produto.setQuantidade(quantidade);
                    produto.setPrecoVenda(precoVenda);
                } else {
                    produto = new Produto(idProduto, nome, quantidade, precoVenda);
                }
                facade.edit(produto);
                
                // Recarrega a lista após inclusão ou atualização
                List<Produto> produtos = facade.findAll();
                request.setAttribute("produtos", produtos);
            }
        }
        
        // Redireciona para o destino apropriado
        request.getRequestDispatcher(destino).forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "ServletProdutoFC";
    }
}
