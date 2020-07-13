# 📦 Projeto Generics em Java – Repositório de Contas Bancárias

Este projeto demonstra o uso de **Generics** em Java de forma prática e robusta.  
A ideia é mostrar como criar **classes genéricas**, **métodos genéricos** e **especializações** aplicadas em um mini-sistema de contas bancárias.


---


## 🛠️ Funcionalidades
- Criar contas bancárias com titular e saldo inicial
- Armazenar contas em um **repositório genérico**
- Listar todas as contas armazenadas
- Buscar conta por titular (especialização do repositório)
- Realizar operações de depósito e saque
- Usar métodos genéricos para imprimir listas de qualquer tipo
---


Este projeto foi criado para ensinar Generics de forma clara e acessível:
- Repositorio<T> → funciona como um armário genérico. Você decide o que guardar nele (contas, textos, números).
- RepositorioContas → é um armário especializado que só guarda contas bancárias e sabe procurar pelo nome do titular.
- Util.imprimirLista<T> → é um impressor universal que consegue imprimir listas de qualquer tipo.
- ContaBancaria → é o modelo de dado usado para testar o repositório.



👉 Explicação didática:
- Criamos um repositório especializado em contas.
- Adicionamos contas nele.
- Listamos todas as contas.
- Buscamos uma conta pelo nome.
- Fizemos operações (depósito e saque).
- Listamos novamente para ver o resultado.
- Generics em classes (Repositorio<T>).
- Generics em métodos (Util.imprimirLista<T>).
- Especialização de Generics (RepositorioContas).
- Aplicação prática em um mini-sistema de contas bancárias.
- Comentários didáticos que tornam o código acessível até para iniciantes.

---

