package org.example;
import java.util.stream.Collectors;
import java.util.List;
import java.util.stream.Stream;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

                var value = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 7, 8, 7)
                        .map(Object::toString)
                        .toList();

                System.out.println(value);




        List<Integer> values1 = List.of(3, 6, 9, 12);
        List<Integer> values2 = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);

        var newValues = values2.stream()
                .filter(values1::contains)
                .peek(n -> System.out.printf("Filter %s \n", n))
                .map(n -> values1.stream().reduce(n, (n1, n2) -> n1 - n2))
                .peek(n -> System.out.printf("Map %s \n", n))
                .collect(Collectors.toSet());

        System.out.println(newValues);
    }
}



/**
RESULTADO DA EXECUÇÃO:

 * Filter 3
 * Map -27
 * Filter 6
 * Map -24
 * Filter 9
 * Map -21
 * Filter 12
 * Map -18
 * [-18, -21, -24, -27]
 *
 * */


/**
 * Passo a passo com exemplo
 * Pegando o primeiro número filtrado: 3
 * - Lista values1 = [3, 6, 9, 12]
 * - Valor inicial: n = 3
 * Cálculo:
 * - Começa com n1 = 3 (inicial), n2 = 3 → 3 - 3 = 0
 * - Agora acumulador = 0, próximo n2 = 6 → 0 - 6 = -6
 * - Acumulador = -6, próximo n2 = 9 → -6 - 9 = -15
 * - Acumulador = -15, próximo n2 = 12 → -15 - 12 = -27
 * Resultado final: -27
 * Por que sempre dá negativo?
 * Porque você está subtraindo todos os elementos de values1 a partir do valor inicial n.
 * Como values1 tem números grandes (3, 6, 9, 12), o acumulador vai ficando cada vez mais negativo.
 * Resumindo
 * - O reduce está pegando o número inicial (3, 6, 9 ou 12) e subtraindo todos os elementos da lista [3, 6, 9, 12].
 * - Por isso os resultados são -27, -24, -21, -18.*/



/**
 * - O stream() está sendo chamado em values2 → ou seja, quem está sendo percorrido é a lista [1,2,3,...,12].
 * - Para cada elemento de values2, o filter pergunta:
 * “Esse elemento está dentro de values1?”
 * usando o método contains da lista values1.
 * Como funciona na prática
 * - Primeiro elemento de values2 é 1.
 * - values1.contains(1) → false → não passa.
 * - Segundo elemento é 2.
 * - values1.contains(2) → false → não passa.
 * - Terceiro elemento é 3.
 * - values1.contains(3) → true → passa para o próximo estágio.
 * - Isso se repete até o fim da lista.
 * Resultado
 * Só os elementos de values2 que também estão em values1 passam pelo filtro.
 * No caso: 3, 6, 9, 12.*/


