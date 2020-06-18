# 💳 Sistema de Conta Bancária (Enum + Exceção Genérica)

Projeto didático em Java que demonstra:
- Programação Orientada a Objetos (POO)
- Tratamento de Exceções (`try/catch/finally`)
- Uso de `enum` para categorizar erros
- Exceção genérica (`BancoException`) que recebe o tipo de erro e mensagem

---
Resumo didático
- Enum TipoErro → define os tipos de erro possíveis.
- Exceção genérica BancoException → recebe um tipo de erro e uma mensagem.
- ContaBancaria → lança sempre BancoException, mas com diferentes tipos (SALDO_INSUFICIENTE, VALOR_INVALIDO, CONTA_NAO_ENCONTRADA).
- Main → trata todas as exceções em um único catch, diferenciando pelo tipo de erro.



---

## 🛠️ Funcionalidades
- Criar contas bancárias com titular e saldo inicial
- Realizar saques (com validação de saldo e valor)
- Fazer depósitos (com validação de valor)
- Transferir valores entre contas
- Tratar erros com uma exceção genérica que diferencia o tipo de erro via `enum`

---

🎯 Objetivo
Exercício simples para praticar:
- Encapsulamento e métodos em POO
- Criação e uso de exceções personalizadas
- Organização de erros com enum e exceção genérica


---
## ▶️ Execução
Compile e execute a classe `Main.java`:

```bash
javac *.java
java Main

```
---

🚀 Objetivo Didático
Este projeto foi criado para:
- Demonstrar POO com classes e objetos.
- Mostrar como criar e usar exceções personalizadas.
- Aplicar tratamento de erros com try/catch/finally.

