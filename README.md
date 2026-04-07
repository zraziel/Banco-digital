# Banco-digital
Sistema bancário desenvolvido em Java aplicando os pilares de POO: herança, polimorfismo, encapsulamento e abstração.

## Como rodar

1. Clone o repositório
```bash
   git clone https://github.com/SEU_USUARIO/banco-digital-java.git
```
2. Abra na sua IDE de preferência
3. Abra a pasta "src"
4. Compile e execute o arquivo `Main.java`

## Decisões de design

- **`Conta` é abstrata** porque nunca faz sentido instanciar uma conta genérica — toda conta precisa ser corrente ou poupança, cada uma com suas próprias regras de saque.
- **`Tributavel` é uma interface** e não uma classe abstrata porque tributação é um comportamento opcional — nem toda conta futura precisará ser tributável.
- **Exceções como `RuntimeException`** porque erros de negócio como saldo insuficiente são responsabilidade de quem chama o método, não do compilador.

## Tecnologias

- Java 11+
- POO puro (sem frameworks externos)
