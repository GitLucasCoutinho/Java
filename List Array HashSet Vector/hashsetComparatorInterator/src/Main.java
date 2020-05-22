//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
import java.util.Comparator;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        // HASHSET
        // HashSet é uma coleção que NÃO permite elementos duplicados.
        // Ele também não garante ordem dos elementos.
        Set<String> nomes = new HashSet<>();
        nomes.add("Lucas");
        nomes.add("Maria");
        nomes.add("João");
        nomes.add("Lucas"); // duplicado, será ignorado automaticamente

        System.out.println("HashSet (sem duplicados): " + nomes);

        // ITERATOR
        // Iterator é usado para percorrer os elementos de uma coleção.
        // Ele funciona como um "cursor" que vai passando por cada item.
        System.out.println("Iterando sobre HashSet:");
        Iterator<String> it = nomes.iterator();
        while (it.hasNext()) { // enquanto houver próximo elemento
            String nome = it.next(); // pega o próximo
            System.out.println(nome);
        }

        // COMPARATOR
        // Comparator serve para definir uma regra de comparação personalizada.
        // Aqui vamos ordenar as Strings pelo tamanho (quantidade de caracteres).
        Comparator<String> comparadorPorTamanho = new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                // retorna negativo se s1 < s2, positivo se s1 > s2, 0 se iguais
                return Integer.compare(s1.length(), s2.length());
            }
        };

        // TreeSet é uma coleção que mantém os elementos ORDENADOS.
        // Se passarmos um Comparator, ele usará essa regra para ordenar.
        Set<String> nomesOrdenados = new TreeSet<>(comparadorPorTamanho);
        nomesOrdenados.addAll(nomes);

        System.out.println("TreeSet ordenado por tamanho: " + nomesOrdenados);

        // ORDENANDO ALFABETICAMENTE
        // Para mostrar ordenação por ordem alfabética, usamos uma lista e Collections.sort.
        List<String> listaNomes = new ArrayList<>(nomes);
        Collections.sort(listaNomes); // ordena em ordem alfabética
        System.out.println("Lista ordenada alfabeticamente: " + listaNomes);

        // REMOVENDO ELEMENTOS COM ITERATOR
        // Vamos remover "Maria" da lista usando Iterator (forma segura).
        Iterator<String> it2 = listaNomes.iterator();
        while (it2.hasNext()) {
            String nome = it2.next();
            if (nome.equals("Maria")) {
                it2.remove(); // remove diretamente da lista original
            }
        }
        System.out.println("Lista depois da remoção de 'Maria': " + listaNomes);

        // PERCORRENDO COM ITERATOR
        // Agora percorremos novamente a lista com Iterator para mostrar os elementos restantes.
        System.out.println("Percorrendo lista com Iterator:");
        it2 = listaNomes.iterator(); // reiniciamos o Iterator
        while (it2.hasNext()) {
            String nome = it2.next();
            System.out.println(nome);
        }
    }
}