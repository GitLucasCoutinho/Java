import java.util.ArrayList;
import java.util.List;

// Esta é a classe genérica principal.
// <T> significa que ela pode armazenar QUALQUER tipo de objeto.
// Exemplo: Repositorio<ContaBancaria>, Repositorio<String>, Repositorio<Integer>, etc.
public class Repositorio<T> {
    // Lista interna que guarda os itens do tipo T
    private List<T> itens = new ArrayList<>();

    // Adiciona um item ao repositório
    public void adicionar(T item) {
        itens.add(item);
    }

    // Remove um item do repositório
    public void remover(T item) {
        itens.remove(item);
    }

    // Retorna todos os itens armazenados
    public List<T> listar() {
        return itens;
    }

    // Busca um item pelo índice (posição na lista)
    public T buscar(int indice) {
        return itens.get(indice);
    }
}