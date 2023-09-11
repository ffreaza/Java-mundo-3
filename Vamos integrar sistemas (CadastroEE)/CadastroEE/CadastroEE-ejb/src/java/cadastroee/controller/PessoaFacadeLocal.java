package cadastroee.controller;

// Importações necessárias
import cadastroee.model.Pessoa;
import jakarta.ejb.Local;
import java.util.List;

/**
 *
 * @author Felipe
 */
@Local
public interface PessoaFacadeLocal {

    void criar(Pessoa pessoa);

    void atualizar(Pessoa pessoa);

    void remover(Pessoa pessoa);

    Pessoa encontrar(Object id);

    List<Pessoa> encontrarTodos();

    List<Pessoa> encontrarRange(int[] range);

    int contar();
}
