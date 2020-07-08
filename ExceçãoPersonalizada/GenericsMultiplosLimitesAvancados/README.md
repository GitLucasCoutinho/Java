# 📦 Projeto Generics Avançado em Java – Múltiplos Limites

Este projeto demonstra o uso de **Generics com múltiplos limites** em Java.  
Generics permitem criar classes e métodos que funcionam com **qualquer tipo de dado**, mas às vezes precisamos impor **restrições** para garantir que o tipo tenha certas capacidades.  
Com múltiplos limites, podemos dizer que um tipo genérico precisa ser **uma classe base** **E** implementar **uma ou mais interfaces** ao mesmo tempo.



---

📂 ProjetoGenericsMultiplosLimitesAvancado
├── Main.java              → Classe principal que executa o programa
├── ContaBancaria.java     → Classe base para contas
├── ClienteVIP.java        → Classe que herda ContaBancaria e implementa múltiplas interfaces
└── Relatorio.java         → Classe utilitária que usa múltiplos limites
---

Explicação detalhada
1. <T extends ContaBancaria & Comparable<T> & Serializable>
- extends ContaBancaria → O tipo T precisa ser uma conta bancária.
  Isso garante que podemos usar métodos como getSaldo() e getTitular().
- & Comparable<T> → O tipo T precisa implementar a interface Comparable.
  Isso garante que podemos comparar objetos T usando compareTo().
  Exemplo: comparar clientes VIP pelo saldo.
- & Serializable → O tipo T precisa implementar a interface Serializable.
  Isso garante que o objeto pode ser transformado em bytes (para salvar em arquivo ou enviar pela rede).
  👉 O símbolo & funciona como um “E lógico”.
  É como dizer: “Esse tipo precisa ser ContaBancaria E Comparable E Serializable”.

2. List<T> contas
- O método recebe uma lista de objetos do tipo T.
- Como T tem todos os limites acima, sabemos que cada elemento é uma conta bancária comparável e serializável.

3. if (contas.isEmpty())
- Verifica se a lista está vazia.
- Se não houver contas, imprime uma mensagem e encerra o método.

4. T maior = contas.get(0);
- Assume que a primeira conta é a que tem maior saldo.
- Esse é o ponto de partida para a comparação.

5. for (T conta : contas)
- Percorre todas as contas da lista.
- Como T implementa Comparable, podemos comparar cada conta com a atual "maior".

6. if (conta.compareTo(maior) > 0)
- Usa o método compareTo() para verificar se a conta atual tem saldo maior que a "maior".
- Se sim, atualiza a referência.

7. System.out.println("🏆 Conta com maior saldo: " + maior);
- No final, imprime a conta com maior saldo.

---


📌 Analogia para iniciantes
Imagine que você está contratando alguém para um trabalho.
Você diz: “Eu preciso de alguém que seja motorista E cozinheiro E saiba falar inglês”.
- Se a pessoa só sabe dirigir → não serve.
- Se a pessoa só sabe cozinhar → não serve.
- Se a pessoa sabe dirigir e cozinhar e falar inglês → aí sim ela serve.
  👉 O & em Generics funciona exatamente assim: o tipo precisa cumprir todos os requisitos juntos.

🎯 Objetivo Didático
- Mostrar que Generics podem ter múltiplos limites.
- No exemplo: <T extends ContaBancaria & Comparable<T> & Serializable> → o tipo precisa ser uma conta bancária, comparável e serializável.
- Isso garante que podemos usar métodos de classe base e interfaces ao mesmo tempo.

📌 Por que impressiona no portfólio?
- Mostra domínio de Generics avançados.
- Demonstra múltiplos limites combinados em um caso realista (comparar contas bancárias).
- Aplica boas práticas: separação de responsabilidades, uso de interfaces, código limpo.
- Código cheio de comentários didáticos, acessível até para iniciantes.

🚀 Próximos Passos no Roadmap
- Explorar Generics em interfaces e hierarquias complexas.
- Usar Generics em coleções avançadas (Map<K,V>, Set<T>).
- Evoluir para um sistema bancário completo com relatórios exportados (CSV, PDF).

---

👉 Lucas, esse README está **super detalhado e didático**, pronto para impressionar no portfólio.  
Quer que eu já prepare o **próximo passo do roadmap (Generics em interfaces e hierarquias complexas)** com código e comentários para iniciantes?


