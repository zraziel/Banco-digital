package model;

import exception.SaldoInsuficienteException;
import exception.ValorInvalidoException;
import interfaces.Tributavel;

public class ContaCorrente extends Conta implements Tributavel {

    private double limiteCredito;

    public ContaCorrente(String titular, double limiteCredito) {
        super(titular);
        this.limiteCredito = limiteCredito;
    }

    @Override
    public void sacar(double valor) {
        if (valor <= 0) throw new ValorInvalidoException(valor);
        if (valor > this.saldo + this.limiteCredito) {
            throw new SaldoInsuficienteException(this.saldo + this.limiteCredito, valor);
        }
        this.saldo -= valor;
        registrar(String.format("Saque: R$ %.2f", valor));
    }

    @Override
    public double calcularTaxa() {
        return this.saldo * TAXA_CORRENTE;
    }

    public double getLimiteCredito() { return limiteCredito; }
}