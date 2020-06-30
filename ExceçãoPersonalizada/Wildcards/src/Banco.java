import java.util.List;

// Classe que gerencia contas bancárias e operações
public class Banco {
    private Repositorio<ContaBancaria> contas = new Repositorio<>();

    // Adiciona uma nova conta ao banco
    public void adicionarConta(ContaBancaria conta) {
        contas.adicionar(conta);
    }

    // Retorna todas as contas
    public List<ContaBancaria> listarContas() {
        return contas.listar();
    }

    // Transferência entre contas
    public void transferir(ContaBancaria origem, ContaBancaria destino, double valor) {
        origem.sacar(valor);   // retira da conta de origem
        destino.depositar(valor); // adiciona na conta de destino
        System.out.println("Transferência de R$" + valor + " realizada de " + origem.getTitular() + " para " + destino.getTitular());
    }
}