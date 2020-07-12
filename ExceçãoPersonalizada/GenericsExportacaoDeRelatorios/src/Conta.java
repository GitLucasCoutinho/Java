// Interface genérica para contas
// <T> é o tipo do saldo (pode ser Double, BigDecimal, etc.)
public interface Conta<T extends Number> {
    String getTitular();
    T getSaldo();
    void depositar(T valor);
    void sacar(T valor);
}