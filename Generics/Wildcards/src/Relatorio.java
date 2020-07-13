import java.util.List;

// Classe utilitária para gerar relatórios usando WILDCARDS
// Wildcards são "curingas" que dão flexibilidade ao trabalhar com coleções genéricas.
public class Relatorio {

    // List<?> → aceita lista de QUALQUER tipo
    // Não sabemos o tipo exato, então tratamos os elementos como Object
    public static void imprimirListaGenerica(List<?> lista) {
        System.out.println("📋 Relatório genérico:");
        for (Object item : lista) { // cada elemento é tratado como Object
            System.out.println(item);
        }
    }

    // List<? extends ContaBancaria> → aceita lista de ContaBancaria ou SUBCLASSES
    // Isso garante que podemos acessar métodos de ContaBancaria (como getSaldo)
    public static void imprimirSaldoTotal(List<? extends ContaBancaria> contas) {
        double total = 0;
        for (ContaBancaria conta : contas) {
            total += conta.getSaldo(); // podemos usar getSaldo porque sabemos que é ContaBancaria
        }
        System.out.println("💰 Saldo total do banco: R$" + total);
    }

    // List<? super ContaBancaria> → aceita lista de ContaBancaria ou SUPERCLASSES
    // Isso é útil para adicionar objetos ContaBancaria em coleções mais genéricas (ex: List<Object>)
    public static void adicionarContaGenerica(List<? super ContaBancaria> lista, ContaBancaria conta) {
        lista.add(conta); // podemos adicionar ContaBancaria sem problemas
        System.out.println("Conta adicionada ao relatório genérico: " + conta);
    }
}