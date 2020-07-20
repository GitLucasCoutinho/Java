package org.example;

/**
 * Este enum representa todos os estados do Brasil.
 *
 * Um enum é um tipo especial de classe em Java que serve para
 * definir um conjunto fixo de constantes. Aqui usamos para listar
 * os estados com seus nomes e siglas oficiais.
 *
 * ✅ Vantagens de usar enum:
 * - Evita erros de digitação (não precisa escrever "SP" como String).
 * - Facilita validações (ex.: verificar se uma sigla é válida).
 * - Permite associar dados extras (nome e sigla).
 */
public enum EstadoBrasil {
    AC("Acre", "AC"),
    AL("Alagoas", "AL"),
    AP("Amapá", "AP"),
    AM("Amazonas", "AM"),
    BA("Bahia", "BA"),
    CE("Ceará", "CE"),
    DF("Distrito Federal", "DF"),
    ES("Espírito Santo", "ES"),
    GO("Goiás", "GO"),
    MA("Maranhão", "MA"),
    MT("Mato Grosso", "MT"),
    MS("Mato Grosso do Sul", "MS"),
    MG("Minas Gerais", "MG"),
    PA("Pará", "PA"),
    PB("Paraíba", "PB"),
    PR("Paraná", "PR"),
    PE("Pernambuco", "PE"),
    PI("Piauí", "PI"),
    RJ("Rio de Janeiro", "RJ"),
    RN("Rio Grande do Norte", "RN"),
    RS("Rio Grande do Sul", "RS"),
    RO("Rondônia", "RO"),
    RR("Roraima", "RR"),
    SC("Santa Catarina", "SC"),
    SP("São Paulo", "SP"),
    SE("Sergipe", "SE"),
    TO("Tocantins", "TO");

    // Cada estado tem dois atributos: nome e sigla
    private final String nome;
    private final String sigla;

    /**
     * Construtor do enum.
     * É chamado automaticamente para cada constante (AC, AL, SP...).
     */
    EstadoBrasil(String nome, String sigla) {
        this.nome = nome;
        this.sigla = sigla;
    }

    // Métodos para acessar os dados de cada estado
    public String getNome() { return nome; }
    public String getSigla() { return sigla; }

    /**
     * Método estático para buscar um estado pela sigla.
     * Exemplo: EstadoBrasil.fromSigla("SP") retorna São Paulo.
     */
    public static EstadoBrasil fromSigla(String sigla) {
        for (EstadoBrasil estado : values()) {
            if (estado.getSigla().equalsIgnoreCase(sigla)) {
                return estado;
            }
        }
        throw new IllegalArgumentException("Sigla inválida: " + sigla);
    }

    @Override
    public String toString() {
        return nome + " (" + sigla + ")";
    }
}
