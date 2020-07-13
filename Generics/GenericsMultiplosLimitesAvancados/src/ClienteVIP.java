import java.io.Serializable;

// ClienteVIP é uma ContaBancaria que também implementa duas interfaces:
// - Comparable: permite comparar clientes VIP (ex: pelo saldo).
// - Serializable: permite que o objeto seja "serializado" (transformado em bytes para salvar ou enviar).
public class ClienteVIP extends ContaBancaria implements Comparable<ClienteVIP>, Serializable {

    public ClienteVIP(String titular, double saldoInicial) {
        super(titular, saldoInicial);
    }

    // Implementação da comparação: compara clientes VIP pelo saldo
    @Override
    public int compareTo(ClienteVIP outro) {
        return Double.compare(this.getSaldo(), outro.getSaldo());
    }
}