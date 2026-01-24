import java.util.ArrayList;   // Importa a classe ArrayList, que implementa a interface List usando um array dinâmico.
import java.util.Collections;
import java.util.LinkedList;  // Importa a classe LinkedList, que implementa a interface List usando uma lista encadeada.
import java.util.List;        // Importa a interface List, que define o comportamento comum para listas em Java.
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

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



        List<User> users = new ArrayList<>();
        // Cria uma lista dinâmica para armazenar objetos do tipo User.
        // Útil para gerenciar coleções de usuários em aplicações.
        var user = new User("Ocooldev", 12, 1);
        users.add(user);
        users.add(new User("Maria", 22, 2)); // As duas linhas criam um user e insere na lista em linhas separadas, e desta forma fizemos a inserçao na mesma linha.
        users.contains(user);
        System.out.println(users.size());
        System.out.println(users.isEmpty());
        System.out.println(users.getFirst().toString());
        System.out.println(users.getLast().toString());
        System.out.println(users.get(users.size() -1).toString());// desta forma era feito antes do java 21 para ver o ultimo elemento de uma lista.
        System.out.println(users);
        System.out.println(users.remove(new User("Maria", 22, 2))); // Removendo o user Maria da lista usando equals sobrescrito.
        System.out.println(users);
        System.out.println(users.remove(0));
        System.out.println(users);
        users.clear();
        System.out.println(users);
        System.out.println(users.add(new User("Lucas", 31, 3)));
        System.out.println(users);


        /*- Vector → lista dinâmica antiga, sincronizada por padrão (segura em multi‑thread, mas mais lenta).
        - ArrayList → lista dinâmica moderna, rápida para acesso por índice, não sincronizada.
        - CopyOnWriteArrayList → lista dinâmica moderna, sincronizada, ótima para muitas leituras e poucas escritas.
             Em resumo:
        - Se você quer simplicidade e velocidade → use ArrayList.
        - Se precisa de segurança em multi‑thread → prefira CopyOnWriteArrayList.
        - Se está mexendo em código legado → pode encontrar Vector, mas hoje quase não se usa.
*/

        //Vector
        // Cria uma lista dinâmica usando Vector (Legado antigo Java2).
        // O Vector é parecido com ArrayList, mas é sincronizado por padrão (thread-safe).
        Vector<String> lista = new Vector<>();

        lista.add("Lucas");     // Adiciona um elemento.
        lista.add("Ocooldev");  // Adiciona outro elemento.

        // Percorre e imprime os elementos da lista.
        lista.forEach(System.out::println);


        //CopyOnWriteArrayList → lista dinâmica moderna
        // Cria uma lista segura para múltiplas threads (CopyOnWriteArrayList).
        // Cada vez que alguém modifica a lista, uma cópia interna é feita.
        CopyOnWriteArrayList<String> lista = new CopyOnWriteArrayList<>();

        lista.add("Lucas");   // Adiciona um elemento.
        lista.add("Ocooldev"); // Adiciona outro elemento.

        // Percorre e imprime os elementos da lista.
        lista.forEach(System.out::println);



        //Collections.synchronizedList
        // Cria uma lista ArrayList normal, mas envolvida por Collections.synchronizedList.
        // Isso garante que todos os métodos sejam sincronizados (thread-safe).
        List<String> lista = Collections.synchronizedList(new ArrayList<>());

        lista.add("Lucas");   // Adiciona um elemento.
        lista.add("Ocooldev"); // Adiciona outro elemento.

        // Para percorrer com segurança em múltiplas threads, usamos synchronized no bloco.
        synchronized (lista) {
            lista.forEach(System.out::println);


        }
}