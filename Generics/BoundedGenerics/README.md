# 💳 Sistema de Conta Bancária (POO + Exceções)

Este projeto demonstra o uso de **Programação Orientada a Objetos (POO)** em Java, aliado ao **Tratamento de Exceções** (`try/catch/finally`) e a criação de uma **exceção personalizada**.  
O objetivo é simular operações básicas de uma conta bancária, como saque, e tratar erros de saldo insuficiente.

---

🎯 O que são "bounded generics"?
- São limites que você coloca nos parâmetros de tipo.
- Servem para dizer: “Esse tipo genérico só pode ser usado se for um subtipo de X”.
- Isso permite usar métodos e propriedades específicas de uma classe base ou interface.



---

## 🛠️ Funcionalidades
- Criar contas bancárias com titular e saldo inicial
- Armazenar contas em um **repositório genérico**
- Listar todas as contas armazenadas
- Buscar conta por titular (especialização do repositório)
- Realizar operações de depósito e saque
- Usar **bounded generics** para criar uma caixa que só aceita números (`Integer`, `Double`, etc.)

---

## ▶️ Execução
Compile e execute a classe `Main.java`:

```bash
javac *.java
java Main
```
---

🎯 Objetivo Didático
Este projeto foi criado para ensinar Generics de forma clara e acessível:
- Repositorio<T> → funciona como um armário genérico. Você decide o que guardar nele (contas, textos, números).
- RepositorioContas → é um armário especializado que só guarda contas bancárias e sabe procurar pelo nome do titular.
- NumeroCaixa<T extends Number> → é uma caixa que só aceita números. Se você tentar guardar uma String, o compilador não deixa.
- Main → demonstra tudo isso em um programa simples e didático.


- Mostra Generics em classes (Repositorio<T>).
- Mostra Generics em métodos (quando usamos listas e loops genéricos).
- Mostra especialização de Generics (RepositorioContas).
- Mostra restrição de tipo (bounded generics) (NumeroCaixa<T extends Number>).
- Aplica tudo em um mini-sistema realista (contas bancárias).
- Código cheio de comentários didáticos, acessível até para iniciantes.
