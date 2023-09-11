package cadastropoo;

/**
 *
 * @author Felipe
 */

// Importações necessárias
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import model.PessoaFisica;
import model.PessoaFisicaRepo;
import model.PessoaJuridica;
import model.PessoaJuridicaRepo;

public class cadastropoo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Inicialização dos repositórios para pessoas físicas e jurídicas
        PessoaFisicaRepo repo1 = new PessoaFisicaRepo();
        PessoaJuridicaRepo repo2 = new PessoaJuridicaRepo();

        int opcao = -1;

        while (opcao != 0) {
            System.out.println("╔═════════════════════════════════╗");
            System.out.println("║      MENU PRINCIPAL       ║");
            System.out.println("╟─────────────────────────────────╢");
            System.out.println("║ Selecione uma opção:      ║");
            System.out.println("║ 1 - Incluir               ║");
            System.out.println("║ 2 - Alterar               ║");
            System.out.println("║ 3 - Excluir               ║");
            System.out.println("║ 4 - Exibir pelo ID        ║");
            System.out.println("║ 5 - Exibir todos          ║");
            System.out.println("║ 6 - Salvar dados          ║");
            System.out.println("║ 7 - Recuperar dados       ║");
            System.out.println("║ 0 - Finalizar             ║");
            System.out.println("╚═════════════════════════════════╝");


            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do scanner

            switch (opcao) {
                case 1:
                    // Opção para incluir uma nova pessoa (física ou jurídica)
                    System.out.println("Selecione o tipo (F - Física, J - Jurídica):");
                    String tipo1 = scanner.next().strip().toUpperCase();
                    scanner.nextLine(); // Limpar o buffer do scanner

                    if (tipo1.equals("F")) {
                        // Inclusão de pessoa física
                        System.out.println("Digite o ID:");
                        int id1 = scanner.nextInt();
                        scanner.nextLine(); // Limpar o buffer do scanner

                        System.out.println("Digite o nome:");
                        String nome1 = scanner.nextLine();

                        System.out.println("Digite o CPF:");
                        String cpf = scanner.nextLine();

                        System.out.println("Digite a idade:");
                        int idade = scanner.nextInt();
                        scanner.nextLine(); // Limpar o buffer do scanner

                        // Criação de um objeto PessoaFisica e inserção no repositório
                        PessoaFisica pessoaFisica = new PessoaFisica(id1, nome1, cpf, idade);
                        repo1.inserir(pessoaFisica);
                    } else if (tipo1.equals("J")) {
                        // Inclusão de pessoa jurídica
                        System.out.println("Digite o ID:");
                        int id2 = scanner.nextInt();
                        scanner.nextLine(); // Limpar o buffer do scanner

                        System.out.println("Digite o nome:");
                        String nome2 = scanner.nextLine();

                        System.out.println("Digite o CNPJ:");
                        String cnpj = scanner.nextLine();

                        // Criação de um objeto PessoaJuridica e inserção no repositório
                        PessoaJuridica pessoaJuridica = new PessoaJuridica(id2, nome2, cnpj);
                        repo2.inserir(pessoaJuridica);
                    }
                    break;

                case 2:
                    // Opção para alterar os dados de uma pessoa (física ou jurídica)
                    System.out.println("Selecione o tipo (F - Física, J - Jurídica):");
                    String tipo2 = scanner.next().strip().toUpperCase();
                    scanner.nextLine(); // Limpar o buffer do scanner

                    if (tipo2 .equals("F")) {
                        // Alteração de pessoa física
                        System.out.println("Digite o ID da pessoa física a ser alterada:");
                        int id1 = scanner.nextInt();
                        scanner.nextLine(); // Limpar o buffer do scanner

                        PessoaFisica pessoaFisica = repo1.obter(id1);
                        if (pessoaFisica != null) {
                            // Exibe os dados da pessoa física
                            pessoaFisica.exibir();

                            System.out.println("Digite o novo nome:");
                            String nome1 = scanner.nextLine();

                            System.out.println("Digite o novo CPF:");
                            String cpf = scanner.nextLine();

                            System.out.println("Digite a nova idade:");
                            int idade = scanner.nextInt();
                            scanner.nextLine(); // Limpar o buffer do scanner

                            // Atualiza os dados da pessoa física
                            pessoaFisica.setNome(nome1);
                            pessoaFisica.setCpf(cpf);
                            pessoaFisica.setIdade(idade);

                            // Realiza a alteração no repositório
                            repo1.alterar(pessoaFisica);
                            System.out.println("Pessoa física alterada com sucesso.");
                        } else {
                            System.out.println("Pessoa física não encontrada.");
                        }
                    } else if (tipo2.equals("J")) {
                        // Alteração de pessoa jurídica
                        System.out.println("Digite o ID da pessoa jurídica a ser alterada:");
                        int id2 = scanner.nextInt();
                        scanner.nextLine(); // Limpar o buffer do scanner

                        PessoaJuridica pessoaJuridica = repo2.obter(id2);
                        if (pessoaJuridica != null) {
                            // Exibe os dados da pessoa jurídica
                            pessoaJuridica.exibir();

                            System.out.println("Digite o novo nome:");
                            String nome2 = scanner.nextLine();

                            System.out.println("Digite o novo CNPJ:");
                            String cnpj = scanner.nextLine();

                            // Atualiza os dados da pessoa jurídica
                            pessoaJuridica.setNome(nome2);
                            pessoaJuridica.setCnpj(cnpj);

                            // Realiza a alteração no repositório
                            repo2.alterar(pessoaJuridica);
                            System.out.println("Pessoa jurídica alterada com sucesso.");
                        } else {
                            System.out.println("Pessoa jurídica não encontrada.");
                        }
                    }
                    break;

                case 3:
                    // Opção para excluir uma pessoa (física ou jurídica)
                    System.out.println("Selecione o tipo (F - Física, J - Jurídica):");
                   String tipo3 = scanner.next().strip().toUpperCase();
                    scanner.nextLine(); // Limpar o buffer do scanner

                    if (tipo3.equals("F")) {
                        // Exclusão de pessoa física
                        System.out.println("Digite o ID da pessoa física a ser excluída:");
                        int id1 = scanner.nextInt();
                        scanner.nextLine(); // Limpar o buffer do scanner

                        PessoaFisica pessoaFisica = repo1.obter(id1);
                        if (pessoaFisica != null) {
                            // Exibe os dados da pessoa física a ser excluída
                            pessoaFisica.exibir();
                            // Realiza a exclusão no repositório
                            repo1.excluir(id1);
                            System.out.println("Pessoa física excluída com sucesso.");
                        } else {
                            System.out.println("Pessoa física não encontrada.");
                        }
                    } else if (tipo3.equals("J")) {
                        // Exclusão de pessoa jurídica
                        System.out.println("Digite o ID da pessoa jurídica a ser excluída:");
                        int id2 = scanner.nextInt();
                        scanner.nextLine(); // Limpar o buffer do scanner

                        PessoaJuridica pessoaJuridica = repo2.obter(id2);
                        if (pessoaJuridica != null) {
                            // Exibe os dados da pessoa jurídica a ser excluída
                            pessoaJuridica.exibir();
                            // Realiza a exclusão no repositório
                            repo2.excluir(id2);
                            System.out.println("Pessoa jurídica excluída com sucesso.");
                        } else {
                            System.out.println("Pessoa jurídica não encontrada.");
                        }
                    }
                    break;

                case 4:
                    // Opção para exibir os dados de uma pessoa pelo ID
                    System.out.println("Digite o ID:");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // Limpar o buffer do scanner

                    PessoaFisica pessoaFisica = repo1.obter(id);
                    PessoaJuridica pessoaJuridica = repo2.obter(id);

                    if (pessoaFisica != null) {
                        // Exibe os dados da pessoa física
                        pessoaFisica.exibir();
                    } else if (pessoaJuridica != null) {
                        // Exibe os dados da pessoa jurídica
                        pessoaJuridica.exibir();
                    } else {
                        System.out.println("Pessoa não encontrada.");
                    }
                    break;

                case 5:
                    // Opção para exibir todos os registros de pessoas físicas e jurídicas
                    System.out.println("Pessoas físicas:");
                    ArrayList<PessoaFisica> pessoasFisicas = repo1.obterTodos();
                    for (PessoaFisica pf : pessoasFisicas) {
                        pf.exibir();
                        System.out.println();
                    }

                    System.out.println("Pessoas jurídicas:");
                    ArrayList<PessoaJuridica> pessoasJuridicas = repo2.obterTodos();
                    for (PessoaJuridica pj : pessoasJuridicas) {
                        pj.exibir();
                        System.out.println();
                    }
                    break;

                case 6:
                    try {
                        // Opção para salvar os dados em arquivos de persistência
                        System.out.println("Digite o nome do arquivo de persistência para pessoas físicas:");
                        String arquivo1 = scanner.nextLine();
                        repo1.persistir(arquivo1);

                        System.out.println("Digite o nome do arquivo de persistência para pessoas jurídicas:");
                        String arquivo2 = scanner.nextLine();
                        repo2.persistir(arquivo2);

                        System.out.println("Dados salvos com sucesso.");
                    } catch (IOException e) {
                        System.out.println("Erro ao salvar os dados.");
                        e.printStackTrace();
                    }
                    break;

                case 7:
                    try {
                        // Opção para recuperar os dados de arquivos de persistência
                        System.out.println("Digite o nome do arquivo de recuperação para pessoas físicas:");
                        String arquivo1 = scanner.nextLine();
                        repo1.recuperar(arquivo1);

                        System.out.println("Digite o nome do arquivo de recuperação para pessoas jurídicas:");
                        String arquivo2 = scanner.nextLine();
                        repo2.recuperar(arquivo2);

                        System.out.println("Dados recuperados com sucesso.");
                    } catch (IOException | ClassNotFoundException e) {
                        System.out.println("Erro ao recuperar os dados.");
                        e.printStackTrace();
                    }
                    break;

                case 0:
                    // Opção para finalizar o programa
                    System.out.println("Finalizando o programa...");
                    break;

                default:
                    // Mensagem de opção inválida
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }

            System.out.println();
        }

        scanner.close();
    }
}
