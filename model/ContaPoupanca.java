package model;

import exception.SaldoInsuficienteException;
import exception.ValorInvalidoException;
import interfaces.Tributavel;

public class ContaPoupanca extends Conta implements Tributavel {

    private double taxaRendimento;

    public ContaPoupanca(String titular, double taxaRendimento) {
        super(titular);
        this.taxaRendimento = taxaRendimento;
    }

    @Override
    public void sacar(double valor) {
        if (valor <= 0) throw new ValorInvalidoException(valor);
        if (valor > this.saldo) {
            throw new SaldoInsuficienteException(this.saldo, valor);
        }
        this.saldo -= valor;
        registrar(String.format("Saque: R$ %.2f", valor));
    }

    public void renderJuros() {
        double rendimento = this.saldo * this.taxaRendimento;
        this.saldo += rendimento;
        registrar(String.format("Rendimento de juros: +R$ %.2f (taxa %.1f%%)",
            rendimento, taxaRendimento * 100));
    }

    @Override
    public double calcularTaxa() {
    return this.saldo * TAXA_POUPANCA;
    }

    public double getTaxaRendimento() { return taxaRendimento; }
}

