# 💳 Sistema de Conta Bancária (Classe Única de Exceções Internas)

Projeto didático em Java que demonstra:
- Programação Orientada a Objetos (POO)
- Tratamento de Exceções (`try/catch/finally`)
- Exceções personalizadas agrupadas em uma única classe (`BancoExceptions`)

---


### 🚀 Objetivo Didático
Este projeto foi criado para:
- Demonstrar POO com classes e objetos.
- Mostrar como criar e usar exceções personalizadas.
- Aplicar tratamento de erros com try/catch/finally.
---

- Facilidade de depuração (debug)
  Mensagens detalhadas ajudam o programador a identificar rapidamente onde e por que o erro ocorreu.
  Sem mensagem, você só veria o nome da exceção, o que é pouco informativo.
- Personalização do contexto
  Você pode incluir dados dinâmicos (como saldo atual, valor solicitado, nome da conta) na mensagem.
  Isso torna o erro mais útil e contextualizado.
- Boas práticas de software
  Em sistemas reais, mensagens de exceção são usadas para logar erros e informar o cliente ou usuário final.
  Isso melhora a experiência e facilita manutenção.
- Didática e aprendizado
  Para quem está estudando, mensagens ajudam a visualizar o fluxo de execução e entender como o tratamento de exceções funciona.


---

## 🛠️ Funcionalidades
- Criar contas bancárias com titular e saldo inicial
- Realizar saques (com validação de saldo e valor)
- Fazer depósitos (com validação de valor)
- Transferir valores entre contas
- Tratar erros com exceções personalizadas agrupadas

---

## ▶️ Execução
Compile e execute a classe `Main.java`:

```bash
javac *.java
java Main
```

---

👉 Esse README segue o mesmo padrão da versão anterior, mas adaptado para a abordagem de **classe única com internas**.  

Quer que eu monte também o README da **versão Enum + Exceção Genérica**, para fechar o trio de projetos com documentação completa?