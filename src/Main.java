import model.Conta;
import model.Cliente;
import model.ContaCorrente;
import model.ContaPoupanca;
import service.Banco;

import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static Banco banco = new Banco("BancoDigital");

    public static void main(String[] args) {
        int opcao = -1;

        while (opcao != 0) {
            exibirMenu();
            opcao = lerInt("Escolha: ");

            switch (opcao) {
                case 1:
                    cadastrarCliente();
                    break;
                case 2:
                    abrirConta();
                    break;
                case 3:
                    depositar();
                    break;
                case 4:
                    sacar();
                    break;
                case 5:
                    transferir();
                    break;
                case 6:
                    verExtrato();
                    break;
                case 7:
                    banco.listarClientes();
                    break;
                case 0:
                    System.out.println("Até logo!");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }

            System.out.println();
        }
    }

    static void exibirMenu() {
        System.out.println("╔══════════════════════════╗");
        System.out.println("║      BANCO DIGITAL       ║");
        System.out.println("╠══════════════════════════╣");
        System.out.println("║ 1. Cadastrar cliente     ║");
        System.out.println("║ 2. Abrir conta           ║");
        System.out.println("║ 3. Depositar             ║");
        System.out.println("║ 4. Sacar                 ║");
        System.out.println("║ 5. Transferir            ║");
        System.out.println("║ 6. Ver extrato           ║");
        System.out.println("║ 7. Listar clientes       ║");
        System.out.println("║ 0. Sair                  ║");
        System.out.println("╚══════════════════════════╝");
    }

    static void cadastrarCliente() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        banco.cadastrarCliente(nome, cpf);
    }

    static void abrirConta() {
        System.out.print("CPF do cliente: ");
        String cpf = scanner.nextLine();

        System.out.println("Tipo: 1 - Corrente | 2 - Poupança");
        int tipo = lerInt("Tipo: ");

        if (tipo == 1) {
            double limite = lerDouble("Limite de crédito: R$ ");
            banco.abrirContaCorrente(cpf, limite);
        } else if (tipo == 2) {
            double taxa = lerDouble("Taxa de rendimento (ex: 0.05 para 5%): ");
            banco.abrirContaPoupanca(cpf, taxa);
        } else {
            System.out.println("Tipo inválido.");
        }
    }

    static void depositar() {
        Conta conta = buscarContaPorCpf();
        if (conta == null)
            return;
        double valor = lerDouble("Valor do depósito: R$ ");
        try {
            conta.depositar(valor);
            System.out.printf("Depósito de R$ %.2f realizado com sucesso.%n", valor);
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    static void sacar() {
        Conta conta = buscarContaPorCpf();
        if (conta == null)
            return;
        double valor = lerDouble("Valor do saque: R$ ");
        try {
            conta.sacar(valor);
            System.out.printf("Saque de R$ %.2f realizado com sucesso.%n", valor);
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    static void transferir() {
        System.out.println("--- Conta de origem ---");
        Conta origem = buscarContaPorCpf();
        if (origem == null)
            return;

        System.out.println("--- Conta de destino ---");
        Conta destino = buscarContaPorCpf();
        if (destino == null)
            return;

        double valor = lerDouble("Valor da transferência: R$ ");
        try {
            origem.transferir(valor, destino);
            System.out.printf("Transferência de R$ %.2f realizada com sucesso.%n", valor);
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    static void verExtrato() {
        Conta conta = buscarContaPorCpf();
        if (conta == null)
            return;
        System.out.println();
        conta.extrato();
    }

    static Conta buscarContaPorCpf() {
        System.out.print("CPF do cliente: ");
        String cpf = scanner.nextLine();
        Cliente cliente = banco.buscarCliente(cpf);
        if (cliente == null) {
            System.out.println("Cliente não encontrado.");
            return null;
        }
        cliente.listarContas();
        int numero = lerInt("Número da conta: ");
        Conta conta = cliente.buscarConta(numero);
        if (conta == null) {
            System.out.println("Conta não encontrada.");
        }
        return conta;
    }

    static int lerInt(String mensagem) {
        System.out.print(mensagem);
        while (!scanner.hasNextInt()) {
            System.out.print("Digite um número válido: ");
            scanner.next();
        }
        int valor = scanner.nextInt();
        scanner.nextLine();
        return valor;
    }

    static double lerDouble(String mensagem) {
        System.out.print(mensagem);
        while (!scanner.hasNextDouble()) {
            System.out.print("Digite um valor válido: ");
            scanner.next();
        }
        double valor = scanner.nextDouble();
        scanner.nextLine();
        return valor;
    }
}