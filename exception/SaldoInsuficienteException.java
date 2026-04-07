package exception;

public class SaldoInsuficienteException extends RuntimeException {

    private double saldoAtual;
    private double valorSolicitado;

    public SaldoInsuficienteException(double saldoAtual, double valorSolicitado) {
        super(String.format(
                "Saldo insuficiente. Solicitado: R$ %.2f | Disponível: R$ %.2f",
                valorSolicitado, saldoAtual));
        this.saldoAtual = saldoAtual;
        this.valorSolicitado = valorSolicitado;
    }

    public double getSaldoAtual() {
        return saldoAtual;
    }

    public double getValorSolicitado() {
        return valorSolicitado;
    }
}