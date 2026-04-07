package model;

import exception.ValorInvalidoException;
import java.util.ArrayList;
import java.util.List;

import exception.ValorInvalidoException;

public abstract class Conta {

    private static int sequencial = 1;

    private int numero;
    private String titular;
    protected double saldo;
    private List<String> historico;

    public Conta(String titular) {
        this.titular = titular;
        this.numero = sequencial++;
        this.saldo = 0;
        this.historico = new ArrayList<>();
    }

    public void depositar(double valor) {
        if (valor <= 0) {
            throw new ValorInvalidoException(valor);
        }
        this.saldo += valor;
        registrar(String.format("Depósito: R$ %.2f", valor));
    }

    public abstract void sacar(double valor) throws Exception;

    public void transferir(double valor, Conta destino) throws Exception {
        this.sacar(valor);
        destino.depositar(valor);
        registrar("Transferência para conta " + destino.getNumero() + ": R$ " + valor);
    }

    protected void registrar(String operacao) {
        historico.add(operacao);
    }

    public void extrato() {
        System.out.println("=== Extrato — Conta " + numero + " (" + titular + ") ===");
        System.out.printf("Saldo atual: R$ %.2f%n", saldo);
        System.out.println("--- Histórico ---");
        for (String op : historico) {
            System.out.println("  " + op);
        }
    }

    public int getNumero() {
        return numero;
    }

    public String getTitular() {
        return titular;
    }

    public double getSaldo() {
        return saldo;
    }
}