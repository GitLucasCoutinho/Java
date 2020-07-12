import java.util.*;

// Classe que gerencia contas usando coleções avançadas
public class Banco {
    // Usamos Map para associar CPF (String) à ContaBancaria
    private Map<String, ContaBancaria> contas = new HashMap<>();

    // Usamos Set para guardar CPFs únicos
    private Set<String> cpfs = new HashSet<>();

    // Adiciona uma conta ao banco
    public void adicionarConta(String cpf, ContaBancaria conta) {
        contas.put(cpf, conta);
        cpfs.add(cpf);
    }

    // Busca conta pelo CPF
    public ContaBancaria buscarConta(String cpf) {
        return contas.get(cpf);
    }

    // Lista todas as contas
    public Collection<ContaBancaria> listarContas() {
        return contas.values();
    }

    // Lista todos os CPFs cadastrados
    public Set<String> listarCpfs() {
        return cpfs;
    }
}