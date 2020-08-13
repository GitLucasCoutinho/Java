package org.example;

import com.google.gson.Gson;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws Exception {
        // Chamamos cada exemplo separadamente
        exemploManual();
        exemploGson();
        exemploJackson();
    }

    // -------------------------------
    // Exemplo 1: Parsing manual ("na raça")
    // -------------------------------
    public static void exemploManual() {
        System.out.println("=== Exemplo Manual ===");

        // JSON em formato de String
        String value = "{\"name\":\"João\",\"age\":18}";

        // Criamos um HashMap para guardar os pares chave → valor
        Map<String, String> map = new HashMap<>();

        // -------------------------------
        // PEGANDO O VALOR DA CHAVE "name"
        // -------------------------------

        // Encontramos a posição inicial da chave "name"
        int nameStart = value.indexOf("\"name\"");

        // Procuramos a aspas que abre o valor "João"
        // +6 para pular os caracteres da chave "name"
        // +1 para começar dentro do valor (ignorar a aspas)
        int nameValueStart = value.indexOf("\"", nameStart + 6) + 1;

        // Procuramos a aspas que fecha o valor
        int nameValueEnd = value.indexOf("\"", nameValueStart);

        // Extraímos o valor usando substring
        String nameValue = value.substring(nameValueStart, nameValueEnd);

        // Guardamos no mapa: chave "name" → valor "João"
        map.put("name", nameValue);

        // -------------------------------
        // PEGANDO O VALOR DA CHAVE "age"
        // -------------------------------

        // Encontramos a posição inicial da chave "age"
        int ageStart = value.indexOf("\"age\"");

        // O valor está depois dos dois pontos (:)
        int ageValueStart = value.indexOf(":", ageStart) + 1;

        // O valor termina antes da chave de fechamento "}"
        int ageValueEnd = value.indexOf("}", ageValueStart);

        // Extraímos o valor e removemos espaços extras
        String ageValue = value.substring(ageValueStart, ageValueEnd).trim();

        // Guardamos no mapa: chave "age" → valor "18"
        map.put("age", ageValue);

        // Mostramos o resultado
        System.out.println(map);
        System.out.println();
    }

    // -------------------------------
    // Exemplo 2: Usando Gson
    // -------------------------------
    public static void exemploGson() {
        System.out.println("=== Exemplo com Gson ===");

        // JSON em formato de String
        String json = "{\"name\":\"João\",\"age\":18}";

        // Criamos um objeto Gson
        Gson gson = new Gson();

        // Convertendo JSON diretamente para um Map
        Map<String, Object> map = gson.fromJson(json, Map.class);

        // Mostramos o resultado
        System.out.println(map);

        // Acessamos valores pelo nome da chave
        System.out.println("Nome: " + map.get("name"));
        System.out.println("Idade: " + map.get("age"));
        System.out.println();
    }

    // -------------------------------
    // Exemplo 3: Usando Jackson
    // -------------------------------
    public static void exemploJackson() throws Exception {
        System.out.println("=== Exemplo com Jackson ===");

        // JSON em formato de String
        String json = "{\"name\":\"João\",\"age\":18}";

        // Criamos um ObjectMapper (classe principal do Jackson)
        ObjectMapper mapper = new ObjectMapper();

        // Convertendo JSON diretamente para um Map
        Map<String, Object> map = mapper.readValue(json, Map.class);

        // Mostramos o resultado
        System.out.println(map);

        // Acessamos valores pelo nome da chave
        System.out.println("Nome: " + map.get("name"));
        System.out.println("Idade: " + map.get("age"));
        System.out.println();
    }
}