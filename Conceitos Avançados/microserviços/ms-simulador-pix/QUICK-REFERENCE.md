# 🚀 QUICK REFERENCE GUIDE

> Guia rápido de referência - Todos os comandos e informações essenciais em um único lugar

---

## ⚡ Comandos Essenciais

### Iniciar Aplicação

```bash
# Opção 1: Maven (desenvolvimento)
mvn clean install
mvn spring-boot:run

# Opção 2: JAR Direto
mvn clean package -DskipTests
java -jar target/ms-simulador-pix-1.0.0.jar

# Opção 3: PowerShell (Windows)
.\RUN.ps1

# Opção 4: Docker Compose (stack completo)
docker-compose up -d
```

### Parar Aplicação

```bash
# Maven: Ctrl + C
# JAR: Ctrl + C
# Docker: docker-compose down
```

### Testes

```bash
# Rodar todos os testes
mvn test

# Teste específico
mvn test -Dtest=PixServiceTest

# Com cobertura
mvn test jacoco:report
```

### Compilar

```bash
# Clean + Compile + Package
mvn clean package -DskipTests

# Apenas compilar
mvn clean compile

# Com testes
mvn clean package
```

---

## 🌐 URLs Importantes

### Aplicação Principal

| Recurso | URL |
|---------|-----|
| **API Base** | http://localhost:8080 |
| **Swagger UI** | http://localhost:8080/swagger-ui.html |
| **API Docs (JSON)** | http://localhost:8080/v3/api-docs |
| **Health Check** | http://localhost:8080/actuator/health |
| **Métricas** | http://localhost:8080/actuator/metrics |
| **Prometheus** | http://localhost:8080/actuator/prometheus |
| **H2 Console** | http://localhost:8080/h2-console |

### Infraestrutura (Docker Compose)

| Serviço | URL | User | Pass |
|---------|-----|------|------|
| **RabbitMQ** | http://localhost:15672 | guest | guest |
| **Prometheus** | http://localhost:9090 | - | - |
| **Grafana** | http://localhost:3000 | admin | admin |
| **Jaeger** | http://localhost:16686 | - | - |
| **Redis** | localhost:6379 | - | - |

---

## 📝 Exemplos de API Calls

### Criar Transação

```bash
curl -X POST http://localhost:8080/pix/authorize \
  -H "Content-Type: application/json" \
  -H "Idempotency-Key: unique-123" \
  -d '{
    "chave": "usuario@email.com",
    "valor": "100.50",
    "solicitacaoPagador": "Pagamento teste"
  }'
```

### Listar Transações

```bash
curl -X GET http://localhost:8080/pix/transactions
```

### Obter Detalhes

```bash
curl -X GET http://localhost:8080/pix/transaction/ABC123DEF456
```

### Gerar QR Code

```bash
curl -X GET http://localhost:8080/pix/transaction/ABC123DEF456/qrcode \
  -o qrcode.png
```

### Confirmar Transação

```bash
curl -X POST http://localhost:8080/pix/refund/ABC123DEF456
```

---

## ⚙️ Configuração Rápida

### H2 Database

```
JDBC URL: jdbc:h2:mem:pixdb
Username: sa
Password: (deixar vazio)
```

### application.yaml - Valores Principais

```yaml
server.port: 8080
spring.datasource.url: jdbc:h2:mem:pixdb
spring.jpa.hibernate.ddl-auto: update
spring.rabbitmq.host: localhost
spring.redis.host: localhost
logging.level.com.ocooldev.pix: DEBUG
```

---

## 🐳 Docker Compose

```bash
# Iniciar
docker-compose up -d

# Ver logs
docker-compose logs -f pix-simulator

# Parar
docker-compose down

# Remover volumes
docker-compose down -v

# Ver status
docker-compose ps
```

### Serviços

```
pix-simulator      → 8080  (Aplicação)
rabbitmq          → 5672  (Message Broker)
rabbitmq-ui       → 15672 (Management)
redis             → 6379  (Cache)
prometheus        → 9090  (Métricas)
grafana           → 3000  (Dashboards)
jaeger            → 16686 (Tracing)
```

---

## 📊 Estrutura de Pastas

```
src/main/java/
├── domain/
│   ├── model/          (Entidades JPA)
│   └── service/        (Lógica de negócio)
├── infrastructure/
│   ├── config/         (Configurações)
│   ├── controller/     (Endpoints REST)
│   ├── dto/            (Data Transfer Objects)
│   ├── event/          (Eventos)
│   ├── repository/     (Data Access)
│   └── messaging/      (RabbitMQ)
└── gateway/            (API Gateway - futuro)

src/main/resources/
├── application.yaml
├── application-dev.yaml
├── application-prod.yaml
└── logback-spring.xml

src/test/java/
└── (Testes unitários e integração)
```

---

## 🔍 Verificações Úteis

### Health Check

```bash
curl -s http://localhost:8080/actuator/health | jq
```

### Métricas

```bash
curl -s http://localhost:8080/actuator/metrics | jq
```

### Tabelas do Banco

```sql
SHOW TABLES;
SELECT * FROM pix_transaction;
SELECT * FROM devolucao;
SELECT * FROM parcela;
```

### RabbitMQ Queues

```bash
docker exec rabbitmq rabbitmqctl list_queues
```

### Redis Keys

```bash
redis-cli
> KEYS *
> GET pix:*
> DEL pix:*
```

---

## 🆘 Troubleshooting Rápido

| Problema | Solução |
|----------|---------|
| **Porta 8080 em uso** | Mudar em `application.yaml` → `server.port: 8081` |
| **RabbitMQ offline** | Comentar config RabbitMQ em `application.yaml` |
| **Redis offline** | Comentar config Redis em `application.yaml` |
| **H2 não conecta** | Verificar `jdbc:h2:mem:pixdb` em H2 console |
| **Testes falhando** | `mvn clean test` ou limpar `.m2/repository` |
| **Container não inicia** | `docker-compose down -v && docker-compose up -d` |

---

## 📚 Arquivos de Documentação

| Arquivo | Propósito |
|---------|-----------|
| **README.md** | Documentação principal (este arquivo ⭐) |
| **QUICK-REFERENCE.md** | Guia rápido (você está aqui) |
| **STARTUP-GUIDE.md** | Como iniciar a aplicação |
| **MICROSERVICES.md** | Arquitetura de microserviços |
| **IMPLEMENTATION-GUIDE.md** | Guia de implementação |
| **HELP.md** | Problemas comuns e soluções |

---

## 🎯 Checklist de Verificação

- [ ] Java 21+ instalado (`java -version`)
- [ ] Maven 3.8+ instalado (`mvn -v`)
- [ ] Git clone feito
- [ ] `mvn clean install` rodou sem erros
- [ ] Aplicação inicia em http://localhost:8080
- [ ] Swagger UI acessível
- [ ] Testes passam (`mvn test`)
- [ ] Docker Compose funciona (opcional)

---

## 🔑 Credenciais Padrão

```
RabbitMQ Management
├── URL: http://localhost:15672
├── User: guest
└── Password: guest

Grafana
├── URL: http://localhost:3000
├── User: admin
└── Password: admin

H2 Console
├── URL: http://localhost:8080/h2-console
├── JDBC: jdbc:h2:mem:pixdb
├── User: sa
└── Password: (vazio)
```

---

## 📦 Dependências Principais

```xml
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-web</artifactId>
  <!-- Spring MVC para REST -->
</dependency>

<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-data-jpa</artifactId>
  <!-- Persistência de dados -->
</dependency>

<dependency>
  <groupId>org.springdoc</groupId>
  <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
  <!-- Swagger/OpenAPI -->
</dependency>

<dependency>
  <groupId>com.google.zxing</groupId>
  <artifactId>javase</artifactId>
  <!-- QR Code generation -->
</dependency>

<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-amqp</artifactId>
  <!-- RabbitMQ -->
</dependency>

<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-data-redis</artifactId>
  <!-- Redis Cache -->
</dependency>

<dependency>
  <groupId>io.github.resilience4j</groupId>
  <artifactId>resilience4j-spring-boot3</artifactId>
  <!-- Circuit Breaker -->
</dependency>
```

---

## 🎓 Próximos Passos

1. ✅ **Ler README.md** - Documentação completa
2. ✅ **Rodar STARTUP-GUIDE.md** - Iniciar aplicação
3. ✅ **Explorar Swagger** - Testar endpoints
4. ✅ **Rodar testes** - Validar funcionamento
5. ✅ **Docker Compose** - Stack completo (opcional)

---

## 💡 Dicas Úteis

```bash
# Ver classe Application
cat src/main/java/com/ocooldev/pix/ms_simulador_pix/MsSimuladorPixApplication.java

# Ver controllers
ls -la src/main/java/com/ocooldev/pix/ms_simulador_pix/infrastructure/controller/

# Ver logs em tempo real
mvn spring-boot:run | grep -i pix

# Limpar cache Maven
rm -rf ~/.m2/repository/com/ocooldev/pix

# Build offline
mvn clean install -o

# Debug mode
mvn spring-boot:run -Ddebug

# Verificar porta
netstat -an | grep 8080  # Linux/Mac
Get-NetTCPConnection -LocalPort 8080  # Windows
```

---

## 🚀 Deployment (Produção)

### Variáveis de Ambiente

```bash
export SERVER_PORT=8080
export DB_URL=jdbc:h2:file:/data/pixdb
export LOG_LEVEL=INFO
export RABBITMQ_HOST=rabbitmq.prod
export REDIS_HOST=redis.prod
```

### Build para Produção

```bash
mvn clean package -DskipTests -P prod
java -jar target/ms-simulador-pix-1.0.0.jar
```

### Docker Build

```bash
docker build -t ocooldev/ms-simulador-pix:1.0.0 .
docker run -p 8080:8080 ocooldev/ms-simulador-pix:1.0.0
```

---

**Última atualização:** 2026-04-02  
**Versão:** 1.0.0  
**Status:** ✅ Estável e Funcional

