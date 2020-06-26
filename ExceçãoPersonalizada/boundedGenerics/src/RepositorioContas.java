// Este repositório é especializado em contas bancárias.
// Ele herda de Repositorio<ContaBancaria>, ou seja, só pode guardar objetos ContaBancaria.
public class RepositorioContas extends Repositorio<ContaBancaria> {

    // Método extra: buscar conta pelo nome do titular
    public ContaBancaria buscarPorTitular(String nome) {
        // Percorre todas as contas armazenadas
        for (ContaBancaria conta : listar()) {
            if (conta.getTitular().equalsIgnoreCase(nome)) {
                return conta; // retorna a conta encontrada
            }
        }
        return null; // retorna null se não encontrar nenhuma
    }
}