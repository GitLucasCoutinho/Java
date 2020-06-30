import java.util.ArrayList;
import java.util.List;

// Classe genérica que funciona como um repositório de objetos.
// <T> significa que ela pode armazenar QUALQUER tipo de objeto.
// Exemplo: Repositorio<ContaBancaria>, Repositorio<String>, etc.
public class Repositorio<T> {
    private List<T> itens = new ArrayList<>(); // lista interna que guarda os itens

    // Adiciona um item ao repositório
    public void adicionar(T item) {
        itens.add(item);
    }

    // Retorna todos os itens armazenados
    public List<T> listar() {
        return itens;
    }
}