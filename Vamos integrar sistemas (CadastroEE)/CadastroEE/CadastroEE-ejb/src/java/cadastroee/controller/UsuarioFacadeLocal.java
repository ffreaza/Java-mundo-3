package cadastroee.controller;

// Importações necessárias
import cadastroee.model.Usuario;
import java.util.List;
import jakarta.ejb.Local;

/**
 *
 * @author Felipe
 */
@Local
public interface UsuarioFacadeLocal {

    void create(Usuario usuario);

    void edit(Usuario usuario);

    void remove(Usuario usuario);

    Usuario find(Object id);

    List<Usuario> findAll();

    List<Usuario> findRange(int[] range);

    int count();
}
