package service;

import model.Cliente;
import model.Conta;
import model.ContaCorrente;
import model.ContaPoupanca;

import java.util.ArrayList;
import java.util.List;

public class Banco {

    private String nome;
    private List<Cliente> clientes;

    public Banco(String nome) {
        this.nome = nome;
        this.clientes = new ArrayList<>();
    }

    public Cliente cadastrarCliente(String nome, String cpf) {
        if (buscarCliente(cpf) != null) {
            System.out.println("Cliente com CPF " + cpf + " já cadastrado.");
            return null;
        }
        Cliente cliente = new Cliente(nome, cpf);
        clientes.add(cliente);
        System.out.println("Cliente " + nome + " cadastrado com sucesso.");
        return cliente;
    }

    public ContaCorrente abrirContaCorrente(String cpf, double limite) {
        Cliente cliente = buscarCliente(cpf);
        if (cliente == null) {
            System.out.println("Cliente não encontrado.");
            return null;
        }
        ContaCorrente conta = new ContaCorrente(cliente.getNome(), limite);
        cliente.adicionarConta(conta);
        System.out.printf("Conta corrente aberta. Número: %d | Limite: R$ %.2f%n",
            conta.getNumero(), limite);
        return conta;
    }

    public ContaPoupanca abrirContaPoupanca(String cpf, double taxaRendimento) {
        Cliente cliente = buscarCliente(cpf);
        if (cliente == null) {
            System.out.println("Cliente não encontrado.");
            return null;
        }
        ContaPoupanca conta = new ContaPoupanca(cliente.getNome(), taxaRendimento);
        cliente.adicionarConta(conta);
        System.out.printf("Conta poupança aberta. Número: %d | Taxa: %.1f%%%n",
            conta.getNumero(), taxaRendimento * 100);
        return conta;
    }

    public Cliente buscarCliente(String cpf) {
        for (Cliente c : clientes) {
            if (c.getCpf().equals(cpf)) return c;
        }
        return null;
    }

    public void listarClientes() {
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
            return;
        }
        System.out.println("=== Clientes do " + nome + " ===");
        for (Cliente c : clientes) {
            System.out.println("  " + c.getNome() + " — CPF: " + c.getCpf());
            c.listarContas();
        }
    }

    public String getNome() { return nome; }
}