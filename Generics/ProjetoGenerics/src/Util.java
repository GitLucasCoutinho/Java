// Classe utilitária com métodos genéricos
public class Util {

    // Método genérico que imprime qualquer array
    // O <T> aqui indica que o método é genérico
    // Ele pode receber um array de qualquer tipo
    public static <T> void imprimirArray(T[] array) {
        // Usamos for-each para percorrer o array
        for (T elemento : array) {
            System.out.println(elemento); // imprime cada elemento
        }
    }
}