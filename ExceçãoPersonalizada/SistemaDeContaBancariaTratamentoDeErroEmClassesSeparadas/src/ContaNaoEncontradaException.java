// Exceção personalizada para quando uma conta não é encontrada
public class ContaNaoEncontradaException extends Exception {

    // Construtor que recebe uma mensagem de erro
    public ContaNaoEncontradaException(String mensagem) {
        super(mensagem);
    }
}