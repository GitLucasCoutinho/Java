import java.util.ArrayList;
import java.util.List;

// Classe principal para testar o sistema bancário com WILDCARDS
public class Main {
    public static void main(String[] args) {
        Banco banco = new Banco();

        // Criando contas
        ContaBancaria conta1 = new ContaBancaria("Lucas", 1000.0);
        ContaBancaria conta2 = new ContaBancaria("Maria", 500.0);
        ContaBancaria conta3 = new ContaBancaria("João", 300.0);

        // Adicionando contas ao banco
        banco.adicionarConta(conta1);
        banco.adicionarConta(conta2);
        banco.adicionarConta(conta3);

        // Relatório genérico com List<?>
        // Aceita qualquer lista, aqui usamos lista de contas
        Relatorio.imprimirListaGenerica(banco.listarContas());

        // Transferência entre contas
        banco.transferir(conta1, conta2, 200.0);

        // Relatório de saldo total com List<? extends ContaBancaria>
        // Aceita lista de ContaBancaria ou subclasses
        Relatorio.imprimirSaldoTotal(banco.listarContas());

        // Relatório com List<? super ContaBancaria>
        // Aceita lista de ContaBancaria ou superclasses (ex: Object)
        List<Object> listaGenerica = new ArrayList<>();
        Relatorio.adicionarContaGenerica(listaGenerica, new ContaBancaria("Ana", 700.0));
        Relatorio.imprimirListaGenerica(listaGenerica);
    }
}