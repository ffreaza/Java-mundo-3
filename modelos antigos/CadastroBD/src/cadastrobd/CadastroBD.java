package cadastrobd;

/**
 * 
 * @author Felipe
 */

import cadastrobd.model.PessoaFisica;
import cadastrobd.model.PessoaFisicaDAO;
import cadastrobd.model.PessoaJuridica;
import cadastrobd.model.PessoaJuridicaDAO;
import java.util.List;
import cadastro.model.util.ConectorBD;
import cadastro.model.util.SequenceManager;

public class CadastroBD {
    public static void main(String[] args) {
        ConectorBD conectorBD = new ConectorBD();

        PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO(conectorBD);
        PessoaJuridicaDAO pessoaJuridicaDAO = new PessoaJuridicaDAO(conectorBD);

        // Instanciar uma pessoa física e persistir no banco de dados
        PessoaFisica pessoaFisica = new PessoaFisica();
        pessoaFisica.setNome("Lucas Oliveira");
        pessoaFisica.setLogradouro("Avenida das Flores");
        pessoaFisica.setCidade("Santa Clara");
        pessoaFisica.setEstado("MG");
        pessoaFisica.setTelefone("8765-4321");
        pessoaFisica.setEmail("lucas.oliveira@example.com");
        pessoaFisica.setCpf("83527194682");
        pessoaFisicaDAO.incluir(pessoaFisica);

        // Alterar dados da pessoa física no banco
        pessoaFisica.setTelefone("1234-5678");
        pessoaFisicaDAO.alterar(pessoaFisica);

        // Consultar todas as pessoas físicas do banco de dados e listar no console
        List<PessoaFisica> pessoasFisicas = pessoaFisicaDAO.getPessoas();
        for (PessoaFisica pf : pessoasFisicas) {
            pf.exibir();
        }

        // Excluir a pessoa física criada anteriormente no banco
        pessoaFisicaDAO.excluir(pessoaFisica);

        // Instanciar uma pessoa jurídica e persistir no banco de dados
        PessoaJuridica pessoaJuridica = new PessoaJuridica();
        pessoaJuridica.setNome("LO");
        pessoaJuridica.setLogradouro("Avenida das Flores");
        pessoaJuridica.setCidade("Santa Clara");
        pessoaJuridica.setEstado("MG");
        pessoaJuridica.setTelefone("8765-1234");
        pessoaJuridica.setEmail("lo@example.com");
        pessoaJuridica.setCnpj("62831745926381");
        pessoaJuridicaDAO.incluir(pessoaJuridica);

        // Alterar dados da pessoa jurídica no banco
        pessoaJuridica.setEmail("nova.lo@example.com");
        pessoaJuridicaDAO.alterar(pessoaJuridica);

        // Consultar todas as pessoas jurídicas do banco de dados e listar no console
        List<PessoaJuridica> pessoasJuridicas = pessoaJuridicaDAO.getPessoas();
        for (PessoaJuridica pj : pessoasJuridicas) {
            pj.exibir();
        }

        // Excluir a pessoa jurídica criada anteriormente no banco
        pessoaJuridicaDAO.excluir(pessoaJuridica);
    }
}
