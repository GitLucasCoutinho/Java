// Classe ContaBancaria
class ContaBancaria { // Declaração da classe ContaBancaria, que representa uma conta de banco

    private String titular; // Atributo privado que guarda o nome do titular da conta
    private double saldo;   // Atributo privado que guarda o saldo atual da conta

    // Construtor da classe: inicializa a conta com titular e saldo inicial
    public ContaBancaria(String titular, double saldoInicial) {
        this.titular = titular;     // 'this.titular' recebe o valor passado como parâmetro
        this.saldo = saldoInicial;  // 'this.saldo' recebe o saldo inicial informado
    }

    // Método de saque
    public void sacar(double valor) throws SaldoInsuficienteException {
        // O método 'sacar' recebe um valor e pode lançar a exceção SaldoInsuficienteException

        if (valor > saldo) { // Verifica se o valor do saque é maior que o saldo disponível
            throw new SaldoInsuficienteException( // Se for maior, lança a exceção personalizada
                    "Saldo insuficiente para saque de R$" + valor + ". Saldo atual: R$" + saldo
            );
        }
        saldo -= valor; // Se o saldo for suficiente, subtrai o valor do saque do saldo
        System.out.println("Saque realizado com sucesso! Novo saldo: R$" + saldo);
        // Exibe mensagem confirmando o saque
    }

    // ✅ Novo: método de depósito
    public void depositar(double valor) {
        // Método 'depositar' adiciona dinheiro à conta

        if (valor <= 0) { // Verifica se o valor é inválido (zero ou negativo)
            System.out.println("❌ Valor de depósito inválido!"); // Mensagem de erro
            return; // Sai do método sem alterar o saldo
        }
        saldo += valor; // Se válido, soma o valor ao saldo
        System.out.println("Depósito de R$" + valor + " realizado! Novo saldo: R$" + saldo);
        // Exibe mensagem confirmando o depósito
    }

    // ✅ Novo: método de transferência entre contas
    public void transferir(ContaBancaria destino, double valor) throws SaldoInsuficienteException {
        // Método 'transferir' envia dinheiro desta conta para outra (destino)

        if (valor > saldo) { // Verifica se há saldo suficiente para transferir
            throw new SaldoInsuficienteException( // Se não houver, lança exceção personalizada
                    "Saldo insuficiente para transferir R$" + valor + ". Saldo atual: R$" + saldo
            );
        }
        this.sacar(valor);        // Usa o método 'sacar' para retirar o valor desta conta
        destino.depositar(valor); // Usa o método 'depositar' para adicionar o valor na conta destino
        System.out.println("Transferência de R$" + valor + " para " + destino.titular + " realizada com sucesso!");
        // Exibe mensagem confirmando a transferência
    }

    public double getSaldo() { // Método getter: retorna o saldo atual da conta
        return saldo;
    }

    public String getTitular() { // Método getter: retorna o nome do titular da conta
        return titular;
    }
}