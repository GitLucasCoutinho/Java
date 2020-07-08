// Método genérico com múltiplos limites
// <T extends ContaBancaria & Comparable<T> & java.io.Serializable>
// Significa que o tipo T precisa ser:
// 1. Uma subclasse de ContaBancaria (ou a própria ContaBancaria)
// 2. Implementar a interface Comparable<T> (ou seja, pode ser comparado com outro objeto do mesmo tipo)
// 3. Implementar a interface Serializable (ou seja, pode ser transformado em bytes para salvar ou enviar)
//
// O símbolo & funciona como um "E lógico": T precisa ser ContaBancaria E Comparable E Serializable.
public static <T extends ContaBancaria & Comparable<T> & java.io.Serializable>
void imprimirMaiorSaldo(List<T> contas) {

    // Primeiro verificamos se a lista está vazia
    if (contas.isEmpty()) {
        System.out.println("Nenhuma conta disponível.");
        return; // se não há contas, encerramos o método
    }

    // Assume que a primeira conta da lista é a que tem maior saldo
    T maior = contas.get(0);

    // Percorre todas as contas da lista
    for (T conta : contas) {
        // Usa o método compareTo (da interface Comparable) para comparar saldos
        // Se conta.compareTo(maior) > 0, significa que 'conta' tem saldo maior que 'maior'
        if (conta.compareTo(maior) > 0) {
            maior = conta; // atualiza a referência para a conta com maior saldo
        }
    }

    // Exibe a conta com maior saldo
    System.out.println("🏆 Conta com maior saldo: " + maior);
}