package cadastrobd;

/**
 *
 * @author Felipe
 */

// Importações necessárias
import cadastrobd.model.PessoaFisica;
import cadastrobd.model.PessoaFisicaDAO;
import cadastrobd.model.PessoaJuridica;
import cadastrobd.model.PessoaJuridicaDAO;
import java.util.List;
import java.util.Scanner;
import cadastro.model.util.ConectorBD;

public class CadastroBD {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ConectorBD conectorBD = new ConectorBD();

        PessoaFisicaDAO pessoaFisicaDAO = new PessoaFisicaDAO(conectorBD);
        PessoaJuridicaDAO pessoaJuridicaDAO = new PessoaJuridicaDAO(conectorBD);

        int opcao;
        do {
            // Menu principal
            System.out.println("╔═════════════════════════════════╗");
            System.out.println("║      MENU PRINCIPAL       ║");
            System.out.println("╟─────────────────────────────────╢");
            System.out.println("║ Selecione uma opção:      ║");
            System.out.println("║ 1. Incluir                ║");
            System.out.println("║ 2. Alterar                ║");
            System.out.println("║ 3. Excluir                ║");
            System.out.println("║ 4. Exibir pelo ID         ║");
            System.out.println("║ 5. Exibir todos           ║");
            System.out.println("║ 0. Sair                   ║");
            System.out.println("╚═════════════════════════════════╝");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer

            switch (opcao) {
                case 1:
                    // Solicita ao usuário que escolha o tipo (F - Física, J - Jurídica)
                    System.out.println("Escolha o tipo (F - Física, J - Jurídica):");
                    String tipo = scanner.nextLine().strip().toUpperCase();

                    if (tipo.equalsIgnoreCase("F")) {
                        // Cria um objeto Pessoa Física
                        PessoaFisica pessoaFisica = new PessoaFisica();
                        System.out.println("════════════════════════════════");
                        System.out.print("Nome: ");
                        pessoaFisica.setNome(scanner.nextLine());

                        System.out.print("Logradouro: ");
                        pessoaFisica.setLogradouro(scanner.nextLine());

                        System.out.print("Cidade: ");
                        pessoaFisica.setCidade(scanner.nextLine());

                        System.out.print("Estado: ");
                        pessoaFisica.setEstado(scanner.nextLine());

                        System.out.print("Telefone: ");
                        pessoaFisica.setTelefone(scanner.nextLine());

                        System.out.print("E-mail: ");
                        pessoaFisica.setEmail(scanner.nextLine());

                        System.out.print("CPF: ");
                        pessoaFisica.setCpf(scanner.nextLine());
                        System.out.println("╚══════════════════════════════╝");

                        // Inclui a Pessoa Física no banco de dados
                        pessoaFisicaDAO.incluir(pessoaFisica);
                        System.out.println("Pessoa Física incluída com sucesso!");

                    } else if (tipo.equalsIgnoreCase("J")) {
                        // Cria um objeto Pessoa Jurídica
                        PessoaJuridica pessoaJuridica = new PessoaJuridica();
                        System.out.println("════════════════════════════════");
                        System.out.print("Nome: ");
                        pessoaJuridica.setNome(scanner.nextLine());

                        System.out.print("Logradouro: ");
                        pessoaJuridica.setLogradouro(scanner.nextLine());

                        System.out.print("Cidade: ");
                        pessoaJuridica.setCidade(scanner.nextLine());

                        System.out.print("Estado: ");
                        pessoaJuridica.setEstado(scanner.nextLine());

                        System.out.print("Telefone: ");
                        pessoaJuridica.setTelefone(scanner.nextLine());

                        System.out.print("E-mail: ");
                        pessoaJuridica.setEmail(scanner.nextLine());

                        System.out.print("CNPJ: ");
                        pessoaJuridica.setCnpj(scanner.nextLine());
                        System.out.println("════════════════════════════════");

                        // Inclui a Pessoa Jurídica no banco de dados
                        pessoaJuridicaDAO.incluir(pessoaJuridica);
                        System.out.println("Pessoa Jurídica incluída com sucesso!");

                    } else {
                        System.out.println("Opção inválida.");
                    }
                    break;

                case 2:
                    // Solicita ao usuário que escolha o tipo (F - Física, J - Jurídica)
                    System.out.println("Escolha o tipo (F - Física, J - Jurídica):");
                    String tipoAlterar = scanner.nextLine().strip().toUpperCase();

                    // Solicita ao usuário o ID da pessoa a ser alterada
                    System.out.print("Informe o ID da pessoa: ");
                    int idAlterar = scanner.nextInt();
                    scanner.nextLine(); // Limpar o buffer

                    if (tipoAlterar.equalsIgnoreCase("F")) {
                        // Obtém a Pessoa Física do banco de dados pelo ID informado
                        PessoaFisica pessoaFisica = pessoaFisicaDAO.getPessoa(idAlterar);
                        if (pessoaFisica != null) {
                            // Exibe os dados atuais da pessoa
                            pessoaFisica.exibir();

                            // Recebe os novos dados da Pessoa Física
                            System.out.println("════════════════════════════════");
                            System.out.print("Novo Nome: ");
                            pessoaFisica.setNome(scanner.nextLine());

                            System.out.print("Novo Logradouro: ");
                            pessoaFisica.setLogradouro(scanner.nextLine());

                            System.out.print("Nova Cidade: ");
                            pessoaFisica.setCidade(scanner.nextLine());

                            System.out.print("Novo Estado: ");
                            pessoaFisica.setEstado(scanner.nextLine());

                            System.out.print("Novo Telefone: ");
                            pessoaFisica.setTelefone(scanner.nextLine());

                            System.out.print("Novo E-mail: ");
                            pessoaFisica.setEmail(scanner.nextLine());

                            System.out.print("Novo CPF: ");
                            pessoaFisica.setCpf(scanner.nextLine());
                            System.out.println("════════════════════════════════");

                            // Altera os dados da Pessoa Física no banco de dados
                            pessoaFisicaDAO.alterar(pessoaFisica);
                            System.out.println("Pessoa Física alterada com sucesso!");

                        } else {
                            System.out.println("Pessoa Física não encontrada.");
                        }
                    } else if (tipoAlterar.equalsIgnoreCase("J")) {
                        // Obtém a Pessoa Jurídica do banco de dados pelo ID informado
                        PessoaJuridica pessoaJuridica = pessoaJuridicaDAO.getPessoa(idAlterar);
                        if (pessoaJuridica != null) {
                            // Exibe os dados atuais da pessoa jurídica
                            pessoaJuridica.exibir();

                            // Recebe os novos dados da Pessoa Jurídica
                            System.out.println("════════════════════════════════");
                            System.out.print("Novo Nome: ");
                            pessoaJuridica.setNome(scanner.nextLine());

                            System.out.print("Novo Logradouro: ");
                            pessoaJuridica.setLogradouro(scanner.nextLine());

                            System.out.print("Nova Cidade: ");
                            pessoaJuridica.setCidade(scanner.nextLine());

                            System.out.print("Novo Estado: ");
                            pessoaJuridica.setEstado(scanner.nextLine());

                            System.out.print("Novo Telefone: ");
                            pessoaJuridica.setTelefone(scanner.nextLine());

                            System.out.print("Novo E-mail: ");
                            pessoaJuridica.setEmail(scanner.nextLine());

                            System.out.print("Novo CNPJ: ");
                            pessoaJuridica.setCnpj(scanner.nextLine());
                            System.out.println("════════════════════════════════");

                            // Altera os dados da Pessoa Jurídica no banco de dados
                            pessoaJuridicaDAO.alterar(pessoaJuridica);
                            System.out.println("Pessoa Jurídica alterada com sucesso!");
                        } else {
                            System.out.println("Pessoa Jurídica não encontrada.");
                        }
                    } else {
                        System.out.println("Opção inválida.");
                    }
                    break;

                case 3:
                    // Solicita ao usuário que escolha o tipo (F - Física, J - Jurídica)
                    System.out.println("Escolha o tipo (F - Física, J - Jurídica):");
                    String tipoExcluir = scanner.nextLine().strip().toUpperCase();

                    // Solicita ao usuário o ID da pessoa a ser excluída
                    System.out.print("Informe o ID da pessoa: ");
                    int idExcluir = scanner.nextInt();
                    scanner.nextLine(); // Limpar o buffer

                    if (tipoExcluir.equalsIgnoreCase("F")) {
                        // Obtém a Pessoa Física do banco de dados pelo ID informado
                        PessoaFisica pessoaFisica = pessoaFisicaDAO.getPessoa(idExcluir);
                        if (pessoaFisica != null) {
                            // Exclui a Pessoa Física do banco de dados
                            pessoaFisicaDAO.excluir(pessoaFisica);
                            System.out.println("Pessoa Física excluída com sucesso!");
                        } else {
                            System.out.println("Pessoa Física não encontrada.");
                        }
                    } else if (tipoExcluir.equalsIgnoreCase("J")) {
                        // Obtém a Pessoa Jurídica do banco de dados pelo ID informado
                        PessoaJuridica pessoaJuridica = pessoaJuridicaDAO.getPessoa(idExcluir);
                        if (pessoaJuridica != null) {
                            // Exclui a Pessoa Jurídica do banco de dados
                            pessoaJuridicaDAO.excluir(pessoaJuridica);
                            System.out.println("Pessoa Jurídica excluída com sucesso!");
                        } else {
                            System.out.println("Pessoa Jurídica não encontrada.");
                        }
                    } else {
                        System.out.println("Opção inválida.");
                    }
                    break;

                case 4:
                    // Solicita ao usuário que escolha o tipo (F - Física, J - Jurídica)
                    System.out.println("Escolha o tipo (F - Física, J - Jurídica):");
                    String tipoExibirId = scanner.nextLine().strip().toUpperCase();

                    // Solicita ao usuário o ID da pessoa a ser exibida
                    System.out.print("Informe o ID da pessoa: ");
                    int idExibirId = scanner.nextInt();
                    scanner.nextLine(); // Limpar o buffer

                    if (tipoExibirId.equalsIgnoreCase("F")) {
                        // Obtém a Pessoa Física do banco de dados pelo ID informado
                        PessoaFisica pessoaFisica = pessoaFisicaDAO.getPessoa(idExibirId);
                        if (pessoaFisica != null) {
                            // Exibe os detalhes da Pessoa Física
                            pessoaFisica.exibir();
                        } else {
                            System.out.println("Pessoa Física não encontrada.");
                        }
                    } else if (tipoExibirId.equalsIgnoreCase("J")) {          
                        // Obtém a Pessoa Jurídica do banco de dados pelo ID informado
                        PessoaJuridica pessoaJuridica = pessoaJuridicaDAO.getPessoa(idExibirId);
                        if (pessoaJuridica != null) {
                            // Exibe os detalhes da Pessoa Jurídica
                            pessoaJuridica.exibir();
                        } else {
                            System.out.println("Pessoa Jurídica não encontrada.");
                        }
                    } else {
                        System.out.println("Opção inválida.");
                    }
                    break;

                case 5:
                    // Solicita ao usuário que escolha o tipo (F - Física, J - Jurídica)
                    System.out.println("Escolha o tipo (F - Física, J - Jurídica):");
                    String tipoExibirTodos = scanner.nextLine().strip().toUpperCase();

                    if (tipoExibirTodos.equalsIgnoreCase("F")) {
                        // Obtém uma lista de todas as Pessoas Físicas do banco de dados
                        List<PessoaFisica> pessoasFisicas = pessoaFisicaDAO.getPessoas();
                        for (PessoaFisica pf : pessoasFisicas) {
                            // Exibe os detalhes de cada Pessoa Física
                            pf.exibir();
                        }
                    } else if (tipoExibirTodos.equalsIgnoreCase("J")) {
                        // Obtém uma lista de todas as Pessoas Jurídicas do banco de dados
                        List<PessoaJuridica> pessoasJuridicas = pessoaJuridicaDAO.getPessoas();
                        for (PessoaJuridica pj : pessoasJuridicas) {
                            // Exibe os detalhes de cada Pessoa Jurídica
                            pj.exibir();
                        }
                    } else {
                        System.out.println("Opção inválida.");
                    }
                    break;

                case 0:
                    System.out.println("Encerrando o programa."); // Exibe uma mensagem de encerramento
                    break;

                default:
                    System.out.println("Opção inválida."); // Exibe uma mensagem de opção inválida quando a escolha do usuário não corresponde a nenhum caso anterior
                    break;
            }

        } while (opcao != 0); // O loop continuará enquanto a opção for diferente de 0 (Encerrar)

        scanner.close(); // Fecha o scanner para liberar recursos após o término do programa
    }
}
