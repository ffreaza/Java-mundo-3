<%-- 
    Document   : ProdutoLista
    Created on : 4 de set. de 2023
    Author     : Felipe
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="cadastroee.model.Produto" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lista de Produtos</title>
    <!-- Inclua o link para o Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    
    <!-- Inclua o link para o Bootstrap JavaScript (copiado do Bootstrap CDN) -->
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body class="container"> <!-- Adicione a classe 'container' ao body -->
    <h1>Lista de Produtos</h1>
    
    <!-- Link para abrir o formulário de inclusão -->
    <a href="ServletProdutoFC?acao=formIncluir" class="btn btn-primary m-2">Incluir Novo Produto</a> <!-- Adicione as classes 'btn', 'btn-primary' e 'm-2' -->

    <table class="table table-striped"> <!-- Adicione as classes 'table' e 'table-striped' à tabela -->
        <thead class="table-dark"> <!-- Adicione a classe 'table-dark' ao thead -->
            <tr>
                <th>ID</th>
                <th>Nome</th>
                <th>Quantidade</th>
                <th>Preço</th>
                <th>Ações</th>
            </tr>
        </thead>
        <%
            List<Produto> produtos = (List<Produto>) request.getAttribute("produtos");
            if (produtos != null && !produtos.isEmpty()) {
                for (Produto produto : produtos) {
        %>
        <tr>
            <td><%= produto.getIdProduto() %></td>
            <td><%= produto.getNome() %></td>
            <td><%= produto.getQuantidade() %></td>
            <td><%= produto.getPrecoVenda() %></td>
            <td>
                <!-- Link para ação de alteração -->
                <a href="ServletProdutoFC?acao=formAlterar&idProduto=<%= produto.getIdProduto() %>" class="btn btn-primary btn-sm">Alterar</a> <!-- Adicione as classes 'btn', 'btn-primary' e 'btn-sm' -->
                <!-- Link para ação de exclusão -->
                <a href="ServletProdutoFC?acao=excluir&idProduto=<%= produto.getIdProduto() %>" class="btn btn-danger btn-sm">Excluir</a> <!-- Adicione as classes 'btn', 'btn-danger' e 'btn-sm' -->
            </td>
        </tr>
        <%
                }
            } else {
        %>
        <tr>
            <td colspan="5">Nenhum produto disponível.</td>
        </tr>
        <%
            }
        %>
    </table>
</body>
</html>
