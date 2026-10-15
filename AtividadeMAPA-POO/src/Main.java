
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static final Scanner sc = new Scanner(System.in);
    private static ArrayList<ContaBancaria> contas = new ArrayList<>();

    public static void main(String[] args) {

        int choice;

        do {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1 - Cadastrar conta");
            System.out.println("2 - Fazer operações");
            System.out.println("0 - Sair do programa");
            System.out.print("\nEscolha: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    cadastroDeConta();
                    break;

                case 2:
                    fazerOperacoes();
                    break;

                case 0:
                    System.out.println("\nPrograma encerrado!");
                    break;

                default:
                    System.out.println("\nOpção inválida!");
            }

        } while (choice != 0);

        sc.close();
    }

    private static void cadastroDeConta() {

        System.out.println("\n=== CADASTRO DE CONTA ===");
        System.out.print("\nDigite o nome do titular: ");
        String titular = sc.nextLine();

        if (titular.trim().length() < 2) {
            System.out.println("\nNome inválido!");
            return;
        }

        System.out.print("\nDigite o número da conta: ");
        int numConta = sc.nextInt();

        System.out.print("\nDigite o saldo inicial: R$ ");
        double saldo = sc.nextDouble();
        sc.nextLine();

        if (numConta <= 0 || saldo < 0) {
            System.out.println("\nNúmero da conta ou saldo inválido!");
            return;
        }

        // Verifica se o número da conta já existe
        for (ContaBancaria conta : contas) {
            if (conta.getNumeroConta() == numConta) {
                System.out.println("\nJá existe uma conta com esse número!");
                return;
            }
        }

        // Cria e armazena a nova conta
        ContaBancaria novaConta =
                new ContaBancaria(titular, numConta, saldo);

        contas.add(novaConta);

        System.out.println("\n=== CONTA CRIADA COM SUCESSO ===");
        System.out.println("\nTitular: " + novaConta.getTitular());
        System.out.println("Número da conta: " + novaConta.getNumeroConta());
        System.out.println("Saldo disponível: R$ " + novaConta.getSaldo());
    }

    private static void fazerOperacoes() {

        if (contas.isEmpty()) {
            System.out.println("\nCadastre uma conta primeiro!");
            return;
        }

        System.out.print("\nDigite o número da conta: ");
        int numContaEsp = sc.nextInt();

        ContaBancaria contaEncontrada = null;

        // Procura a conta pelo número
        for (ContaBancaria conta : contas) {
            if (conta.getNumeroConta() == numContaEsp) {
                contaEncontrada = conta;
                break;
            }
        }

        if (contaEncontrada == null) {
            System.out.println("\nConta não encontrada!");
            return;
        }

        int opcao;

        do {
            System.out.println("\n=== OPERAÇÕES DA CONTA ===");
            System.out.println("Titular: " + contaEncontrada.getTitular());
            System.out.println("1 - Consultar saldo");
            System.out.println("2 - Depositar");
            System.out.println("3 - Sacar");
            System.out.println("0 - Voltar ao menu principal");
            System.out.print("\nEscolha: ");

            opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    consultarContaEspecifica(contaEncontrada);
                    break;

                case 2:
                    System.out.print("\nValor do depósito: R$ ");
                    double deposito = sc.nextDouble();
                    contaEncontrada.depositar(deposito);
                    break;

                case 3:
                    System.out.print("\nValor do saque: R$ ");
                    double saque = sc.nextDouble();
                    contaEncontrada.sacar(saque);
                    break;

                case 0:
                    System.out.println("\nVoltando ao menu principal...");
                    break;

                default:
                    System.out.println("\nOpção inválida!");
            }

        } while (opcao != 0);
    }

    private static void consultarContaEspecifica(ContaBancaria conta) {

        System.out.println("\n=== DADOS DA CONTA ===");
        System.out.println("\nTitular: " + conta.getTitular());
        System.out.println("Número da conta: " + conta.getNumeroConta());
        System.out.println("Saldo atual: R$ " + conta.getSaldo());
    }
}
