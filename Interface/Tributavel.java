package interfaces;

public interface Tributavel {

    double TAXA_CORRENTE = 0.01;  // 1% sobre saques
    double TAXA_POUPANCA = 0.005; // 0.5% sobre rendimentos

    double calcularTaxa();
}