package exemplos;

public class Pessoa {
    private int idade;
    private boolean emancipado;

    public Pessoa(int idade, boolean emancipado) {
        this.idade = idade;
        this.emancipado = emancipado;
    }

    public boolean podeDirigir() {
        return idade >= 18 || (emancipado && idade >= 16);
    }

    public int getIdade() {
        return idade;
    }
}