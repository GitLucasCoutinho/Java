// Classe que centraliza todas as exceções personalizadas do sistema bancário
public class BancoExceptions {

    // Exceção para saldo insuficiente
    public static class SaldoInsuficienteException extends Exception {
        public SaldoInsuficienteException(String mensagem) {
            super(mensagem); // chama o construtor da classe Exception
        }
    }

    // Exceção para valores inválidos (ex: depósito negativo ou zero)
    public static class ValorInvalidoException extends Exception {
        public ValorInvalidoException(String mensagem) {
            super(mensagem);
        }
    }

    // Exceção para conta não encontrada
    public static class ContaNaoEncontradaException extends Exception {
        public ContaNaoEncontradaException(String mensagem) {
            super(mensagem);
        }
    }
}