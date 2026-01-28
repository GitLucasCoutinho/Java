// ClienteVIP é uma ContaBancaria que também implementa Comparable
// Isso permite comparar clientes VIP pelo saldo
public class ClienteVIP extends ContaBancaria implements Comparable<ClienteVIP> {

    public ClienteVIP(String titular, double saldoInicial) {
        super(titular, saldoInicial);
    }

    // Implementação da comparação: compara pelo saldo
    @Override
    public int compareTo(ClienteVIP outro) {
        return Double.compare(this.getSaldo(), outro.getSaldo());
    }
}