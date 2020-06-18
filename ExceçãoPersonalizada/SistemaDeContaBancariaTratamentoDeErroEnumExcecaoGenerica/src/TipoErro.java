// Enum que define os tipos de erro possíveis no sistema bancário
public enum TipoErro {
    SALDO_INSUFICIENTE, // erro quando o saldo não é suficiente para saque ou transferência
    VALOR_INVALIDO,     // erro quando o valor informado é inválido (zero ou negativo)
    CONTA_NAO_ENCONTRADA // erro quando a conta destino não existe
}