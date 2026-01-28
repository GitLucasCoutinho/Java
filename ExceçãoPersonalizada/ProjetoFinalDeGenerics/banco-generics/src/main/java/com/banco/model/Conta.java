// Interface genérica para contas bancárias
// O <T extends Number> significa que o saldo da conta deve ser um tipo numérico (Double, Integer, BigDecimal).
// Isso garante que sempre poderemos realizar operações matemáticas com o saldo.
public interface Conta<T extends Number> {

    // Retorna o cliente/titular da conta
    String getTitular();

    // Retorna o saldo atual da conta
    T getSaldo();

    // Realiza um depósito na conta
    void depositar(T valor);

    // Realiza um saque na conta
    void sacar(T valor);
}