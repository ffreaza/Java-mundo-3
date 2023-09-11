/* a. Dados completos de pessoas físicas */
SELECT PF.*, P.*
FROM PessoaFisica AS PF
INNER JOIN Pessoa AS P ON PF.idPessoa = P.idPessoa;


/* b. Dados completos de pessoas jurídicas */
SELECT PJ.*, P.*
FROM PessoaJuridica AS PJ
INNER JOIN Pessoa AS P ON PJ.idPessoa = P.idPessoa;


/* c. Movimentações de entrada, com produto, fornecedor, quantidade, preço unitário e valor total */
SELECT M.idMovimento, P.nome AS Produto, PF.nome AS Fornecedor, M.quantidade, M.valorUnitario, M.quantidade * M.valorUnitario AS ValorTotal
FROM Movimento AS M
INNER JOIN Produto AS P ON M.idProduto = P.idProduto
INNER JOIN PessoaJuridica AS PJ ON M.idPessoa = PJ.idPessoa
INNER JOIN Pessoa AS PF ON PJ.idPessoa = PF.idPessoa
WHERE M.tipo = 'E';


/* d. Movimentações de saída, com produto, comprador, quantidade, preço unitário e valor total. */
SELECT
    mov.tipo AS TIPO_MOVIMENTAÇÃO,
    pro.nome AS PRODUTO,
    pes.nome AS COMPRADOR,
    mov.quantidade AS QUANTIDADE,
    mov.valorUnitario AS VALOR_UNITÁRIO,
    (mov.quantidade * mov.valorUnitario) AS VALOR_TOTAL
FROM
    dbo.Movimento AS mov
INNER JOIN
    dbo.Pessoa AS pes ON mov.idPessoa = pes.idPessoa
INNER JOIN
    dbo.Produto AS pro ON mov.idProduto = pro.idProduto
WHERE
    mov.tipo = 'S';


/* e. Valor total das entradas agrupadas por produto */
SELECT P.nome AS Produto, SUM(M.quantidade * M.valorUnitario) AS ValorTotalEntradas
FROM Movimento AS M
INNER JOIN Produto AS P ON M.idProduto = P.idProduto
WHERE M.tipo = 'E'
GROUP BY P.nome;


/* f. Valor total das saídas agrupadas por produto */
SELECT P.nome AS Produto, SUM(M.quantidade * M.valorUnitario) AS ValorTotalSaidas
FROM Movimento AS M
INNER JOIN Produto AS P ON M.idProduto = P.idProduto
WHERE M.tipo = 'S'
GROUP BY P.nome;


/* g. Operadores que não efetuaram movimentações de entrada (compra) */
SELECT U.idUsuario, U.login, U.senha
FROM Usuario AS U
WHERE U.idUsuario NOT IN (
    SELECT DISTINCT M.idUsuario
    FROM Movimento AS M
    WHERE M.tipo = 'E'
);



/* h. Valor total de entrada, agrupado por operador */
SELECT U.idUsuario, U.login, SUM(M.quantidade * M.valorUnitario) AS ValorTotalEntrada
FROM Movimento AS M
INNER JOIN Usuario AS U ON M.idUsuario = U.idUsuario
WHERE M.tipo = 'E'
GROUP BY U.idUsuario, U.login;


/* i. Valor total de saída, agrupado por operador */
SELECT U.idUsuario, U.login, SUM(M.quantidade * M.valorUnitario) AS ValorTotalSaida
FROM Movimento AS M
INNER JOIN Usuario AS U ON M.idUsuario = U.idUsuario
WHERE M.tipo = 'S'
GROUP BY U.idUsuario, U.login;


/* j. Valor médio de venda por produto, utilizando média ponderada */
SELECT P.nome AS Produto, SUM(M.quantidade * M.valorUnitario) / SUM(M.quantidade) AS ValorMedioVenda
FROM Movimento AS M
INNER JOIN Produto AS P ON M.idProduto = P.idProduto
WHERE M.tipo = 'S'
GROUP BY P.nome;
