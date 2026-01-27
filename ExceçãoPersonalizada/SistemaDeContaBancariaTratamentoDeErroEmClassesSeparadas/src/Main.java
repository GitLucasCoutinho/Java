// Classe principal para testar o sistema
public class Main {
    public static void main(String[] args) {
        // Criação de duas contas
        ContaBancaria conta1 = new ContaBancaria("Lucas", 500.0);
        ContaBancaria conta2 = new ContaBancaria("Maria", 300.0);

        try {
            // Exibe saldos iniciais
            System.out.println("Saldo inicial Lucas: R$" + conta1.getSaldo());
            System.out.println("Saldo inicial Maria: R$" + conta2.getSaldo());

            // Operações
            conta1.sacar(200.0); // saque válido
            conta1.depositar(150.0); // depósito válido
            conta1.transferir(conta2, 300.0); // transferência válida
            conta1.sacar(600.0); // saque inválido → lança exceção

        } catch (SaldoInsuficienteException e) {
            System.out.println("❌ Erro: " + e.getMessage());
        } catch (ValorInvalidoException e) {
            System.out.println("❌ Erro: " + e.getMessage());
        } catch (ContaNaoEncontradaException e) {
            System.out.println("❌ Erro: " + e.getMessage());
        } finally {
            // Sempre executa, independente de erro
            System.out.println("Operação finalizada.");
            System.out.println("Saldo final Lucas: R$" + conta1.getSaldo());
            System.out.println("Saldo final Maria: R$" + conta2.getSaldo());
        }
    }
}