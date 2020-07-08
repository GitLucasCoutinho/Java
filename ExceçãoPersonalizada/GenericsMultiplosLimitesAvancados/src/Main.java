import java.util.ArrayList;
import java.util.List;

// Classe principal para testar múltiplos limites avançados
public class Main {
    public static void main(String[] args) {
        // Criando clientes VIP
        ClienteVIP cliente1 = new ClienteVIP("Lucas", 1500.0);
        ClienteVIP cliente2 = new ClienteVIP("Maria", 2500.0);
        ClienteVIP cliente3 = new ClienteVIP("João", 1800.0);

        // Lista de clientes VIP
        List<ClienteVIP> clientes = new ArrayList<>();
        clientes.add(cliente1);
        clientes.add(cliente2);
        clientes.add(cliente3);

        // Relatório usando múltiplos limites avançados
        Relatorio.imprimirMaiorSaldo(clientes);
    }
}