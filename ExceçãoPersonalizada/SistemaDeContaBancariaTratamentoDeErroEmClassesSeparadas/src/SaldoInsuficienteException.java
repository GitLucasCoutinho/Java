// Exceção personalizada para saldo insuficiente
// Herda de Exception, ou seja, é uma exceção checada (precisa ser tratada com try/catch)
public class SaldoInsuficienteException extends Exception {

    // Construtor que recebe uma mensagem de erro
    public SaldoInsuficienteException(String mensagem) {
        super(mensagem); // chama o construtor da classe Exception
    }
}