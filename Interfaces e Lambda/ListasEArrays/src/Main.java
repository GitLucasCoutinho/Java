import java.util.ArrayList;   // Importa a classe ArrayList, que implementa a interface List usando um array dinâmico.
import java.util.LinkedList;  // Importa a classe LinkedList, que implementa a interface List usando uma lista encadeada.
import java.util.List;        // Importa a interface List, que define o comportamento comum para listas em Java.

public class Main {           // Declaração da classe principal chamada "Main".
    public static void main(String[] args) {   // Método principal: ponto de entrada do programa.

        int[] codes = { 789, 852 };            // Cria um array de inteiros com dois valores: 789 e 852.

        System.out.println(codes.length);      // Imprime o tamanho do array (nesse caso, 2).

        List<Integer> codes2 = new ArrayList<>();
        // Cria uma lista dinâmica baseada em array.
        // DIFERENÇAS:
        // - Melhor desempenho para acesso direto (get por índice).
        // - Inserções no fim são rápidas.
        // - Inserções/remoções no meio podem ser lentas (precisa mover elementos).

        List<Integer> codes3 = new LinkedList<>();
        // Cria uma lista dinâmica baseada em nós encadeados.
        // DIFERENÇAS:
        // - Melhor desempenho para inserções/remoções no meio da lista.
        // - Acesso direto por índice é mais lento (precisa percorrer os nós).
        // - Consome mais memória (cada elemento guarda referência para anterior e próximo).

        codes2.add(codes[0]);                  // Adiciona o primeiro elemento do array (789) na lista codes2.
        codes2.add(codes[1]);                  // Adiciona o segundo elemento do array (852) na lista codes2.

        codes2.forEach(System.out::println);   // Percorre a lista codes2 e imprime cada elemento (789 e 852).

        codes2.add(74988);                     // Adiciona o número 74988 à lista codes2.

        codes2.forEach(System.out::println);   // Percorre novamente a lista e imprime todos os elementos:
        // 789, 852 e 74988.
    }
}