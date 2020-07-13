// Classe genérica que representa um par (chave, valor)
// Usamos <K, V> para indicar dois tipos genéricos diferentes
public class Par<K, V> {
    private K chave; // tipo da chave
    private V valor; // tipo do valor

    // Construtor recebe chave e valor, ambos genéricos
    public Par(K chave, V valor) {
        this.chave = chave;
        this.valor = valor;
    }

    // Getter para chave
    public K getChave() {
        return chave;
    }

    // Getter para valor
    public V getValor() {
        return valor;
    }
}