# 📄 Projeto Exportação de Relatórios com Generics em Java

Este projeto demonstra como usar **Generics avançados** em conjunto com **coleções** (`Map`, `Set`, `Collection`) para criar um **sistema bancário** capaz de **exportar relatórios** em formato CSV.  
O foco está em mostrar como os **wildcards (`?`)** tornam os métodos flexíveis e seguros.


---


📂 ProjetoExportacaoRelatorios
├── Main.java              → Classe principal que executa o programa
├── Conta.java             → Interface genérica para contas
├── ContaBancaria.java     → Classe base que implementa Conta
├── ContaCorrente.java     → Subclasse de ContaBancaria
├── ContaPoupanca.java     → Outra subclasse de ContaBancaria
├── Banco.java             → Classe que gerencia contas usando Map e Set
└── ExportadorRelatorio.java → Classe utilitária que exporta relatórios
---

---

## 🔧 Funcionalidades
- Criar contas bancárias (corrente e poupança).
- Gerenciar contas usando `Map<K,V>` (CPF → Conta).
- Garantir unicidade de CPFs com `Set<T>`.
- Exportar relatórios em CSV:
- Lista de contas com titular e saldo.
- Saldo total do banco.

---

## 📄 Uso dos Wildcards (`?`)
### 1. `Collection<? extends Conta<?>>`
- Usado em `exportarContasParaCSV`.
- Significa: *“aceito qualquer coleção de contas ou subclasses de Conta”*.
- Permite imprimir contas sem se preocupar se são `ContaCorrente` ou `ContaPoupanca`.

### 2. `Collection<? extends Conta<? extends Number>>`
- Usado em `exportarSaldoTotalParaCSV`.
- Significa: *“aceito qualquer coleção de contas cujo saldo seja algum subtipo de Number”*.
- Garante que podemos chamar `doubleValue()` em qualquer saldo (Integer, Double, BigDecimal).

👉 O `?` é o **curinga** que dá flexibilidade:
- `?` → qualquer tipo.
- `? extends Tipo` → algum subtipo de `Tipo`.
- `? super Tipo` → algum supertipo de `Tipo`.

---

## ▶️ Execução
Compile e execute a classe `Main.java`:

```bash
javac *.java
java Main


```
---
🎯 Objetivo Didático
- Mostrar como Generics funcionam junto com coleções avançadas e exportação de dados.
- Demonstrar uso de wildcards (?) para aceitar diferentes tipos de contas.
- Aplicar boas práticas: separação de responsabilidades (Banco gerencia contas, Exportador gera relatórios).
- Usar CSV como formato simples e universal para relatórios.


- Demonstra boas práticas de arquitetura: classes utilitárias, responsabilidades bem definidas.
- Aplica conceitos em um mini-sistema bancário realista.
- Código cheio de comentários didáticos, acessível até para iniciantes.

🚀 Próximos Passos no Roadmap
- Evoluir para exportação em PDF usando bibliotecas externas (ex: iText).
- Criar relatórios mais complexos (transações, histórico).
- Integrar com banco de dados para persistência real.
