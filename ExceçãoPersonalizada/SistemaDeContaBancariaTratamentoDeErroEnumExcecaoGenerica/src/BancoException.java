// Exceção genérica que usa o enum TipoErro para categorizar o erro
public class BancoException extends Exception {
    private TipoErro tipo; // guarda o tipo de erro associado

    // Construtor: recebe o tipo de erro e a mensagem
    public BancoException(TipoErro tipo, String mensagem) {
        super(mensagem); // chama o construtor da classe Exception para armazenar a mensagem
        this.tipo = tipo; // guarda o tipo de erro
    }

    // Getter para acessar o tipo de erro
    public TipoErro getTipo() {
        return tipo;
    }
}