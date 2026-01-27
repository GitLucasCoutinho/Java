import java.util.ArrayList;
import java.util.List;

// Classe genérica que funciona como um repositório de objetos
// <T> significa que pode armazenar qualquer tipo
public class Repositorio<T> {
    private List<T> itens = new ArrayList<>();

    // Adiciona um item ao repositório
    public void adicionar(T item) {
        itens.add(item);
    }

    // Remove um item do repositório
    public void remover(T item) {
        itens.remove(item);
    }

    // Retorna todos os itens
    public List<T> listar() {
        return itens;
    }

    // Busca um item pelo índice
    public T buscar(int indice) {
        return itens.get(indice);
    }
}