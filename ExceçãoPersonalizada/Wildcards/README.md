# 🏦 Projeto Generics com Wildcards em Java – Sistema Bancário

Este projeto demonstra o uso de **Generics avançados** em Java, aplicados em um **sistema bancário completo**.  
Aqui você vai aprender sobre **Wildcards (`?`)**, que funcionam como **curingas** para dar flexibilidade ao trabalhar com coleções genéricas.


---

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
