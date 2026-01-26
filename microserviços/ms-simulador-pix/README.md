# 📌 ms-simulador-pix

![Build](https://img.shields.io/badge/build-passing-brightgreen)
![Tests](https://img.shields.io/badge/tests-100%25-success)
![License](https://img.shields.io/badge/license-MIT-blue)

Microserviço em **Spring Boot** para simulação de autorização de transações **Pix**.  
Este projeto fornece uma API simples e open source para testar integrações e fluxos de pagamento **sem depender dos sistemas oficiais**.

---

## 📖 Sobre o projeto

- **Linguagem:** Java 21
- **Framework:** Spring Boot 3.2.2
- **Banco de dados:** H2 (em memória)

### Funcionalidades principais
- Autorização de transações Pix
- Consulta de transações
- Estorno (refund)
- Validação de chave Pix

---

```bash
ms-simulador-pix/
 ├── Dockerfile                  # Configuração para rodar em container
 ├── pom.xml                     # Dependências Maven
 ├── src/
 │   └── main/
 │       ├── java/com/ocooldev/pix/ms_simulador_pix/
 │       │    ├── MsSimuladorPixApplication.java
 │       │    ├── domain/
 │       │    │    ├── model/
 │       │    │    │    ├── PixTransaction.java
 │       │    │    │    ├── Calendario.java
 │       │    │    │    ├── Valor.java
 │       │    │    │    ├── Horario.java
 │       │    │    │    ├── InfoAdicional.java
 │       │    │    │    ├── PixParcelado.java
 │       │    │    │    ├── Parcela.java
 │       │    │    │    └── Devolucao.java
 │       │    │    └── service/
 │       │    │         ├── PixService.java
 │       │    │         ├── PixParceladoService.java
 │       │    │         ├── DevolucaoService.java
 │       │    │         └── WebhookService.java
 │       │    └── infrastructure/
 │       │         ├── repository/
 │       │         │    ├── PixTransactionRepository.java
 │       │         │    ├── PixParceladoRepository.java
 │       │         │    └── DevolucaoRepository.java
 │       │         └── controller/
 │       │              ├── PixController.java
 │       │              ├── PixParceladoController.java
 │       │              ├── PixDevolucaoController.java
 │       │              └── WebhookController.java
 │       └── resources/
 │            ├── application.yaml   # Configurações Spring Boot
 │            └── logback-spring.xml # Configuração de logs (opcional)
  
```
Entenda a estrutura do projeto
- pom.xml — dependências, plugins (procure spring-boot-maven-plugin e Jib se existir).
- src/main/java — pacotes principais; abra o pacote raiz e identifique:
- Application (classe com @SpringBootApplication) — ponto de entrada.
- controller — endpoints REST; comece por aqui para ver rotas e métodos HTTP.
- service — lógica de negócio; onde a simulação é implementada.
- model / dto — classes de request/response e entidades.
- repository — persistência (in-memory, JPA, ou adaptadores).
- config — configuração de beans, segurança e profiles.
- src/main/resources — application.yml e profiles (dev, prod); veja variáveis configuráveis.
- src/test — testes unitários e de integração; execute para ver cobertura básica.

Ler o código passo a passo
- Ponto de entrada
- Abra a classe Application. Veja como o Spring inicializa e quais profiles são ativados por padrão.
- Controllers
- Localize PixTransactionController (ou nome similar). Para cada método:
- Identifique a rota (@RequestMapping, @GetMapping, @PostMapping).
- Observe os DTOs de entrada e saída.
- Anote validações (@Valid, @NotNull) e códigos de resposta.
- Services
- Abra o serviço que o controller chama. Siga a cadeia de chamadas:
- Validação de negócio
- Geração de transactionId
- Persistência em memória ou banco
- Emissão de eventos (logs, métricas)
- Modelos e DTOs
- Verifique campos obrigatórios, tipos (BigDecimal para valores monetários), e formatos de data (ISO8601).
- Configuração do simulador
- Veja como SIMULATOR_MODE, latencyMs e errorRate são aplicados. Entenda onde a latência é injetada e como falhas são simuladas.
- Idempotência
- Procure por tratamento de Idempotency-Key: onde a chave é lida, armazenada e consultada antes de criar transação.
- Observabilidade
- Verifique application.yml e classes de configuração para Actuator, logs estruturados e métricas.



 Compile e execute com Maven
```bash
mvn clean install
mvn spring-boot:run
```

A aplicação estará disponível em:
👉 http://localhost:8080

 Rodar os testes
```bash
mvn test
```
👉 http://localhost:8080/h2-console

Credenciais:

JDBC URL: jdbc:h2:mem:pixdb

User Name: sa

Password: (em branco)

Após conectar, você poderá visualizar a tabela pix_transaction.

📌 Exemplos de uso (JSON)
🔹 Autorizar transação


```bash
Endpoints Disponíveis:

 POST   /api/pix/transactions                        -- Criar nova transação PIX; suporte a Idempotency-Key.
 GET    /api/pix/transactions                        -- Listar transações com filtros e paginação.
 GET    /api/pix/transactions/{transactionId}        -- Recuperar detalhes e histórico de uma transação.
 PATCH  /api/pix/transactions/{transactionId}/status -- Atualizar/forçar status da transação (parcial).
 PUT    /api/pix/simulator/config                    -- Definir configuração global do simulador (idempotente).
 GET    /actuator/health                             -- Health check do serviço.
 GET    /v3/api-docs                                 -- OpenAPI JSON.
 GET    /swagger-ui.html                             -- Interface Swagger UI.


```

📚 Documentação com Swagger / OpenAPI

    O projeto inclui documentação automática da API usando Swagger UI.
    Após iniciar a aplicação, acesse:
    
    👉 http://localhost:8080/swagger-ui.html


📜 Licença

    Este projeto é open source sob a licença MIT.
Sinta-se livre para usar, modificar e contribuir! 🎉

