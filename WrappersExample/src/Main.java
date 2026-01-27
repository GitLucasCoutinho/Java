import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        // 🔎 Em Java, tipos primitivos (int, double, char, etc.) não são objetos.
        // Para tratá-los como objetos, existem as "Wrapper Classes".
        // Elas embrulham (wrap) o valor primitivo dentro de um objeto.

        // ✅ Integer (wrapper para int)
        int numeroPrimitivo = 10; // tipo primitivo
        Integer numeroWrapper = Integer.valueOf(numeroPrimitivo); // embrulha o int em um objeto
        System.out.println("Integer: " + numeroWrapper); // imprime 10

        // ✅ Double (wrapper para double)
        double precoPrimitivo = 99.99;
        Double precoWrapper = Double.valueOf(precoPrimitivo);
        System.out.println("Double: " + precoWrapper); // imprime 99.99

        // ✅ Character (wrapper para char)
        char letraPrimitiva = 'A';
        Character letraWrapper = Character.valueOf(letraPrimitiva);
        System.out.println("Character: " + letraWrapper); // imprime A

        // ✅ Boolean (wrapper para boolean)
        boolean ativoPrimitivo = true;
        Boolean ativoWrapper = Boolean.valueOf(ativoPrimitivo);
        System.out.println("Boolean: " + ativoWrapper); // imprime true

        // ✅ Long (wrapper para long)
        long numeroGrandePrimitivo = 123456789L;
        Long numeroGrandeWrapper = Long.valueOf(numeroGrandePrimitivo);
        System.out.println("Long: " + numeroGrandeWrapper); // imprime 123456789

        // ✅ Float (wrapper para float)
        float taxaPrimitiva = 3.14f;
        Float taxaWrapper = Float.valueOf(taxaPrimitiva);
        System.out.println("Float: " + taxaWrapper); // imprime 3.14

        // ✅ Short (wrapper para short)
        short pequenoPrimitivo = 100;
        Short pequenoWrapper = Short.valueOf(pequenoPrimitivo);
        System.out.println("Short: " + pequenoWrapper); // imprime 100

        // ✅ Byte (wrapper para byte)
        byte bPrimitivo = 1;
        Byte bWrapper = Byte.valueOf(bPrimitivo);
        System.out.println("Byte: " + bWrapper); // imprime 1

        // 🛠 Autoboxing e Unboxing
        // O Java converte automaticamente entre primitivo e wrapper:
        Integer autoBox = 20; // autoboxing: int -> Integer
        int unBox = autoBox;  // unboxing: Integer -> int
        System.out.println("Autoboxing: " + autoBox + " | Unboxing: " + unBox);

        // 📌 Observação importante:
        // - Wrapper classes (Integer, Double, etc.) → só embrulham valores primitivos, não têm .map.
        // - Optional → é um wrapper especial que pode estar vazio ou conter valor, e por isso tem .map.
        // - Stream → coleção de elementos, também tem .map para transformar cada item.
                /* - Um Stream em Java é uma sequência de elementos (como uma coleção) que você pode processar de forma declarativa.
                   - Ele não guarda os dados por si só, mas fornece operações para manipular os dados de uma coleção (como List, Set, Map).
                   - Foi introduzido no Java 8 junto com lambdas e Optional.
                */
        // ✅ Optional com map
        Optional<Integer> numero = Optional.of(10);
        int dobro = numero.map(n -> n * 2).orElse(0); // transforma o valor se presente
        System.out.println("Dobro (Optional): " + dobro); // imprime 20

        // ✅ Wrapper Integer sem map
        Integer numeroWrapper2 = Integer.valueOf(10);
        int dobro2 = numeroWrapper2 * 2; // acessa direto o valor
        System.out.println("Dobro (Wrapper): " + dobro2); // imprime 20
    }
}