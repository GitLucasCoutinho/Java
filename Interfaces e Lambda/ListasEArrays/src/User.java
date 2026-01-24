import java.util.Objects;

public class User {
    String nome;
    int idade;
    int code;

    public User(String nome, int idade, int code) {
        this.nome = nome;
        this.idade = idade;
        this.code = code;
    }

    public User() {
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    /*
    @Override
    public boolean equals(final Object obj) {
        var isEqual = false;
        if (obj instanceof User user){
            if (this == user) isEqual = true;
            if (this.code == user.code && Objects.equals(this.name, user.name)) isEqual = true;
        }
        return isEqual;
    }
*/

    // igual ao de cima, mas com comentarios explicativos

    // Método equals sobrescrito para comparar objetos da classe User.
    // Ele garante que dois objetos User sejam considerados iguais
    // se tiverem o mesmo código e o mesmo nome, mesmo que sejam instâncias diferentes.

    @Override
    public boolean equals(final Object obj) {          // Sobrescreve o método equals da classe Object.
        var isEqual = false;                           // Inicializa uma variável booleana como false (assumindo que não são iguais).

        if (obj instanceof User user){                 // Verifica se o objeto recebido é realmente um User.

            if (this == user) isEqual = true;          // Se for exatamente o mesmo objeto na memória, define como igual.

            if (this.code == user.code                 // Se o código for igual...
                    && Objects.equals(this.nome, user.nome)) // ...e o nome também for igual (comparação segura contra null).
                isEqual = true;                        // Então os objetos são considerados iguais.
        }

        return isEqual;                                // Retorna o resultado final da comparação.
    }


    @Override
    public String toString() {
        return "User{" +
                "nome='" + nome + '\'' +
                ", idade=" + idade +
                '}';
    }
}
