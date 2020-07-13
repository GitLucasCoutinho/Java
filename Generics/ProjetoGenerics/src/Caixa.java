// Classe genérica que guarda um valor de qualquer tipo
// O <T> significa que essa classe pode trabalhar com qualquer tipo (String, Integer, etc.)
public class Caixa<T> {
    // Aqui declaramos um atributo do tipo genérico T
    // Ele não é fixo, quem usa a classe decide qual tipo será
    private T valor;

    // Método para definir o valor dentro da caixa
    // O parâmetro também é do tipo T, ou seja, pode ser String, Integer, etc.
    public void setValor(T valor) {
        this.valor = valor; // guardamos o valor dentro da caixa
    }

    // Método para recuperar o valor guardado
    // O retorno também é do tipo T
    public T getValor() {
        return valor; // devolve o valor armazenado
    }
}