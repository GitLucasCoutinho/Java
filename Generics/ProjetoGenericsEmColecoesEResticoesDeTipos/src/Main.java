// Classe principal para testar o projeto
public class Main {
    public static void main(String[] args) {
        // Criando o repositório de contas
        RepositorioContas repositorio = new RepositorioContas();

        // Criando algumas contas bancárias
        ContaBancaria conta1 = new ContaBancaria("Lucas", 500.0);
        ContaBancaria conta2 = new ContaBancaria("Maria", 1000.0);
        ContaBancaria conta3 = new ContaBancaria("João", 300.0);

        // Adicionando as contas ao repositório
        repositorio.adicionar(conta1);
        repositorio.adicionar(conta2);
        repositorio.adicionar(conta3);

        // Listando todas as contas
        System.out.println("📋 Lista de contas:");
        Util.imprimirLista(repositorio.listar());

        // Buscando uma conta pelo nome do titular
        ContaBancaria contaBuscada = repositorio.buscarPorTitular("Maria");
        System.out.println("\n🔎 Conta encontrada: " + contaBuscada);

        // Fazendo operações na conta encontrada
        contaBuscada.depositar(200.0); // adiciona dinheiro
        contaBuscada.sacar(150.0);     // retira dinheiro

        // Mostrando novamente todas as contas após as operações
        System.out.println("\n💰 Após operações:");
        Util.imprimirLista(repositorio.listar());
    }
}