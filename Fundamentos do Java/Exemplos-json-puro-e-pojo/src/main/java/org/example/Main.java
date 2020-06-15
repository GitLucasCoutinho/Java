package org.example;

import com.google.gson.Gson;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws Exception {
        exemploManual();
        exemploGsonMap();
        exemploJacksonMap();
        //POJO é a sigla para Plain Old Java Object, Objeto Java Simples.
        exemploPOJO();
        //classes simples, independentes, sem dependência de frameworks, os desenvolvedores começaram a chamar essas classes de POJOs.


    }

    // -------------------------------
    // Exemplo 1: Parsing manual ("na raça")
    // -------------------------------
    public static void exemploManual() {
        System.out.println("=== Exemplo Manual ===");

        String value = "{\"name\":\"João\",\"age\":18}";
        Map<String, String> map = new HashMap<>();

        // Pegando valor da chave "name"
        int nameStart = value.indexOf("\"name\"");
        int nameValueStart = value.indexOf("\"", nameStart + 6) + 1;
        int nameValueEnd = value.indexOf("\"", nameValueStart);
        String nameValue = value.substring(nameValueStart, nameValueEnd);
        map.put("name", nameValue);

        // Pegando valor da chave "age"
        int ageStart = value.indexOf("\"age\"");
        int ageValueStart = value.indexOf(":", ageStart) + 1;
        int ageValueEnd = value.indexOf("}", ageValueStart);
        String ageValue = value.substring(ageValueStart, ageValueEnd).trim();
        map.put("age", ageValue);

        System.out.println(map);
        System.out.println();
    }

    // -------------------------------
    // Exemplo 2: Usando Gson com Map
    // -------------------------------
    public static void exemploGsonMap() {
        System.out.println("=== Exemplo Gson com Map ===");

        String json = "{\"name\":\"João\",\"age\":18}";
        Gson gson = new Gson();

        Map<String, Object> map = gson.fromJson(json, Map.class);

        System.out.println(map);
        System.out.println("Nome: " + map.get("name"));
        System.out.println("Idade: " + map.get("age"));
        System.out.println();
    }

    // -------------------------------
    // Exemplo 3: Usando Jackson com Map
    // -------------------------------
    public static void exemploJacksonMap() throws Exception {
        System.out.println("=== Exemplo Jackson com Map ===");

        String json = "{\"name\":\"João\",\"age\":18}";
        ObjectMapper mapper = new ObjectMapper();

        Map<String, Object> map = mapper.readValue(json, Map.class);

        System.out.println(map);
        System.out.println("Nome: " + map.get("name"));
        System.out.println("Idade: " + map.get("age"));
        System.out.println();
    }

    // -------------------------------
    // Exemplo 4: Usando Gson/Jackson com POJO
    // -------------------------------
    public static void exemploPOJO() throws Exception {
        System.out.println("=== Exemplo com POJO (Pessoa) ===");

        String json = "{\"name\":\"João\",\"age\":18}";

        // Usando Gson
        Gson gson = new Gson();
        Pessoa pessoaGson = gson.fromJson(json, Pessoa.class);
        System.out.println("Com Gson: " + pessoaGson);

        // Usando Jackson
        ObjectMapper mapper = new ObjectMapper();
        Pessoa pessoaJackson = mapper.readValue(json, Pessoa.class);
        System.out.println("Com Jackson: " + pessoaJackson);

        System.out.println("Nome (Jackson): " + pessoaJackson.getName());
        System.out.println("Idade (Jackson): " + pessoaJackson.getAge());
        System.out.println();
    }
}