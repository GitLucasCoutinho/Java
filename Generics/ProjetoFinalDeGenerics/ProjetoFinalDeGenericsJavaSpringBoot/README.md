📘 ProjetoFinalDeGenericsJavaPuro

---

📌 Sobre o projeto

Este projeto é uma implementação em **Spring Boot** que simula operações bancárias utilizando conceitos de POO e Generics.  
Ele inclui:
- Modelos de Cliente e Conta (Corrente e Poupança).
- Serviços para transferência de valores e relatórios.
- Controller REST para expor endpoints da API.
- Documentação interativa com Swagger/OpenAPI.
- Testes unitários com JUnit 5 e Mockito.
- Relatórios de cobertura com JaCoCo.
- Empacotamento e execução com Docker.

🛠️ Tecnologias utilizadas
- Java 21
- Spring Boot 3.2.2
- Maven
- JUnit 5
- Mockito
- JaCoCo
- Swagger/OpenAPI (Springdoc)
- Docker

🚀 Como rodar o projeto
```bash
1. Clonar o repositório
   git clone https://github.com/seuusuario/ProjetoFinalDeGenericsJavaPuro.git
   cd ProjetoFinalDeGenericsJavaPuro

2. Build do projeto
   mvn clean install 

3. Rodar a aplicação
   mvn spring-boot:run
    # Ou usando Docker
    docker build -t projeto-generics-java .        
    docker run -p 8080:8080 projeto-generics-java
  
5. Acessar Swagger UI
   http://localhost:8080/swagger-ui.html

    
    
    
```


Cobertura de testes (JaCoCo)
- Relatórios em HTML gerados em target/site/jacoco/index.html.
- Exemplo de resultado:
- Classe Cliente → 100% de cobertura.
- Controller e Services → cobertura crescente conforme novos testes são adicionados.


---




MIT License

Copyright (c) 2026 Lucas

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.

