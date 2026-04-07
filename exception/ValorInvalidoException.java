package exception;

public class ValorInvalidoException extends RuntimeException {

    public ValorInvalidoException(double valor) {
        super(String.format("Valor inválido: R$ %.2f. O valor deve ser maior que zero.", valor));
    }
}