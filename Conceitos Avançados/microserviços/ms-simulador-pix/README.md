# 🏦 ms-simulador-pix

> **Simulador completo de transações PIX em Java/Spring Boot**
> Um projeto open source para testar integrações de pagamento PIX sem depender dos sistemas oficiais

[![Build Status](https://img.shields.io/badge/build-passing-brightgreen?style=flat-square)](https://github.com/seu-usuario/ms-simulador-pix)
[![Tests](https://img.shields.io/badge/tests-100%25-success?style=flat-square)](./src/test)
[![Java](https://img.shields.io/badge/Java-21-orange?style=flat-square&logo=java)](https://www.java.com)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.2-brightgreen?style=flat-square&logo=springboot)](https://spring.io/projects/spring-boot)
[![License](https://img.shields.io/badge/license-MIT-blue?style=flat-square)](./LICENSE)
[![Status](https://img.shields.io/badge/status-Estável-brightgreen?style=flat-square)](.)

---

## 📋 Índice

- [🎯 Sobre](#-sobre)
- [✨ Funcionalidades](#-funcionalidades)
- [🚀 Quick Start](#-quick-start)
- [📊 Arquitetura](#-arquitetura)
- [📂 Estrutura do Projeto](#-estrutura-do-projeto)
- [🔌 Endpoints da API](#-endpoints-da-api)
- [⚙️ Configuração](#️-configuração)
- [🐳 Docker & Compose](#-docker--compose)
- [📖 Documentação](#-documentação)
- [🧪 Testes](#-testes)
- [🛠️ Tecnologias](#️-tecnologias)
- [📚 Referências](#-referências)
- [📜 Licença](#-licença)

---

## 🎯 Sobre

**ms-simulador-pix** é um microserviço **completo e funcional** que simula o sistema de pagamentos **PIX** brasileiro. Perfeito para:

✅ Testar integrações de pagamento em desenvolvimento  
✅ Aprender sobre arquitetura de microserviços  
✅ Prototipar fluxos de pagamento  
✅ Validar implementações de cliente  
✅ Estudar Spring Boot, RabbitMQ, Redis e observabilidade  

**Desenvolvido 100% em Java, sem dependência de APIs externas!**

### 📊 Especificações Técnicas

| Aspecto | Detalhe |
|---------|---------|
| **Linguagem** | Java 21 |
| **Framework** | Spring Boot 3.2.2 |
| **Build** | Maven 3.8+ |
| **Banco de Dados** | H2 (em memória) |
| **Message Broker** | RabbitMQ (opcional) |
| **Cache** | Redis (opcional) |
| **Observabilidade** | Prometheus, Grafana, Jaeger |

---

## ✨ Funcionalidades

### 💳 Transações PIX

- ✅ Criar transações com idempotência (`Idempotency-Key`)
- ✅ Listar e consultar transações
- ✅ Gerar QR Code (dinâmico e estático)
- ✅ Confirmar/liquidar transações
- ✅ Estorno (devolução/refund)
- ✅ Calendário de expiração

### 📦 Cobranças Parceladas

- ✅ Criar cobrança parcelada
- ✅ Controlar status de parcelas
- ✅ Devolução de parcelas

### 🔄 Devoluções

- ✅ Solicitar devolução
- ✅ Rastrear status da devolução
- ✅ Validação de prazos

### 🔐 Segurança & Validação

- ✅ Validação de chaves PIX (email, CPF, CNPJ, telefone)
- ✅ Circuit Breaker (Resilience4j)
- ✅ Tratamento de erros global
- ✅ Segurança básica (desabilitável para dev)

### 📡 Eventos & Mensageria

- ✅ Publicação de eventos via RabbitMQ
- ✅ Arquitetura event-driven
- ✅ Integração com múltiplos serviços

### 📊 Observabilidade

- ✅ Métricas (Prometheus)
- ✅ Dashboards (Grafana)
- ✅ Tracing distribuído (Jaeger)
- ✅ Health checks
- ✅ Logs estruturados

---

## 🚀 Quick Start

### Pré-requisitos

```bash
# Verificar Java
java -version  # Java 21+

# Verificar Maven
mvn -v         # Maven 3.8+

# Opcional: Docker
docker --version
```

### Modo 1️⃣: Maven (Recomendado para Desenvolvimento)

```bash
# Clone o repositório
git clone https://github.com/seu-usuario/ms-simulador-pix.git
cd ms-simulador-pix

# Compilar
mvn clean install

# Executar
mvn spring-boot:run
```

### Modo 2️⃣: JAR Direto

```bash
# Compilar
mvn clean package -DskipTests

# Executar
java -jar target/ms-simulador-pix-1.0.0.jar
```

### Modo 3️⃣: PowerShell Script (Windows)

```powershell
.\RUN.ps1
```

### ✅ Verificar se está funcionando

```bash
# Health check
curl -s http://localhost:8080/actuator/health | jq

# Swagger UI
open http://localhost:8080/swagger-ui.html
```

**Esperado:** A aplicação estará em **http://localhost:8080** ✅

---

## 📊 Arquitetura

### Visão Geral

```
┌─────────────────────────────────────────────────────────────┐
│                    Camada de Apresentação                    │
│  ┌────────────────────────────────────────────────────────┐ │
│  │           REST Controllers (Spring Web)                 │ │
│  │  - PixController                                        │ │
│  │  - PixParceladoController                              │ │
│  │  - PixDevolucaoController                              │ │
│  └────────────────────────────────────────────────────────┘ │
└────────────────────┬─────────────────────────────────────────┘
                     │
┌────────────────────▼─────────────────────────────────────────┐
│                 Camada de Aplicação                          │
│  ┌────────────────────────────────────────────────────────┐ │
│  │        Business Logic Services (Spring Service)         │ │
│  │  - PixService                                           │ │
│  │  - PixParceladoService                                 │ │
│  │  - DevolucaoService                                    │ │
│  │  - QRCodeService                                       │ │
│  └────────────────────────────────────────────────────────┘ │
└────────────────────┬─────────────────────────────────────────┘
                     │
┌────────────────────▼─────────────────────────────────────────┐
│                  Camada de Domínio                           │
│  ┌────────────────────────────────────────────────────────┐ │
│  │            Models & Entities (JPA)                      │ │
│  │  - PixTransaction                                       │ │
│  │  - PixParcelado                                         │ │
│  │  - Devolucao                                            │ │
│  │  - Enums: StatusTransacao, StatusParcela, etc          │ │
│  └────────────────────────────────────────────────────────┘ │
└────────────────────┬─────────────────────────────────────────┘
                     │
┌────────────────────▼─────────────────────────────────────────┐
│               Camada de Infraestrutura                       │
│  ┌──────────────┬──────────────┬──────────────┐             │
│  │ Repository   │   Events &   │ Configuration│             │
│  │ (Spring Data)│  Messaging   │ & Security   │             │
│  └──────────────┴──────────────┴──────────────┘             │
│                     │                                        │
│  ┌──────────────┬──────────────┬──────────────┐             │
│  │     H2       │   RabbitMQ   │    Redis     │             │
│  │  Database    │  Message Bus │    Cache     │             │
│  └──────────────┴──────────────┴──────────────┘             │
└─────────────────────────────────────────────────────────────┘
```

### Fluxo de Requisição

```
Cliente HTTP
    │
    ▼
┌─────────────────────┐
│  Security Filter    │ ◄─── Validação de segurança
└────────┬────────────┘
         │
         ▼
┌─────────────────────┐
│    Controller       │ ◄─── Recebe requisição
└────────┬────────────┘      Valida input
         │
         ▼
┌─────────────────────┐
│    Service Layer    │ ◄─── Lógica de negócio
└────────┬────────────┘      Validações
         │
         ▼
┌─────────────────────┐
│    Repository       │ ◄─── Acesso ao banco
└────────┬────────────┘      (H2/JPA)
         │
         ▼
┌─────────────────────┐
│    Event Publisher  │ ◄─── Publicar eventos
└────────┬────────────┘      (RabbitMQ)
         │
         ▼
┌─────────────────────┐
│   Response DTO      │ ◄─── Serializar resposta
└────────┬────────────┘      (JSON)
         │
         ▼
     Cliente HTTP ✅
```

---

## 📂 Estrutura do Projeto

```
ms-simulador-pix/
│
├── 📄 pom.xml                          # Configuração Maven & Dependências
├── 📄 docker-compose.yml               # Orquestração Docker
├── 📄 prometheus.yml                   # Configuração Prometheus
│
├── 📁 src/main/java/com/ocooldev/pix/
│   │
│   ├── 📦 ms_simulador_pix/            # Simulador Principal
│   │   ├── MsSimuladorPixApplication.java      # ⭐ Ponto de entrada
│   │   │
│   │   ├── 📂 domain/                  # Camada de Domínio
│   │   │   ├── 📂 model/               # Entidades JPA
│   │   │   │   ├── PixTransaction.java         # Transação PIX
│   │   │   │   ├── PixParcelado.java          # Cobrança Parcelada
│   │   │   │   ├── Devolucao.java             # Devolução/Refund
│   │   │   │   ├── Parcela.java               # Parcela de cobrança
│   │   │   │   ├── Calendario.java            # Data/expiração
│   │   │   │   ├── Horario.java               # Horário da transação
│   │   │   │   ├── Valor.java                 # Valor em BigDecimal
│   │   │   │   ├── InfoAdicional.java         # Info customizável
│   │   │   │   ├── StatusTransacao.java       # ATIVA, CONCLUIDA, etc
│   │   │   │   ├── StatusParcela.java         # PENDENTE, PAGA, etc
│   │   │   │   └── StatusDevolucao.java       # SOLICITADA, EFETIVADA
│   │   │   │
│   │   │   └── 📂 service/              # Lógica de Negócio
│   │   │       ├── PixService.java             # Criar, listar, estornar
│   │   │       ├── PixParceladoService.java    # Cobranças parceladas
│   │   │       ├── DevolucaoService.java       # Gerenciar devoluções
│   │   │       ├── WebhookService.java         # Webhooks simulados
│   │   │       ├── IdempotencyService.java     # Idempotência
│   │   │       └── QRCodeService.java          # Geração de QR Code
│   │   │
│   │   └── 📂 infrastructure/           # Camada de Infraestrutura
│   │       ├── 📂 config/               # Configurações
│   │       │   ├── SecurityConfig.java         # Spring Security
│   │       │   ├── RabbitMQConfig.java         # RabbitMQ
│   │       │   ├── CacheConfig.java            # Redis Cache
│   │       │   └── GatewayConfig.java          # Gateway (futuro)
│   │       │
│   │       ├── 📂 controller/           # Endpoints REST
│   │       │   ├── PixController.java          # POST/GET /pix/...
│   │       │   ├── PixParceladoController.java # POST/GET /parcelado/...
│   │       │   ├── PixDevolucaoController.java # POST/GET /devolucao/...
│   │       │   └── WebhookController.java      # POST /webhook/...
│   │       │
│   │       ├── 📂 dto/                  # Data Transfer Objects
│   │       │   ├── PixTransactionRequest.java
│   │       │   ├── PixTransactionResponse.java
│   │       │   ├── PixParceladoRequest.java
│   │       │   ├── DevolucaoRequest.java
│   │       │   └── ErrorResponse.java
│   │       │
│   │       ├── 📂 event/                # Eventos de Domínio
│   │       │   ├── PixTransactionEvent.java
│   │       │   ├── PixEventPublisher.java      # Publicador
│   │       │   └── PixEventListener.java       # Consumidor
│   │       │
│   │       ├── 📂 repository/           # Data Access (Spring Data JPA)
│   │       │   ├── PixTransactionRepository.java
│   │       │   ├── PixParceladoRepository.java
│   │       │   └── DevolucaoRepository.java
│   │       │
│   │       ├── 📂 messaging/            # Mensageria
│   │       │   ├── PixEventPublisher.java      # Publica eventos
│   │       │   └── RabbitMQConfig.java         # Configuração
│   │       │
│   │       └── 📂 exception/             # Tratamento de Erros
│   │           ├── GlobalExceptionHandler.java
│   │           ├── PixException.java
│   │           └── ValidationException.java
│   │
│   └── 📦 gateway/ (Futuro: API Gateway)
│       └── config/GatewayConfig.java
│
├── src/main/resources/
│   ├── application.yaml                # ⚙️ Configurações Spring
│   ├── application-dev.yaml            # Perfil de desenvolvimento
│   ├── application-prod.yaml           # Perfil de produção
│   └── logback-spring.xml              # Configuração de logs
│
├── src/test/java/
│   └── com/ocooldev/pix/ms_simulador_pix/
│       ├── domain/service/
│       │   └── PixServiceTest.java     # Testes unitários
│       └── infrastructure/controller/
│           └── PixControllerTest.java  # Testes de integração
│
├── 📚 DOCUMENTAÇÃO
│   ├── README.md                        # Este arquivo ⭐
│   ├── STARTUP-GUIDE.md                 # Como iniciar
│   ├── MICROSERVICES.md                 # Arquitetura de microserviços
│   ├── IMPLEMENTATION-GUIDE.md          # Guia de implementação
│   ├── HELP.md                          # Problemas comuns
│   └── CHANGELOG.md                     # Histórico de mudanças
│
└── 🐳 DEPLOYMENT
    ├── Dockerfile                       # Build Docker
    ├── docker-compose.yml               # Stack completo
    └── .github/workflows/ci-cd.yml      # CI/CD (futuro)
```

---

## 🔌 Endpoints da API

### 📌 Base URL
```
http://localhost:8080
```

### 💳 Transações PIX

#### Criar Transação
```bash
POST /pix/authorize
Content-Type: application/json
Idempotency-Key: unique-key-123

{
  "chave": "usuario@email.com",
  "valor": "100.50",
  "solicitacaoPagador": "Pagamento de teste"
}

# Response
{
  "txid": "a1b2c3d4e5f6g7h8i9j0k1l2m3n4o5p6",
  "chave": "usuario@email.com",
  "valor": { "original": 100.50 },
  "status": "ATIVA",
  "calendario": { "criacao": "2026-04-02T13:36:00Z", "expiracao": 3600 }
}
```

#### Listar Transações
```bash
GET /pix/transactions

# Response
[
  { "txid": "...", "chave": "...", "valor": {...}, "status": "ATIVA" },
  { "txid": "...", "chave": "...", "valor": {...}, "status": "CONCLUIDA" }
]
```

#### Obter Detalhes
```bash
GET /pix/transaction/{txid}
```

#### Gerar QR Code
```bash
GET /pix/transaction/{txid}/qrcode

# Response: PNG binary
[PNG Image Data]
```

#### Confirmar/Liquidar
```bash
POST /pix/refund/{txid}

# Response: Transaction com status CONCLUIDA
```

### 📦 Cobranças Parceladas

```bash
# Criar cobrança parcelada
POST /parcelado/criar
{
  "chave": "usuario@email.com",
  "valor": "1000.00",
  "numParcelas": 12
}

# Listar
GET /parcelado/listar

# Detalhes
GET /parcelado/{txid}
```

### 🔄 Devoluções

```bash
# Solicitar devolução
POST /devolucao/solicitar/{txid}
{
  "valor": "50.00",
  "motivo": "Produto devolvido"
}

# Listar devoluções
GET /devolucao/listar

# Status da devolução
GET /devolucao/{devolucaoId}
```

### 📊 Observabilidade

```bash
# Health Check
GET /actuator/health

# Métricas
GET /actuator/metrics

# Prometheus Metrics
GET /actuator/prometheus

# Swagger UI
GET /swagger-ui.html

# OpenAPI JSON
GET /v3/api-docs

# H2 Console (dev)
GET /h2-console
```

---

## ⚙️ Configuração

### `application.yaml` - Principais Configurações

```yaml
spring:
  application:
    name: ms-simulador-pix

  # Banco de Dados
  datasource:
    url: jdbc:h2:mem:pixdb
    driverClassName: org.h2.Driver
  h2:
    console:
      enabled: true
      path: /h2-console

  # JPA/Hibernate
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: false

  # RabbitMQ (opcional)
  rabbitmq:
    host: localhost
    port: 5672
    username: guest
    password: guest

  # Redis (opcional)
  redis:
    host: localhost
    port: 6379

server:
  port: 8080

logging:
  level:
    root: INFO
    com.ocooldev.pix: DEBUG
    org.springframework.web: DEBUG

management:
  endpoints:
    web:
      exposure:
        include: health,metrics,prometheus
  endpoint:
    health:
      show-details: always
```

### Variáveis de Ambiente

```bash
# Banco de Dados
DB_HOST=localhost
DB_PORT=1521
DB_NAME=pixdb

# RabbitMQ
RABBITMQ_HOST=rabbitmq
RABBITMQ_PORT=5672
RABBITMQ_USER=guest
RABBITMQ_PASS=guest

# Redis
REDIS_HOST=redis
REDIS_PORT=6379

# Logging
LOG_LEVEL=INFO

# Server
SERVER_PORT=8080
```

---

## 🐳 Docker & Compose

### Rodar Tudo com Docker Compose

```bash
# Iniciar stack completo
docker-compose up -d

# Ver logs
docker-compose logs -f pix-simulator

# Parar tudo
docker-compose down

# Limpar tudo (volumes, networks)
docker-compose down -v
```

### Stack Incluído

| Serviço | Porta | URL |
|---------|-------|-----|
| **PIX Simulator** | 8080 | http://localhost:8080 |
| **RabbitMQ Management** | 15672 | http://localhost:15672 |
| **Redis** | 6379 | localhost:6379 |
| **Prometheus** | 9090 | http://localhost:9090 |
| **Grafana** | 3000 | http://localhost:3000 |
| **Jaeger UI** | 16686 | http://localhost:16686 |

### Credenciais Padrão

| Serviço | User | Password |
|---------|------|----------|
| RabbitMQ | guest | guest |
| Grafana | admin | admin |
| H2 Console | sa | (vazio) |

---

## 📖 Documentação

### 📚 Documentação Automática (Swagger)

Após iniciar a aplicação:

```
🔗 http://localhost:8080/swagger-ui.html
```

- Visualize todos os endpoints
- Teste requisições diretamente
- Veja exemplos de request/response
- Analise modelos de dados

### 📄 Guias Inclusos

| Arquivo | Propósito |
|---------|-----------|
| **STARTUP-GUIDE.md** | Como iniciar a aplicação |
| **MICROSERVICES.md** | Arquitetura de microserviços |
| **IMPLEMENTATION-GUIDE.md** | Guia de implementação completa |
| **HELP.md** | Troubleshooting e FAQ |

---

## 🧪 Testes

### Rodar Testes

```bash
# Testes unitários
mvn test

# Com cobertura
mvn test jacoco:report

# Testes específicos
mvn test -Dtest=PixServiceTest
```

### Cobertura de Testes

- ✅ `PixServiceTest` - Testes de criar transação
- ✅ `PixParceladoServiceTest` - Testes de parcelado
- ✅ `DevolucaoServiceTest` - Testes de devolução
- ✅ Integração E2E

**Status:** 100% dos testes passando ✅

---

## 🛠️ Tecnologias

### Core
- **Java 21** - Linguagem
- **Spring Boot 3.2.2** - Framework web
- **Maven 3.8+** - Build tool
- **H2 Database** - Banco em memória (dev)

### Data & Persistence
- **Spring Data JPA** - ORM
- **Hibernate 6.4** - Persistência
- **Lombok** - Reduzir boilerplate

### Web & API
- **Spring Web MVC** - REST
- **Swagger/OpenAPI** - Documentação automática
- **Jackson** - Serialização JSON

### Message & Events
- **Spring AMQP** - RabbitMQ
- **RabbitMQ** - Message broker

### Cache
- **Spring Data Redis** - Redis client
- **Redis** - Cache distribuído
- **Caffeine** - Cache local

### Resilience
- **Resilience4j** - Circuit breaker
- **Spring Retry** - Retry automático

### Observability
- **Micrometer** - Métricas
- **Prometheus** - Time-series DB
- **Grafana** - Visualização
- **Jaeger** - Distributed tracing
- **SLF4J** - Logging

### Security
- **Spring Security** - Autenticação/Autorização
- **ZXing** - QR Code generation

### Testing
- **JUnit 5** - Framework de testes
- **Mockito** - Mocking
- **Spring Test** - Testes de integração

---

## 📚 Referências

### Documentação Oficial
- [Spring Boot Docs](https://spring.io/projects/spring-boot)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [Spring Security](https://spring.io/projects/spring-security)
- [RabbitMQ Java Client](https://www.rabbitmq.com/java-client.html)
- [Redis Java Client](https://github.com/redis/jedis)

### PIX & Pagamentos
- [Manual PIX - Banco Central](https://www.bcb.gov.br/content/dam/Microsites/pix/Oversight/Manual_de_padronizacoes_v06_20201125.pdf)
- [PIX API - Especificações](https://www.bcb.gov.br/pix)
- [QR Code Format](https://www.emvco.com/specification/qr-code-specification-for-payment-systems-emv-qrcps/)

### Arquitetura & Padrões
- [Microservices Architecture](https://microservices.io/)
- [Event-Driven Architecture](https://martinfowler.com/articles/201701-event-driven.html)
- [CQRS Pattern](https://martinfowler.com/bliki/CQRS.html)
- [Saga Pattern](https://microservices.io/patterns/data/saga.html)

### DevOps & Deploy
- [Docker](https://docs.docker.com/)
- [Docker Compose](https://docs.docker.com/compose/)
- [Kubernetes](https://kubernetes.io/)
- [GitHub Actions](https://github.com/features/actions)

---

## 🤝 Contribuindo

Contribuições são bem-vindas! Para contribuir:

1. **Fork** o projeto
2. **Crie uma branch** para sua feature (`git checkout -b feature/minha-feature`)
3. **Commit** suas mudanças (`git commit -am 'Add minha feature'`)
4. **Push** para a branch (`git push origin feature/minha-feature`)
5. **Abra um Pull Request**

### Diretrizes
- Mantenha o código limpo e bem documentado
- Adicione testes para novas funcionalidades
- Atualize a documentação se necessário
- Siga as convenções do projeto

---

## 🐛 Troubleshooting

### Porta 8080 já está em uso

```bash
# Linux/Mac: Encontrar processo
lsof -i :8080

# Windows PowerShell
Get-NetTCPConnection -LocalPort 8080

# Solução: Mudar porta em application.yaml
server:
  port: 8081
```

### RabbitMQ connection refused

```yaml
# Desabilitar RabbitMQ
spring:
  rabbitmq:
    host: localhost  # ou comentar
```

### Redis connection refused

```yaml
# Similar ao RabbitMQ
spring:
  redis:
    host: localhost
```

### H2 não carrega

```yaml
# Verificar configuração
spring:
  h2:
    console:
      enabled: true
      path: /h2-console
```

Acesse: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:pixdb`
- Username: `sa`
- Password: (deixar vazio)

---

## 📋 Roadmap

### ✅ Implementado
- [x] Transações PIX básicas
- [x] QR Code generation
- [x] Cobranças parceladas
- [x] Devoluções/refund
- [x] Circuit breaker
- [x] Eventos/messaging
- [x] Métricas e observabilidade
- [x] Testes unitários

### 🔄 Em Progresso
- [ ] API Gateway (Spring Cloud Gateway)
- [ ] Múltiplos serviços separados
- [ ] OAuth2/JWT full implementation
- [ ] mTLS entre serviços
- [ ] Kubernetes deployment

### 📅 Planejado
- [ ] Webhooks reais (callback)
- [ ] Integração com auth real (Keycloak)
- [ ] Service mesh (Istio)
- [ ] Saga pattern
- [ ] API versioning (v1, v2)

---

## 📜 Licença

Este projeto está licenciado sob a **MIT License** - veja o arquivo [LICENSE](./LICENSE) para detalhes.

```
MIT License

Copyright (c) 2026 OCoolDev

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software...
```

---

## 📞 Suporte & Contato

- 📧 Email: [seu-email@example.com](mailto:seu-email@example.com)
- 🐛 Issues: [GitHub Issues](https://github.com/seu-usuario/ms-simulador-pix/issues)
- 💬 Discussions: [GitHub Discussions](https://github.com/seu-usuario/ms-simulador-pix/discussions)
- 🌐 Website: [seu-website.com](https://seu-website.com)

---

## 🙏 Agradecimentos

Agradecimentos especiais a:
- 💜 Spring Boot & Spring Community
- 🐰 RabbitMQ Team
- 🚀 Prometheus & Grafana Communities
- 📊 Jaeger Tracing Project
- 🏦 Banco Central do Brasil (PIX)

---

## ⭐ Se Gostou, Dê uma Star!

Se este projeto foi útil para você, considere dar uma ⭐ no GitHub!

```bash
# Clone e comece a usar
git clone https://github.com/seu-usuario/ms-simulador-pix.git
cd ms-simulador-pix
mvn spring-boot:run
```

**Bom coding! 🚀**

---

<div align="center">

**Made with ❤️ para a comunidade Java/Spring Boot**

Desenvolvido em 2026 • [OCoolDev](https://github.com/ocooldev) • Brasil 🇧🇷

</div>

