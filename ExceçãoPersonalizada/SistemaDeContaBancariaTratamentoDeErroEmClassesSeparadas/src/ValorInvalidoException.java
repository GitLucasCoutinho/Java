// Exceção personalizada para valores inválidos (ex: depósito negativo ou zero)
public class ValorInvalidoException extends Exception {

    // Construtor que recebe uma mensagem de erro
    public ValorInvalidoException(String mensagem) {
        super(mensagem);
    }
}