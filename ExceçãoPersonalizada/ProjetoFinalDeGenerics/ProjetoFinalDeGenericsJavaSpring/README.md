
📘 ProjetoFinalDeGenericsJavaPuro

---
📌 Sobre o projeto

Este projeto é uma implementação Java Puro que simula operações bancárias utilizando conceitos de POO e Generics.
Ele inclui:
- Modelos de Cliente e Conta (Corrente e Poupança).
- Serviços para transferência de valores e relatórios.
- Repositórios simples em memória.
- Testes unitários com JUnit 5 e Mockito.
- Relatórios de cobertura com JaCoCo.
- Empacotamento e execução com Docker.

🛠️ Tecnologias utilizadas
- Java 21
- Maven
- JUnit 5
- Mockito
- JaCoCo
- Docker

🚀 Como rodar o projeto
```bash 
1. Clonar o repositório
   git clone https://github.com/seuusuario/ProjetoFinalDeGenericsJavaPuro.git
   cd ProjetoFinalDeGenericsJavaPuro
```
```bash 
2. Compilar e rodar testes
   mvn clean test
```
```bash 
3. Gerar relatório de cobertura (JaCoCo)
   mvn verify


Abrir no navegador:
target/site/jacoco/index.html
```
---

📊 Cobertura de testes (JaCoCo)
- Relatórios em HTML gerados em target/site/jacoco/index.html.
- Exemplo de resultado:
- Classe Cliente → 100% de cobertura.
- Controller e Services → cobertura crescente conforme novos testes são adicionados.

🐳 Docker
1. Gerar o JAR
   mvn package


2. Criar imagem Docker
   docker build -t banco-generics .


3. Rodar container
   docker run -p 8080:8080 banco-generics


