 📌 Como Iniciar o ms-simulador-pix

## 🚀 Opção 1: Rodar com Maven (Desenvolvimento)

```bash
mvn clean install
mvn spring-boot:run
```

A aplicação estará disponível em: **http://localhost:8080**

---

## 🚀 Opção 2: Rodar JAR diretamente

```bash
mvn clean package -DskipTests
java -jar target/ms-simulador-pix-1.0.0.jar
```

---

## 🚀 Opção 3: PowerShell Script (Windows)

```powershell
.\RUN.ps1
```

---

## 🌐 Acessos disponíveis

### API REST
- **Base URL**: `http://localhost:8080`
- **Health Check**: `http://localhost:8080/actuator/health`
- **Métricas**: `http://localhost:8080/actuator/metrics`

### Documentação
- **Swagger UI**: `http://localhost:8080/swagger-ui.html`
- **OpenAPI JSON**: `http://localhost:8080/v3/api-docs`

### Banco de Dados
- **H2 Console**: `http://localhost:8080/h2-console`
  - **JDBC URL**: `jdbc:h2:mem:pixdb`
  - **User**: `sa`
  - **Password**: (deixe em branco)

---

## 📋 Endpoints principais

### Transações PIX

```bash
# Criar transação
POST /pix/authorize
Content-Type: application/json

{
  "chave": "usuario@email.com",
  "valor": "100.00",
  "solicitacaoPagador": "Pagamento teste"
}

# Listar transações
GET /pix/transactions

# Obter detalhes
GET /pix/transaction/{txid}

# Gerar QR Code
GET /pix/transaction/{txid}/qrcode

# Confirmar/Liquidar transação
POST /pix/refund/{txid}
```

---

## 🐳 Docker Compose (Opcional)

Para rodar com dependências (RabbitMQ, Redis, Prometheus, Grafana):

```bash
docker-compose up -d
```

**Serviços disponíveis:**
- PIX Simulator: http://localhost:8080
- RabbitMQ Management: http://localhost:15672 (guest/guest)
- Prometheus: http://localhost:9090
- Grafana: http://localhost:3000 (admin/admin)
- Jaeger UI: http://localhost:16686

---

## ✅ Verificar se está rodando

```bash
# Linux/Mac
curl -s http://localhost:8080/actuator/health | jq

# Windows PowerShell
Invoke-WebRequest -Uri "http://localhost:8080/swagger-ui.html" -UseBasicParsing | Select-Object StatusCode
```

---

## 📝 Logs

Os logs são exibidos no console. Para aumentar o nível de debug, edite:

**src/main/resources/application.yaml**

```yaml
logging:
  level:
    root: DEBUG
    com.ocooldev.pix: DEBUG
```

---

## 🛑 Parar a aplicação

- **Maven**: `Ctrl + C` no terminal
- **Direto (JAR)**: `Ctrl + C`
- **Docker**: `docker-compose down`

---

## ❓ Troubleshooting

### "Port 8080 is already in use"
```bash
# Encontrar processo
lsof -i :8080  # macOS/Linux
Get-NetTCPConnection -LocalPort 8080  # Windows PowerShell

# Mudar porta no application.yaml
server:
  port: 8081
```

### "RabbitMQ connection refused"
Desabilite RabbitMQ no application.yaml:
```yaml
spring:
  rabbitmq:
    host: localhost  # ou remova a configuração
```

### "Redis connection refused"
Similar ao RabbitMQ, você pode desabilitar ou usar um servidor Redis local

---

## 📚 Estrutura do Projeto

```
ms-simulador-pix/
├── src/main/java
│   ├── domain/
│   │   ├── model/        # Entidades (PixTransaction, Devolucao, etc)
│   │   └── service/      # Lógica de negócio
│   └── infrastructure/
│       ├── controller/   # Endpoints REST
│       ├── config/       # Configurações (Security, etc)
│       ├── repository/   # Acesso a dados
│       └── messaging/    # Eventos e mensageria
├── src/main/resources/
│   ├── application.yaml  # Configurações
│   └── logback-spring.xml
├── pom.xml              # Dependências Maven
├── docker-compose.yml   # Orquestração Docker
└── RUN.ps1              # Script PowerShell
```

---

**Desenvolvido para simular transações PIX! 🎉**

