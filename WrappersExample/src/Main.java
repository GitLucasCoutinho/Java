public class Main {
    public static void main(String[] args) {
        // 🔎 Em Java, tipos primitivos (int, double, char, etc.) não são objetos.
        // Para tratá-los como objetos, existem as "Wrapper Classes".
        // Elas "embrulham" (wrap) o valor primitivo dentro de um objeto.

        // ✅ Integer (wrapper para int)
        int numeroPrimitivo = 10; // tipo primitivo
        Integer numeroWrapper = Integer.valueOf(numeroPrimitivo); // embrulha o int em um objeto
        System.out.println("Integer: " + numeroWrapper); // imprime o objeto, mas mostra o valor 10

        // ✅ Double (wrapper para double)
        double precoPrimitivo = 99.99; // tipo primitivo
        Double precoWrapper = Double.valueOf(precoPrimitivo); // embrulha o double
        System.out.println("Double: " + precoWrapper); // imprime 99.99

        // ✅ Character (wrapper para char)
        char letraPrimitiva = 'A'; // tipo primitivo
        Character letraWrapper = Character.valueOf(letraPrimitiva); // embrulha o char
        System.out.println("Character: " + letraWrapper); // imprime A

        // ✅ Boolean (wrapper para boolean)
        boolean ativoPrimitivo = true; // tipo primitivo
        Boolean ativoWrapper = Boolean.valueOf(ativoPrimitivo); // embrulha o boolean
        System.out.println("Boolean: " + ativoWrapper); // imprime true

        // ✅ Long (wrapper para long)
        long numeroGrandePrimitivo = 123456789L; // tipo primitivo
        Long numeroGrandeWrapper = Long.valueOf(numeroGrandePrimitivo); // embrulha o long
        System.out.println("Long: " + numeroGrandeWrapper); // imprime 123456789

        // ✅ Float (wrapper para float)
        float taxaPrimitiva = 3.14f; // tipo primitivo
        Float taxaWrapper = Float.valueOf(taxaPrimitiva); // embrulha o float
        System.out.println("Float: " + taxaWrapper); // imprime 3.14

        // ✅ Short (wrapper para short)
        short pequenoPrimitivo = 100; // tipo primitivo
        Short pequenoWrapper = Short.valueOf(pequenoPrimitivo); // embrulha o short
        System.out.println("Short: " + pequenoWrapper); // imprime 100

        // ✅ Byte (wrapper para byte)
        byte bPrimitivo = 1; // tipo primitivo
        Byte bWrapper = Byte.valueOf(bPrimitivo); // embrulha o byte
        System.out.println("Byte: " + bWrapper); // imprime 1

        // 🛠 Autoboxing e Unboxing
        // O Java faz conversão automática entre primitivo e wrapper:
        Integer autoBox = 20; // autoboxing: int -> Integer (embrulha automaticamente)
        int unBox = autoBox;  // unboxing: Integer -> int (desembrulha automaticamente)
        System.out.println("Autoboxing: " + autoBox + " | Unboxing: " + unBox);


/*
* - Wrapper classes (Integer, Double, etc.) → só embrulham valores primitivos, não têm .map.
- Optional → é um wrapper especial que pode estar vazio ou conter valor, e por isso tem .map.
- Stream → coleção de elementos, também tem .map para transformar cada item.

* */

        // Optional com map
        Optional<Integer> numero = Optional.of(10);
        int dobro = numero.map(n -> n * 2).orElse(0);
        System.out.println("Dobro: " + dobro); // 20

// Wrapper Integer sem map
        Integer numeroWrapper = Integer.valueOf(10);
// Aqui não existe map, você acessa direto o valor
        int dobro2 = numeroWrapper * 2;
        System.out.println("Dobro2: " + dobro2); // 20

    }
}