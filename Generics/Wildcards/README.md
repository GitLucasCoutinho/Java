# 🏦 Projeto Generics com Wildcards em Java – Sistema Bancário

Este projeto demonstra o uso de **Generics avançados** em Java, aplicados em um **sistema bancário completo**.  
Aqui você vai aprender sobre **Wildcards (`?`)**, que funcionam como **curingas** para dar flexibilidade ao trabalhar com coleções genéricas.


---

## 🛠️ Funcionalidades
- Criar contas bancárias com titular e saldo inicial
- Armazenar contas em um **repositório genérico**
- Realizar transferências entre contas
- Gerar relatórios com **wildcards**:
    - `List<?>` → aceita qualquer lista (curinga universal)
    - `List<? extends ContaBancaria>` → aceita ContaBancaria e subclasses (curinga de limite superior)
    - `List<? super ContaBancaria>` → aceita ContaBancaria e superclasses (curinga de limite inferior)

---
- Wildcards (?) aplicados em três formas:
- List<?> → aceita qualquer tipo.
- List<? extends ContaBancaria> → aceita ContaBancaria e subclasses.
- List<? super ContaBancaria> → aceita ContaBancaria e superclasses.
- Aplicação prática em um sistema bancário completo com:
- Criação de contas
- Transferências entre contas
- Relatórios genéricos e especializados
- Código cheio de comentários didáticos, acessível até para iniciantes.


---

## ▶️ Execução
Compile e execute a classe `Main.java`:

```bash
javac *.java
java Main
```

---
Este projeto foi criado para ensinar Wildcards de forma clara e acessível:
- List<?> → é como um curinga universal. Aceita qualquer lista, mas você só pode ler os elementos como Object.
- List<? extends ContaBancaria> → é como dizer: “aceito qualquer lista que seja de ContaBancaria ou de algo que herde dela”. Isso permite acessar métodos da classe base (ex: getSaldo).
- List<? super ContaBancaria> → é como dizer: “aceito qualquer lista que seja de ContaBancaria ou de algo acima dela (superclasse)”. Isso permite adicionar objetos ContaBancaria em coleções mais genéricas, como List<Object>.


--- 
- Mostra Generics avançados com wildcards.
- Aplica conceitos em um mini-sistema bancário realista.
- Demonstra boas práticas: separação de responsabilidades, clareza e reutilização de código.
- Código cheio de comentários didáticos, acessível até para iniciantes.



---


.

🎯 O que são Wildcards?
- O ? é chamado de wildcard (curinga).
- Ele significa: “não sei exatamente qual é o tipo, mas sei que ele respeita certas regras”.
- Serve para dar flexibilidade quando trabalhamos com coleções genéricas (List, Set, Map, etc.).

📌 Tipos de Wildcards
1. Unbounded Wildcard (?)
List<?> lista = new ArrayList<String>();


- Significa: “lista de qualquer coisa”.
- Você pode ler elementos como Object, mas não pode adicionar (exceto null).
- Útil quando você só precisa percorrer ou imprimir elementos sem se importar com o tipo.
👉 Analogia: É como uma caixa misteriosa: você pode olhar o que tem dentro, mas não pode colocar nada novo porque não sabe o tipo exato.

2. Upper Bounded Wildcard (? extends Tipo)
List<? extends Number> numeros = new ArrayList<Integer>();


- Significa: “lista de algum tipo que herda de Number”.
- Pode ser Integer, Double, Float, etc.
- Você pode ler elementos como Number, mas não pode adicionar (exceto null).
- Útil quando você quer garantir que os elementos tenham certas capacidades (ex: todos são números e têm doubleValue()).
👉 Analogia: É como dizer: “aceito qualquer fruta que seja uma maçã ou descendente dela”. Você pode comer (usar métodos da classe base), mas não pode plantar novas porque não sabe a variedade exata.

3. Lower Bounded Wildcard (? super Tipo)
List<? super Integer> lista = new ArrayList<Number>();


- Significa: “lista de algum tipo que é superclasse de Integer”.
- Pode ser Integer, Number, ou até Object.
- Você pode adicionar elementos do tipo Integer (ou subclasses), mas ao ler só tem certeza que são Object.
- Útil quando você quer inserir elementos com segurança.
👉 Analogia: É como dizer: “aceito qualquer caixa que possa guardar Integers ou algo mais genérico”. Você pode colocar Integers dentro, mas quando tira, só sabe que é um objeto.

📊 Resumindo em tabela

| ?              |      Qualquer tipo      | Object | só null   
| ? extends Tipo |  Algum subtipo de Tipo  | Tipo   | só null  
| ? super Tipo   | Algum supertipo de Tipo | Object | Tipo  




