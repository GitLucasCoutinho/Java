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
        for (ContaBancaria conta : repositorio.listar()) {
            System.out.println(conta);
        }

        // Buscando uma conta pelo nome do titular
        ContaBancaria contaBuscada = repositorio.buscarPorTitular("Maria");
        System.out.println("\n🔎 Conta encontrada: " + contaBuscada);

        // Fazendo operações na conta encontrada
        contaBuscada.depositar(200.0); // adiciona dinheiro
        contaBuscada.sacar(150.0);     // retira dinheiro

        // Mostrando novamente todas as contas após as operações
        System.out.println("\n💰 Após operações:");
        for (ContaBancaria conta : repositorio.listar()) {
            System.out.println(conta);
        }

        // Testando a classe NumeroCaixa
        NumeroCaixa<Integer> caixaInt = new NumeroCaixa<>(42);
        NumeroCaixa<Double> caixaDouble = new NumeroCaixa<>(3.14);

        System.out.println("\n📦 NumeroCaixa:");
        System.out.println("Caixa Int: " + caixaInt.getValorComoDouble());
        System.out.println("Caixa Double: " + caixaDouble.getValorComoDouble());

        // ❌ Erro de compilação: String não é Number
        // NumeroCaixa<String> caixaTexto = new NumeroCaixa<>("Lucas");
    }
}