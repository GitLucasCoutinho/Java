// Classe genérica com restrição (bounded generics).
// <T extends Number> significa que só aceita tipos que herdam de Number (Integer, Double, Float, etc.).
public class NumeroCaixa<T extends Number> {
    private T valor; // valor armazenado

    // Construtor: recebe um número
    public NumeroCaixa(T valor) {
        this.valor = valor;
    }

    // Retorna o valor como double
    // Como T é garantidamente um Number, podemos usar métodos de Number
    public double getValorComoDouble() {
        return valor.doubleValue();
    }

    // Retorna o valor original
    public T getValor() {
        return valor;
    }
}