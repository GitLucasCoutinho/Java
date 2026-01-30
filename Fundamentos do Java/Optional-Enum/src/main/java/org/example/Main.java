package org.example;

import org.example.domain.User;
import org.example.domain.UserV2;
import org.example.domain.SexEnum;

import java.util.Optional;

public class Main {

    public static void main(String[] args) {

        // ============================================================
        // 0) CRIAÇÃO DE OPTIONALS
        // ============================================================
        // Optional é uma "caixinha" que pode ou não conter um valor.
        // Isso evita o famoso NullPointerException.
        // Aqui temos dois exemplos:
        Optional<User> optionalEmpty = Optional.empty(); // caixinha vazia
        Optional<User> optionalPresent = Optional.of(new User("João", 18, SexEnum.MALE)); // caixinha com valor


        // ============================================================
        // 1) MAP + OR_ELSE_GET
        // ============================================================
        // O método map serve para transformar o valor dentro do Optional.
        // Se houver valor, ele aplica a função (User -> UserV2).
        // Se não houver valor, ele simplesmente retorna Optional vazio.
        //
        // Depois usamos orElseGet, que só cria o valor padrão SE o Optional estiver vazio.
        // Isso é eficiente porque evita criar objetos desnecessários.
        var userV2 = optionalEmpty
                .map(Main::getUserV2) // transforma User em UserV2 (se existir)
                .orElseGet(() -> new UserV2("Default", 0, SexEnum.MALE)); // só chamado se vazio
        System.out.println(userV2); // imprime o UserV2 padrão


        // ============================================================
        // 2) USANDO APENAS MAP
        // ============================================================
        // O map retorna outro Optional.
        // Se a caixinha original estiver vazia, o resultado também será vazio.
        var userV2Optional = optionalEmpty.map(Main::getUserV2);
        System.out.println(userV2Optional); // imprime: Optional.empty


        // ============================================================
        // 3) MAP COM VALOR PRESENTE
        // ============================================================
        // Como optionalPresent tem um User, o map aplica a transformação.
        var userV2Optional2 = optionalPresent.map(Main::getUserV2);
        System.out.println(userV2Optional2); // imprime: Optional[UserV2(...)]


        // ============================================================
        // 4) OR_ELSE
        // ============================================================
        // O método orElse recebe um objeto diretamente.
        // Se o Optional estiver vazio, retorna esse objeto.
        // Se não estiver vazio, retorna o valor presente.
        //
        // DIFERENÇA IMPORTANTE: o objeto passado em orElse() é SEMPRE criado,
        // mesmo que não seja usado. Isso pode gerar gasto desnecessário.
        System.out.println(optionalEmpty.orElse(defaultUser()));


        // ============================================================
        // 5) OR_ELSE_GET
        // ============================================================
        // O método orElseGet recebe um Supplier (função/lambda).
        // Ele só é chamado SE o Optional estiver vazio.
        //
        // Aqui usamos uma referência de método: Main::defaultUser
        // Isso equivale a escrever () -> Main.defaultUser()
        System.out.println(optionalEmpty.orElseGet(Main::defaultUser));


        // ============================================================
        // 6) IF_PRESENT
        // ============================================================
        // Executa uma ação apenas se houver valor dentro do Optional.
        // Se vazio, não faz nada.
        optionalPresent.ifPresent(user -> System.out.println("Usuário presente: " + user));


        // ============================================================
        // 7) OR_ELSE_THROW
        // ============================================================
        // Lança uma exceção se o Optional estiver vazio.
        // Útil quando você quer garantir que o valor exista.
        try {
            User u = optionalEmpty.orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
            System.out.println(u);
        } catch (Exception e) {
            System.out.println("Exceção lançada: " + e.getMessage());
        }
    }

    // ============================================================
    // FUNÇÃO AUXILIAR: CONVERTE USER -> USERV2
    // ============================================================
    // Este método recebe um User e cria um UserV2 com os mesmos dados.
    // É usado dentro do map para transformar o valor do Optional.
    private static UserV2 getUserV2(User user) {
        return new UserV2(user.name(), user.age(), user.sex());
    }

    // ============================================================
    // MÉTODO QUE CRIA UM USUÁRIO PADRÃO
    // ============================================================
    // Este método é usado em orElse e orElseGet.
    // A diferença é:
    // - Em orElse: ele é chamado SEMPRE, mesmo que não seja necessário.
    // - Em orElseGet: ele só é chamado SE o Optional estiver vazio.
    public static User defaultUser() {
        System.out.println("Chamando defaultUser..."); // ajuda a visualizar quando é chamado
        return new User("Maria", 22, SexEnum.FEMALE);
    }
}
