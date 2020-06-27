//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        // Criando um HashMap
        Map<String, Integer> idadePorNome = new HashMap<>();

        // Adicionando elementos
        idadePorNome.put("Lucas", 30);
        idadePorNome.put("Maria", 25);
        idadePorNome.put("João", 40);

        // Acessando um valor pela chave
        System.out.println("Idade do Lucas: " + idadePorNome.get("Lucas")); // 30

        // Verificando se contém chave
        if (idadePorNome.containsKey("Maria")) {
            System.out.println("Maria está no mapa!");
        }

        // Iterando sobre o mapa
        for (Map.Entry<String, Integer> entry : idadePorNome.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        // Removendo um elemento
        idadePorNome.remove("João");

    }
}