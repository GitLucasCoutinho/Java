# 💳 Sistema de Conta Bancária - Generics com múltiplos limites.

---
Objetivo Didático
- Múltiplos limites permitem combinar classe base + interfaces.
- No exemplo: <T extends ContaBancaria & Comparable<T>> → o tipo precisa ser uma conta bancária e ser comparável.
- Isso garante que podemos acessar métodos de ContaBancaria (como getSaldo) e usar compareTo para comparar.

- Múltiplos limites (<T extends Classe & Interface>)
- Exemplo com ClienteVIP extends ContaBancaria & Comparable
- Relatório que encontra maior saldo



---
O contexto do Código
- Criamos uma hierarquia de contas bancárias (ContaBancaria, ContaCorrente, ContaPoupanca).
- Implementamos Comparable para permitir comparação entre contas com base no saldo.
  - Desenvolvemos uma classe genérica GerenciadorContas que gerencia contas bancárias e encontra a conta com o maior saldo.     


Exemplo Prático
- Criamos uma classe genérica GerenciadorContas<T extends ContaBancaria & Comparable<T>>.
- Essa classe gerencia uma lista de contas bancárias e pode encontrar a conta com o maior saldo.
- Usamos o método compareTo para comparar saldos entre contas.

- Mostra domínio de Generics avançados.
- Demonstra múltiplos limites em um caso realista (comparar contas bancárias).
- Aplica boas práticas: separação de responsabilidades, uso de interfaces, código limpo.
- Fácil de entender graças aos comentários didáticos.

---

