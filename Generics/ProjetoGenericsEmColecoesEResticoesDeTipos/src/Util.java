// Classe utilitária com métodos genéricos
public class Util {
    // Método genérico que imprime qualquer lista
    // <T> significa que o método aceita listas de qualquer tipo
    public static <T> void imprimirLista(java.util.List<T> lista) {
        for (T item : lista) {
            System.out.println(item); // imprime cada item da lista
        }
    }
}